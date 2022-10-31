package br.com.diegorezende.job.integralizar;

import br.com.diegorezende.job.FAPJob;
import br.com.diegorezende.job.FAPProducer;


public class IntegralizarProponenteJob extends FAPJob {

	@Override
	protected int getTamanhoMaximoFila() {
		return 500;
	}

	@Override
	protected int getQuantidadeTheads() {
		return 4;
	}

	@Override
	protected IntegralizarProponenteConsumer getConsumer() {
		return new IntegralizarProponenteConsumer(getDataQueue());
	}

	@Override
	protected FAPProducer getProducer() {
		return new IntegralizarProponenteProducer(getDataQueue());
	}

}
