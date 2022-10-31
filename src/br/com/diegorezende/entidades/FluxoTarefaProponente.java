package br.com.diegorezende.entidades;

import java.util.Date;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class FluxoTarefaProponente {
	
	private ObjectNode _id;
	
	private Integer idProponenteDigital;

	private Integer idTarefaAtual;

	private Integer idStatusProcessamento;
	
	private ObjectNode dataHoraInicioFluxoTarefa;

	private ObjectNode dataHoraFimFluxoTarefa;

	public Integer getIdProponenteDigital() {
		return idProponenteDigital;
	}

	public void setIdProponenteDigital(Integer idProponenteDigital) {
		this.idProponenteDigital = idProponenteDigital;
	}

	public ObjectNode get_id() {
		return _id;
	}

	public void set_id(ObjectNode _id) {
		this._id = _id;
	}

	public Integer getIdTarefaAtual() {
		return idTarefaAtual;
	}

	public void setIdTarefaAtual(Integer idTarefaAtual) {
		this.idTarefaAtual = idTarefaAtual;
	}

	public Integer getIdStatusProcessamento() {
		return idStatusProcessamento;
	}

	public void setIdStatusProcessamento(Integer idStatusProcessamento) {
		this.idStatusProcessamento = idStatusProcessamento;
	}

	public ObjectNode getDataHoraInicioFluxoTarefa() {
		return dataHoraInicioFluxoTarefa;
	}

	public void setDataHoraInicioFluxoTarefa(ObjectNode dataHoraInicioFluxoTarefa) {
		this.dataHoraInicioFluxoTarefa = dataHoraInicioFluxoTarefa;
	}

	public ObjectNode getDataHoraFimFluxoTarefa() {
		return dataHoraFimFluxoTarefa;
	}

	public void setDataHoraFimFluxoTarefa(ObjectNode dataHoraFimFluxoTarefa) {
		this.dataHoraFimFluxoTarefa = dataHoraFimFluxoTarefa;
	}
	
	

}
