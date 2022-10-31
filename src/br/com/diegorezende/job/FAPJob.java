package br.com.diegorezende.job;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import br.com.diegorezende.queue.Fila;
import br.com.diegorezende.util.ThreadUtil;

@DisallowConcurrentExecution
public abstract class FAPJob implements Job {
	
	private Fila dataQueue;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		dataQueue = new Fila(getTamanhoMaximoFila());

		FAPProducer producer = getProducer();
        Thread producerThread = new Thread(producer);

        FAPConsumer consumer = getConsumer();
        List<Thread> threads = new ArrayList<>();
        
        producerThread.start();
        threads.add(producerThread);
        
        for(int i = 0; i < getQuantidadeTheads(); i++) {
            Thread consumerThread = new Thread(consumer);
            consumerThread.start();
            threads.add(consumerThread);
        }
        
        ThreadUtil.waitForAllThreadsToComplete(threads);

	}
	
	public Fila getDataQueue() {
		return dataQueue;
	}

	protected abstract FAPProducer getProducer();

	protected abstract FAPConsumer getConsumer();

	protected abstract int getQuantidadeTheads();

	protected abstract int getTamanhoMaximoFila();

}
