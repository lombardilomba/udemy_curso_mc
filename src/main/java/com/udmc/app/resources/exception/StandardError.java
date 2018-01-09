package com.udmc.app.resources.exception;

import java.io.Serializable;
import java.util.Date;

public class StandardError implements Serializable {

	private static final long serialVersionUID = -5244787578197709209L;

	private int status;
	
	private String mensagem;
	
	private Date data;
	
	public StandardError() {
	}

	public StandardError(int status, String mensagem, Date data) {
		super();
		this.status = status;
		this.mensagem = mensagem;
		this.data = data;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
}