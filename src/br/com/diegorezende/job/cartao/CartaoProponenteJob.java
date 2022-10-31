package br.com.diegorezende.job.cartao;

import br.com.diegorezende.job.FAPJob;
import br.com.diegorezende.job.FAPProducer;


public class CartaoProponenteJob extends FAPJob {

	@Override
	protected int getTamanhoMaximoFila() {
		return 500;
	}

	@Override
	protected int getQuantidadeTheads() {
		return 1;
	}

	@Override
	protected CartaoProponenteConsumer getConsumer() {
		return new CartaoProponenteConsumer(getDataQueue());
	}

	@Override
	protected FAPProducer getProducer() {
		return new CartaoProponenteProducer(getDataQueue());
	}

}
