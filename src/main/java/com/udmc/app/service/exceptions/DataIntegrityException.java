package com.udmc.app.service.exceptions;

public class DataIntegrityException extends RuntimeException {

	private static final long serialVersionUID = -5743195364259460931L;
	
	public DataIntegrityException(String mensagem) {
		super(mensagem);
	}
	
	public DataIntegrityException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}