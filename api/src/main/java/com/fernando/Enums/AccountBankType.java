package com.fernando.Enums;

public enum AccountBankType {
	
	CORRENTE ("CORRENTE"),
	POUPANÇA ("POUPANÇA");

	private String descricao; 
	
	AccountBankType(String descricao) {
		this.descricao = descricao;
	}

	public String descricao() {
		return descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	
	
	
}
