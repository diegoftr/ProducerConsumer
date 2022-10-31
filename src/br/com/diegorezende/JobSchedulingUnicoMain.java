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

public class JobSchedulingUnicoMain {
	
	public static void main(String[] args) {
        SchedulerFactory shedFact = new StdSchedulerFactory();
        
        try {
            Scheduler scheduler = shedFact.getScheduler();
            
            JobDetail jobReceber = JobBuilder.newJob(ReceberProponenteJob.class)
                          .withIdentity("ReceberProponenteJob", "grupo1")
                          .build();
            Trigger triggerReceber = TriggerBuilder.newTrigger()
            		.withIdentity("ReceberProponenteJobTrigger", "grupo1")
            		.withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * 1/1 * ? *"))
            		.build();
            
            scheduler.scheduleJob(jobReceber, triggerReceber);


            scheduler.start();
            
     } catch (SchedulerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
     }

	}

}
