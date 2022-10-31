package br.com.diegorezende.job.cartao;

import br.com.diegorezende.enums.TarefaProcessamentoEnum;
import br.com.diegorezende.job.FAPProducer;
import br.com.diegorezende.queue.Fila;

public class CartaoProponenteProducer extends FAPProducer {

	public CartaoProponenteProducer(Fila fila) {
		super(fila, TarefaProcessamentoEnum.CARTAO);
	}


}
