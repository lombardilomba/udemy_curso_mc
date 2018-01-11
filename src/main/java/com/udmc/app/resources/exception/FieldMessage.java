package com.udmc.app.resources.exception;

import java.io.Serializable;

public class FieldMessage implements Serializable {

	private static final long serialVersionUID = -5244787578197709209L;

	private String campo;
	
	private String mensagem;
	
	public FieldMessage() {
	}

	public FieldMessage(String campo, String mensagem) {
		super();
		this.setCampo(campo);
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

}