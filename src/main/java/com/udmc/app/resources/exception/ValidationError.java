package com.udmc.app.resources.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ValidationError extends StandardError {

	private static final long serialVersionUID = -5244787578197709209L;

	private List<FieldMessage> erros = new ArrayList<FieldMessage>();

	public ValidationError() {
		super();
	}

	public ValidationError(int status, String mensagem, Date data) {
		super(status, mensagem, data);
	}

	public List<FieldMessage> getListErrors() {
		return erros;
	}

	public void addError(String campo, String mensagem) {
		this.erros.add(new FieldMessage(campo, mensagem));
	}
	
}