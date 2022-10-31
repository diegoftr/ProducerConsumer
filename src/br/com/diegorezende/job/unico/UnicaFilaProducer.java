package br.com.diegorezende.job.unico;

import java.util.List;

import br.com.diegorezende.dao.MongoDAOImpl;
import br.com.diegorezende.entidades.FluxoTarefaProponente;
import br.com.diegorezende.entidades.ProponenteFila;
import br.com.diegorezende.enums.TarefaProcessamentoEnum;
import br.com.diegorezende.queue.Fila;

public class UnicaFilaProducer implements Runnable {

	MongoDAOImpl dao = new MongoDAOImpl();

	private final Fila fila;
	
	private TarefaProcessamentoEnum tarefa;

	public UnicaFilaProducer(Fila fila) {
		this.fila = fila;
	}

	@Override
	public void run() {
		List<FluxoTarefaProponente> consultarProponente;
		try {
			consultarProponente = dao.consultarTodosFluxoTarefaProponente();
			consultarProponente.forEach(p -> {
				ProponenteFila filaProponente = new ProponenteFila();
				filaProponente.setDataHoraFimProcessamento(null);
				filaProponente.setDataHoraInicioProcessamento(null);
				filaProponente.setIdProponenteDigital(p.getIdProponenteDigital());
				filaProponente.setIdTarefaAtual(tarefa.getId());
				if(!fila.isFull())
					fila.add(filaProponente);
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
