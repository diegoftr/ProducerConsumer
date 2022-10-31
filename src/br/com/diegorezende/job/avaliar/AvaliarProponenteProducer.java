package br.com.diegorezende.job.avaliar;

import br.com.diegorezende.enums.TarefaProcessamentoEnum;
import br.com.diegorezende.job.FAPProducer;
import br.com.diegorezende.queue.Fila;

public class AvaliarProponenteProducer extends FAPProducer {

	public AvaliarProponenteProducer(Fila fila) {
		super(fila, TarefaProcessamentoEnum.AVALIAR);
	}


}
