package br.com.diegorezende.enums;

public enum TarefaProcessamentoEnum {
	
	RECEBER(1, "RECEBER"),
	AVALIAR(2, "AVALIAR"),
	INTEGRALIZAR(3, "INTEGRALIZAR"),
	CARTAO(4, "CARTAO");
	
	private Integer id;

	private String descricao;
	
	private TarefaProcessamentoEnum(Integer id, String descricao) {
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
