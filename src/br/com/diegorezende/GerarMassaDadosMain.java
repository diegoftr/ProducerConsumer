package br.com.diegorezende;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.bson.Document;
import org.bson.types.ObjectId;

import br.com.diegorezende.dao.MongoDAOImpl;
import br.com.diegorezende.enums.StatusProcessamentoEnum;

public class GerarMassaDadosMain {

	public static void main(String[] args) {
		MongoDAOImpl dao = new MongoDAOImpl();

		int qntFluxos = 5000;

		List<Document> fluxos = new ArrayList<>();

		for (int i = 1; i < qntFluxos; i++) {

			Document fluxo = new Document().append("_id", new ObjectId()).append("idProponenteDigital", i)
					.append("idTarefaAtual", sortearTarefa())
					.append("idStatusProcessamento", StatusProcessamentoEnum.AGUARDANDO_EXECUCAO.getId())
					.append("dataHoraInicioFluxoTarefa", new Date())
					.append("dataHoraFimFluxoTarefa", null)
					;
			fluxos.add(fluxo);
		}

		dao.inserirFluxoTarefaProponente(fluxos);

	}

	private static Integer sortearTarefa() {
		Random random = new Random();
		return random.ints(1, 4).findFirst().getAsInt();
	}

}
