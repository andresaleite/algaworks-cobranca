package br.com.marquei.cobranca.dominio;

public enum EnumStatusTitulo {

	PENDENTE("Pendente"),
	RECEBIDO("Recebido");
	
	private String descricao;
	
	EnumStatusTitulo(String descricao) {
		this.descricao = descricao;
				
	}

	public String getDescricao() {
		return descricao;
	}


	
	
}
