package br.com.diegorezende.dao;

import java.util.List;

import org.bson.Document;

import br.com.diegorezende.entidades.FluxoTarefaProponente;
import br.com.diegorezende.entidades.ProponenteFila;
import br.com.diegorezende.enums.TarefaProcessamentoEnum;

public abstract interface MongoDAO {

	List<ProponenteFila> consultarFilaProponente(Integer idProponente, TarefaProcessamentoEnum tarefa);

	void iniciarProcessamento(ProponenteFila proponente, TarefaProcessamentoEnum tarefaProcessamentoEnum) throws Exception;

	void finalizarProcessamentoComSucesso(ProponenteFila proponente) throws Exception;

	void finalizarProcessamentoComErro(ProponenteFila proponente, String erro) throws Exception;

	void atribuirProximaTarefa(ProponenteFila proponente, Integer proximaTarefa) throws Exception;
	
	List<FluxoTarefaProponente> consultarFluxoTarefaProponente(TarefaProcessamentoEnum tarefa) throws Exception;

	void inserirFluxoTarefaProponente(List<Document> fluxo);
	
	List<FluxoTarefaProponente> consultarTodosFluxoTarefaProponente() throws Exception;
}
