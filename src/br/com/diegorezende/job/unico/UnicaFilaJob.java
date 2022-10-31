package br.com.diegorezende.job.unico;

import java.util.ArrayList;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import br.com.diegorezende.job.FAPConsumer;
import br.com.diegorezende.job.FAPJob;
import br.com.diegorezende.job.FAPProducer;
import br.com.diegorezende.queue.Fila;
import br.com.diegorezende.util.ThreadUtil;


public class UnicaFilaJob implements Job {

	private Fila dataQueue;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		dataQueue = new Fila(getTamanhoMaximoFila());

		UnicaFilaProducer producer = new UnicaFilaProducer(dataQueue);
        Thread producerThread = new Thread(producer);

        UnicaFilaConsumer consumer = new UnicaFilaConsumer(dataQueue);
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
	
	private int getQuantidadeTheads() {
		return 1;
	}

	private int getTamanhoMaximoFila() {
		return 3000;
	}

	public Fila getDataQueue() {
		return dataQueue;
	}


}
