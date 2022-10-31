package br.com.diegorezende.job.receber;

import br.com.diegorezende.job.FAPJob;
import br.com.diegorezende.job.FAPProducer;


public class ReceberProponenteJob extends FAPJob {

	@Override
	protected int getTamanhoMaximoFila() {
		return 500;
	}

	@Override
	protected int getQuantidadeTheads() {
		return 1;
	}

	@Override
	protected ReceberProponenteConsumer getConsumer() {
		return new ReceberProponenteConsumer(getDataQueue());
	}

	@Override
	protected FAPProducer getProducer() {
		return new ReceberProponenteProducer(getDataQueue());
	}

}
