package br.com.diegorezende.dao;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;

import br.com.diegorezende.entidades.FluxoTarefaProponente;
import br.com.diegorezende.entidades.ProponenteFila;
import br.com.diegorezende.enums.StatusProcessamentoEnum;
import br.com.diegorezende.enums.TarefaProcessamentoEnum;
import br.com.diegorezende.util.JsonUtil;

public class MongoDAOImpl implements MongoDAO {

	private static final String URI = "mongodb://localhost:27017/";
	private static final String DATABASE = "facaparte";
	private static final String COLLECTION_FILA = "ProponenteFila";

	private static final String COLLECTION_FLUXO = "FluxoTarefaProponente";

	@Override
	public List<ProponenteFila> consultarFilaProponente(Integer idProponenteDigital, TarefaProcessamentoEnum tarefa) {
		List<ProponenteFila> retorno = new ArrayList<>();
		try (MongoClient client = getMongoClient()) {
			MongoCollection<Document> collection = getFilaProponenteCollection(client);

			Bson filter = and(eq("idTarefaAtual", tarefa.getId()), eq("idProponenteDigital", idProponenteDigital),
					eq("dataHoraFimProcessamento", null));
			Iterable<Document> documents = collection.find(filter);

			if (documents.iterator().hasNext()) {
				String json = documents.iterator().next().toJson();

				ProponenteFila converterJson = converterJsonProponenteFila(json);
				retorno.add(converterJson);
			}
			
		}

		return retorno;
	}

	public List<FluxoTarefaProponente> consultarFluxoTarefaProponente(TarefaProcessamentoEnum tarefa) throws Exception {
		List<FluxoTarefaProponente> retorno = new ArrayList<>();
		try (MongoClient client = getMongoClient()) {
			MongoCollection<Document> collection = getFluxoCollection(client);

			Bson filter = and(eq("idTarefaAtual", tarefa.getId()), eq("dataHoraFimFluxoTarefa", null));
			Iterable<Document> documents = collection.find(filter);
			
			documents.forEach(a -> {
				String json = a.toJson();
				FluxoTarefaProponente converterJson = converterJsonFluxo(json);
				retorno.add(converterJson);
			});
			
		}

		return retorno;
	}
	
	public List<FluxoTarefaProponente> consultarTodosFluxoTarefaProponente() throws Exception {
		List<FluxoTarefaProponente> retorno = new ArrayList<>();
		try (MongoClient client = getMongoClient()) {
			MongoCollection<Document> collection = getFluxoCollection(client);

			Bson filter = and(eq("dataHoraFimFluxoTarefa", null));
			Iterable<Document> documents = collection.find(filter);
			
			documents.forEach(a -> {
				String json = a.toJson();
				FluxoTarefaProponente converterJson = converterJsonFluxo(json);
				retorno.add(converterJson);
			});
			
		}

		return retorno;
	}

	private static MongoClient getMongoClient() {
		return new MongoClient(new MongoClientURI(getURI()));
	}

	private static String getURI() {
		return URI;
	}

	private static MongoCollection<Document> getFilaProponenteCollection(MongoClient client) {
		MongoDatabase database = client.getDatabase(DATABASE);
		return database.getCollection(COLLECTION_FILA);
	}

	private static MongoCollection<Document> getFluxoCollection(MongoClient client) {
		MongoDatabase database = client.getDatabase(DATABASE);
		return database.getCollection(COLLECTION_FLUXO);
	}

	@Override
	public void iniciarProcessamento(ProponenteFila proponente, TarefaProcessamentoEnum tarefaProcessamentoEnum) throws Exception {
		try (MongoClient client = getMongoClient()) {
			MongoCollection<Document> collection = getFilaProponenteCollection(client);
			
			Document proponenteMongo = new Document().append("_id", new ObjectId())
					.append("idProponenteDigital", proponente.getIdProponenteDigital())
					.append("idTarefaAtual", tarefaProcessamentoEnum.getId())
					.append("dataHoraInicioProcessamento", new Date())
					.append("dataHoraFimProcessamento", null)
					.append("erro", null);
			
			collection.insertOne(proponenteMongo);
			
		}
	}

	@Override
	public void finalizarProcessamentoComSucesso(ProponenteFila proponente) throws Exception {
		try (MongoClient client = getMongoClient()) {
			MongoCollection<Document> collection = getFilaProponenteCollection(client);

			Bson filter = and(eq("idProponenteDigital", proponente.getIdProponenteDigital()),
					eq("dataHoraFimProcessamento", null));

			Bson update = Updates.combine(
					Updates.set("dataHoraFimProcessamento", new Date()));
			UpdateResult result = collection.updateOne(filter, update);

			if (result == null || result.getModifiedCount() == 0) {
				throw new Exception("Não foi encontrado nenhum registro para alteracao.");
			}
		}
	}

	@Override
	public void finalizarProcessamentoComErro(ProponenteFila proponente, String erro) throws Exception {
		try (MongoClient client = getMongoClient()) {
			MongoCollection<Document> collection = getFilaProponenteCollection(client);

			Bson filter = and(eq("idProponenteDigital", proponente.getIdProponenteDigital()),
					eq("dataHoraFimProcessamento", null));

			Bson update = Updates.combine(
					Updates.set("dataHoraFimProcessamento", new Date()), Updates.set("erro", erro));
			UpdateResult result = collection.updateOne(filter, update);

			if (result == null || result.getModifiedCount() == 0) {
				throw new Exception("Não foi encontrado nenhum registro para alteracao.");
			}
		}
	}

	@Override
	public void atribuirProximaTarefa(ProponenteFila proponente, Integer proximaTarefa) {
		try (MongoClient client = getMongoClient()) {
			
			MongoCollection<Document> collection = getFluxoCollection(client);
			
			Bson filter = and(eq("idProponenteDigital", proponente.getIdProponenteDigital()),
					eq("dataHoraFimFluxoTarefa", null));

			Bson update = Updates.combine(
					Updates.set("idStatusProcessamento", StatusProcessamentoEnum.EXECUTANDO_COM_SUCESSO.getId()),
					Updates.set("dataHoraFimFluxoTarefa", new Date()));

			collection.updateOne(filter, update);
			
			
			Document proponenteMongo = new Document().append("_id", new ObjectId())
					.append("idProponenteDigital", proponente.getIdProponenteDigital())
					.append("idTarefaAtual", proximaTarefa)
					.append("idStatusProcessamento", StatusProcessamentoEnum.AGUARDANDO_EXECUCAO.getId())
					.append("dataHoraInicioFluxoTarefa", new Date())
					.append("dataHoraFimFluxoTarefa", null);

			collection.insertOne(proponenteMongo);
		}
	}
	
	private ProponenteFila converterJsonProponenteFila(final String json) {
		try {
			return JsonUtil.converterJsonToObject(json, ProponenteFila.class);
		} catch (IOException e) {
			System.out.println("Erro ao converter JSON");
		}

		return null;
	}

	private FluxoTarefaProponente converterJsonFluxo(final String json) {
		try {
			return JsonUtil.converterJsonToObject(json, FluxoTarefaProponente.class);
		} catch (IOException e) {
			System.out.println("Erro ao converter JSON");
			e.printStackTrace();
		}

		return null;
	}

	public void inserirFluxoTarefaProponente(List<Document> fluxos) {
		try (MongoClient client = getMongoClient()) {
			MongoCollection<Document> collection = getFluxoCollection(client);

			collection.insertMany(fluxos);

		}
	}

}
