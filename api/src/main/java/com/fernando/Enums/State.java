package com.fernando.Enums;

public enum State {

	AMAZONAS("Amazonas"), 
	ALAGOAS("Alagoas"), 
	ACRE("Acre"),
	AMAPA("Amapá"), 
	BAHIA("Bahia"), 
	PARA("Pará"),
	MATO_GROSSO("Mato Grosso"), 
	MINAS_GERAIS("Minas Gerais"),
	MATO_GROSSO_DO_SUL("Mato Grosso do Sul"), 
	GOIAS("Goiás"),
	MARANHAO("Maranhão"), 
	RIO_GRANDE_DO_SUL("Rio Grande do Sul"),
	TOCANTINS("Tocantins"), 
	PIAUI("Piauí"), 
	SAO_PAULO("São Paulo"),
	RONDONIA("Rondônia"), 
	RORAIMA("Roraima"),
	PARANA("Paraná"), 
	CEARA("Ceará"), 
	PERNAMBUCO("Pernambuco"),
	SANTA_CATARINA("Santa Catarina"), 
	PARAIBA("Paraíba"),
	RIO_GRANDE_DO_NORTE("Rio Grande do Norte"), 
	ESPIRITO_SANTO("Espírito Santo"),
	RIO_DE_JANEIRO("Rio de Janeiro"), 
	SERGIPE("Sergipe"),
	DISTRITO_FEDERAL("Distrito Federal");

	private String descricao; 
	
	State(String descricao) {
		this.descricao = descricao;
	}

	public String descricao() {
		return descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
