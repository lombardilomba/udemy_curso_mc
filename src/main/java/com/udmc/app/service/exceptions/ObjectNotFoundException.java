package com.udmc.app.service.exceptions;

public class ObjectNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -5743195364259460931L;
	
	public ObjectNotFoundException(String mensagem) {
		super(mensagem);
	}
	
	public ObjectNotFoundException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}