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

import br.com.diegorezende.job.avaliar.AvaliarProponenteJob;
import br.com.diegorezende.job.cartao.CartaoProponenteJob;
import br.com.diegorezende.job.integralizar.IntegralizarProponenteJob;
import br.com.diegorezende.job.receber.ReceberProponenteJob;

public class JobSchedulingMain {
	
	public static void main(String[] args) throws SchedulerException {
        SchedulerFactory shedFact = new StdSchedulerFactory();
        
            Scheduler scheduler = shedFact.getScheduler();
            
            
            JobDetail jobReceber = JobBuilder.newJob(ReceberProponenteJob.class)
                          .withIdentity("ReceberProponenteJob", "grupo1")
                          .build();
            Trigger triggerReceber = TriggerBuilder.newTrigger()
            		.withIdentity("ReceberProponenteJobTrigger", "grupo1")
            		.withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * 1/1 * ? *"))
            		.build();
            
            
            JobDetail jobAvaliar = JobBuilder.newJob(AvaliarProponenteJob.class)
                    .withIdentity("AvaliarProponenteJob", "grupo1")
                    .build();
            Trigger triggerAvaliar = TriggerBuilder.newTrigger()
            		.withIdentity("AvaliarProponenteTrigger", "grupo1")
            		.withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * 1/1 * ? *"))
            		.build();

            
            JobDetail jobIntegralizar = JobBuilder.newJob(IntegralizarProponenteJob.class)
                    .withIdentity("IntegralizarProponenteJob", "grupo1")
                    .build();
            Trigger triggerIntegralizar = TriggerBuilder.newTrigger()
            		.withIdentity("IntegralizarProponenteTrigger", "grupo1")
            		.withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * 1/1 * ? *"))
            		.build();
            
            
            JobDetail jobCartao = JobBuilder.newJob(CartaoProponenteJob.class)
                    .withIdentity("CartaoProponenteJob", "grupo1")
                    .build();
            Trigger triggerCartao = TriggerBuilder.newTrigger()
            		.withIdentity("CartaoProponenteTrigger", "grupo1")
            		.withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * 1/1 * ? *"))
            		.build();
            
            
            scheduler.scheduleJob(jobReceber, triggerReceber);
            scheduler.scheduleJob(jobAvaliar, triggerAvaliar);
            scheduler.scheduleJob(jobIntegralizar, triggerIntegralizar);
            scheduler.scheduleJob(jobCartao, triggerCartao);


            scheduler.start();
            

	}

}
