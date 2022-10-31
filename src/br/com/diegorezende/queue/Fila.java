package br.com.diegorezende.queue;

import java.util.LinkedList;
import java.util.Queue;

import br.com.diegorezende.entidades.ProponenteFila;
import br.com.diegorezende.enums.TarefaProcessamentoEnum;

public class Fila {

	private final Queue<ProponenteFila> fila = new LinkedList<>();
	private final int tamanhoMaximo;
	private final Object FULL_QUEUE = new Object();
	private final Object EMPTY_QUEUE = new Object();
	
	public  Queue<ProponenteFila> getListaFila() {
		return fila;
	}

	public Fila(int tamanhoMaximo) {
		this.tamanhoMaximo = tamanhoMaximo;
	}

	public boolean isFull() {
		return fila.size() == tamanhoMaximo;
	}

	public boolean isEmpty() {
		return fila.isEmpty();
	}

	public void waitOnFull() throws InterruptedException {
		synchronized (FULL_QUEUE) {
			FULL_QUEUE.wait();
		}
	}

	public void waitOnEmpty() throws InterruptedException {
		synchronized (EMPTY_QUEUE) {
			EMPTY_QUEUE.wait();
		}
	}

	public void notifyAllForFull() {
		synchronized (FULL_QUEUE) {
			FULL_QUEUE.notifyAll();
		}
	}

	public void notifyAllForEmpty() {
		synchronized (EMPTY_QUEUE) {
			EMPTY_QUEUE.notifyAll();
		}
	}

	public void add(ProponenteFila message) {
		synchronized (fila) {
			fila.add(message);
		}
	}

	public ProponenteFila remove() {
		synchronized (fila) {
			return fila.poll();
		}
	}

}