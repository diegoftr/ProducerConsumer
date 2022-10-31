package br.com.diegorezende.job;

import br.com.diegorezende.dao.MongoDAOImpl;
import br.com.diegorezende.entidades.ProponenteFila;
import br.com.diegorezende.enums.TarefaProcessamentoEnum;
import br.com.diegorezende.queue.Fila;
import br.com.diegorezende.util.ThreadUtil;

public abstract class FAPConsumer implements Runnable {

	public MongoDAOImpl dao = new MongoDAOImpl();

	private final Fila fila;

	private volatile boolean runFlag = true;
	private TarefaProcessamentoEnum tarefa;

	public FAPConsumer(Fila fila, TarefaProcessamentoEnum tarefa) {
		this.fila = fila;
		this.tarefa = tarefa;
	}

	@Override
	public void run() {
		ThreadUtil.sleep(5000);
		while (runFlag) {
			ProponenteFila proponente = fila.remove();
			if (proponente != null) {
				try {
					if (tarefa.getId().equals(proponente.getIdTarefaAtual())) {
						dao.iniciarProcessamento(proponente, tarefa);
						try {
							processar(proponente);
							dao.finalizarProcessamentoComSucesso(proponente);
						} catch (Exception e) {
							dao.finalizarProcessamentoComErro(proponente, e.getMessage());
						}
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			if (fila.isEmpty()) {
				runFlag = false;
				fila.notifyAllForEmpty();
			}
		}
	}

	public abstract void processar(ProponenteFila proponente) throws Exception;

}
