package br.com.diegorezende.job.avaliar;

import br.com.diegorezende.job.FAPJob;
import br.com.diegorezende.job.FAPProducer;


public class AvaliarProponenteJob extends FAPJob {

	@Override
	protected int getTamanhoMaximoFila() {
		return 500;
	}

	@Override
	protected int getQuantidadeTheads() {
		return 4;
	}

	@Override
	protected AvaliarProponenteConsumer getConsumer() {
		return new AvaliarProponenteConsumer(getDataQueue());
	}

	@Override
	protected FAPProducer getProducer() {
		return new AvaliarProponenteProducer(getDataQueue());
	}

}
