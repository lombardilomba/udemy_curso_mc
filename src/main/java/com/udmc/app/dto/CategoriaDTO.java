package com.udmc.app.dto;

import java.io.Serializable;

import com.udmc.app.model.Categoria;

public class CategoriaDTO implements Serializable {
	
	private static final long serialVersionUID = 48912788236341767L;

	private long id;
	
	private String nome;
	
	public CategoriaDTO() {}
	
	public CategoriaDTO(Categoria categoria) {
		this.id = categoria.getId();
		this.nome = categoria.getNome();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}