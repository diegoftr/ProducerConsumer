package br.com.diegorezende.job.unico;

import br.com.diegorezende.dao.MongoDAOImpl;
import br.com.diegorezende.entidades.ProponenteFila;
import br.com.diegorezende.enums.TarefaProcessamentoEnum;
import br.com.diegorezende.job.receber.ReceberProponenteConsumer;
import br.com.diegorezende.queue.Fila;
import br.com.diegorezende.util.ThreadUtil;

public class UnicaFilaConsumer implements Runnable {

	public MongoDAOImpl dao = new MongoDAOImpl();

	private final Fila fila;

	private volatile boolean runFlag = true;

	public UnicaFilaConsumer(Fila fila) {
		this.fila = fila;
	}

	@Override
	public void run() {
		ThreadUtil.sleep(5000);
		while (runFlag) {
			ProponenteFila proponente = fila.remove();
			if (proponente != null) {
				try {
//					dao.iniciarProcessamento(proponente);
					
					if (TarefaProcessamentoEnum.RECEBER.getId().equals(proponente.getIdTarefaAtual())) {
						
						ReceberProponenteConsumer asd = new ReceberProponenteConsumer(fila);
						asd.processar(proponente);
						try {
//							processar(proponente);
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


}