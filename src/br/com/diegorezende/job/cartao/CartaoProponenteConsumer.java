package br.com.diegorezende.job.cartao;

import java.util.Random;

import br.com.diegorezende.entidades.ProponenteFila;
import br.com.diegorezende.enums.TarefaProcessamentoEnum;
import br.com.diegorezende.job.FAPConsumer;
import br.com.diegorezende.queue.Fila;
import br.com.diegorezende.util.ThreadUtil;

public class CartaoProponenteConsumer extends FAPConsumer {

	public CartaoProponenteConsumer(Fila fila) {
		super(fila, TarefaProcessamentoEnum.CARTAO);
	}

	@Override
	public void processar(ProponenteFila proponente) throws Exception {
		Random rand = new Random();

//		Pausa aleatoria
//		ThreadUtil.sleep(rand.ints(0, 300).findFirst().getAsInt());

		// Erro aleatorio
//		int n = rand.nextInt(100);
//		if (n > 80) {
//			throw new Exception("Erro ao preocessar proponente id - " + proponente.getIdProponenteDigital());
//		}

		dao.atribuirProximaTarefa(proponente, TarefaProcessamentoEnum.RECEBER.getId());

	}

}