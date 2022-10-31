package br.com.diegorezende.job.integralizar;

import br.com.diegorezende.enums.TarefaProcessamentoEnum;
import br.com.diegorezende.job.FAPProducer;
import br.com.diegorezende.queue.Fila;

public class IntegralizarProponenteProducer extends FAPProducer {

	public IntegralizarProponenteProducer(Fila fila) {
		super(fila, TarefaProcessamentoEnum.INTEGRALIZAR);
	}


}
