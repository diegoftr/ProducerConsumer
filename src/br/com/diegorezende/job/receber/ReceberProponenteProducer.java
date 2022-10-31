package br.com.diegorezende.job.receber;

import br.com.diegorezende.enums.TarefaProcessamentoEnum;
import br.com.diegorezende.job.FAPProducer;
import br.com.diegorezende.queue.Fila;

public class ReceberProponenteProducer extends FAPProducer {

	public ReceberProponenteProducer(Fila fila) {
		super(fila, TarefaProcessamentoEnum.RECEBER);
	}


}
