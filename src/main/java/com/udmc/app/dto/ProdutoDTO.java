package com.udmc.app.dto;

import java.io.Serializable;

import com.udmc.app.model.Produto;

public class ProdutoDTO implements Serializable {
	
	private static final long serialVersionUID = 48912788236341767L;

	private long id;
	
	private String nome;
	
	private Double preco;
	
	public ProdutoDTO() {}
	
	public ProdutoDTO(Produto produto) {
		this.id = produto.getId();
		this.nome = produto.getNome();
		this.preco = produto.getPreco();
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

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
}