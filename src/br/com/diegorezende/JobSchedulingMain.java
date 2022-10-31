package br.com.diegorezende;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.listeners.JobChainingJobListener;

import br.com.diegorezende.job.avaliar.AvaliarProponenteJob;
import br.com.diegorezende.job.cartao.CartaoProponenteJob;
import br.com.diegorezende.job.integralizar.IntegralizarProponenteJob;
import br.com.diegorezende.job.receber.ReceberProponenteJob;

public class JobSchedulingMain {
	
	public static void main(String[] args) throws SchedulerException {
        SchedulerFactory shedFact = new StdSchedulerFactory();
        
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            
            JobChainingJobListener jobListener = new JobChainingJobListener("ChainListener");
            
            JobDetail jobReceber = JobBuilder.newJob(ReceberProponenteJob.class)
                          .withIdentity("ReceberProponenteJob", "grupo1")
                          .build();
            Trigger trigger = TriggerBuilder.newTrigger()
            		.withIdentity("JobTrigger", "grupo1")
            		.withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * 1/1 * ? *"))
            		.build();
            

            JobDetail jobAvaliar = JobBuilder.newJob(AvaliarProponenteJob.class)
                    .storeDurably(true)
                    .build();
            
            JobDetail jobIntegralizar = JobBuilder.newJob(IntegralizarProponenteJob.class)
            		.storeDurably(true)
                    .build();
            
            JobDetail jobCartao = JobBuilder.newJob(CartaoProponenteJob.class)
            		.storeDurably(true)
                    .build();
            
            scheduler.scheduleJob(jobReceber, trigger);
            scheduler.addJob(jobAvaliar, true);
            scheduler.addJob(jobIntegralizar, true);
            scheduler.addJob(jobCartao, true);
            
            jobListener.addJobChainLink(jobReceber.getKey(), jobAvaliar.getKey());
            jobListener.addJobChainLink(jobAvaliar.getKey(), jobIntegralizar.getKey());
            jobListener.addJobChainLink(jobIntegralizar.getKey(), jobCartao.getKey());
            
            
            scheduler.getListenerManager().addJobListener(jobListener);

         


            scheduler.start();
            

	}

}
