package br.com.diegorezende.enums;

public enum StatusProcessamentoEnum {
	
	AGUARDANDO_EXECUCAO(1, "AGUARDANDO_EXECUCAO"),
	EXECUTANDO(2, "AGUARDANDO_EXECUCAO"),
	EXECUTANDO_COM_ERRO(3, "EXECUTANDO_COM_ERRO"),
	EXECUTANDO_COM_SUCESSO(4, "EXECUTANDO_COM_SUCESSO");
	
	private Integer id;

	private String descricao;
	
	private StatusProcessamentoEnum(Integer id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
