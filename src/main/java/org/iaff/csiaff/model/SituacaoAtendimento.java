package org.iaff.csiaff.model;

public enum SituacaoAtendimento {

	AGE("Agendado"),
	AGU("Aguardando"),
	EMA("Atendimento"),
	ATE("Atendido"),
	FAL("Falta");

	private String descricao;
	
	SituacaoAtendimento(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

}

