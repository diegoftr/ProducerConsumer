package br.com.diegorezende.entidades;

import java.util.Date;

public class ProponenteFila {

	private Integer idProponenteDigital;

	private Integer idTarefaAtual;

	private Date dataHoraInicioProcessamento;

	private Date dataHoraFimProcessamento;
	
	private String erro;

	
	public String getErro() {
		return erro;
	}

	public void setErro(String erro) {
		this.erro = erro;
	}

	public Integer getIdProponenteDigital() {
		return idProponenteDigital;
	}

	public void setIdProponenteDigital(Integer idProponenteDigital) {
		this.idProponenteDigital = idProponenteDigital;
	}

	public Integer getIdTarefaAtual() {
		return idTarefaAtual;
	}

	public void setIdTarefaAtual(Integer idTarefaAtual) {
		this.idTarefaAtual = idTarefaAtual;
	}

	public Date getDataHoraInicioProcessamento() {
		return dataHoraInicioProcessamento;
	}

	public void setDataHoraInicioProcessamento(Date dataHoraInicioProcessamento) {
		this.dataHoraInicioProcessamento = dataHoraInicioProcessamento;
	}

	public Date getDataHoraFimProcessamento() {
		return dataHoraFimProcessamento;
	}

	public void setDataHoraFimProcessamento(Date dataHoraFimProcessamento) {
		this.dataHoraFimProcessamento = dataHoraFimProcessamento;
	}

}