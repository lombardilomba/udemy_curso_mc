package com.udmc.app.model.enums;

public enum TipoCliente {
	
	PESSOAFISICA(1, "Pessoa Física"),
	PESSOAJURIDICA(2, "Pessoa Juridica");
	
	private int codigo;
	
	private String descricao;
	
	private TipoCliente(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public static TipoCliente toEnum(Integer codigo) {
		if(codigo == null)
			return null;
		
		for(TipoCliente tipo : TipoCliente.values()) {
			if(codigo.equals(tipo.getCodigo()))
					return tipo;
		}
		
		throw new IllegalArgumentException("Id inválido: " + codigo);
	}

	public int getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
}