package com.udmc.app.model;

import java.io.Serializable;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.udmc.app.model.enums.EstadoPagamento;

@Entity
@JsonTypeName("pagamentoComCartao")
public class PagamentoComCartao extends Pagamento implements Serializable {

	private static final long serialVersionUID = 6158911697014320798L;
	
	private int numeroDeParcelas;
	
	public PagamentoComCartao() {
		super();
	}

	public PagamentoComCartao(Long id, EstadoPagamento estado, Pedido pedido, int numeroParcelas) {
		super(id, estado, pedido);
		this.numeroDeParcelas = numeroParcelas;
	}

	public int getNumeroDeParcelas() {
		return numeroDeParcelas;
	}

	public void setNumeroDeParcelas(int numeroDeParcelas) {
		this.numeroDeParcelas = numeroDeParcelas;
	}
	
}