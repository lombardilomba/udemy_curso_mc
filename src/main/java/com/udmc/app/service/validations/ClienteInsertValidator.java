package com.udmc.app.service.validations;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.udmc.app.dto.ClienteNewDTO;
import com.udmc.app.model.Cliente;
import com.udmc.app.model.enums.TipoCliente;
import com.udmc.app.repository.ClienteRepository;
import com.udmc.app.resources.exception.FieldMessage;
import com.udmc.app.service.validations.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

	@Autowired
	private ClienteRepository repository;
	
	@Override
	public void initialize(ClienteInsert arg0) {
	}

	@Override
	public boolean isValid(ClienteNewDTO dto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<FieldMessage>();
		
		if(dto.getTipo().equals(TipoCliente.PESSOAFISICA.getCodigo()) && BR.isValidCPF(dto.getCpfOuCnpj()) == false) {
			list.add(new FieldMessage("cpfOuCnpj"," CPF inválido"));
		}
		
		if(dto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCodigo()) && BR.isValidCPF(dto.getCpfOuCnpj()) == false) {
			list.add(new FieldMessage("cpfOuCnpj"," CNPJ inválido"));
		}
		
		Cliente aux = repository.findByEmail(dto.getEmail());
		if (aux != null) {
			list.add(new FieldMessage("email"," Email já existe"));
		}
		
		for(FieldMessage error : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(error.getMensagem())
				.addPropertyNode(error.getCampo())
				.addConstraintViolation();
		}
		
		return list.isEmpty();
	}

	
}