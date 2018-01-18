package com.udmc.app.service.validations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.udmc.app.dto.ClienteDTO;
import com.udmc.app.model.Cliente;
import com.udmc.app.repository.ClienteRepository;
import com.udmc.app.resources.exception.FieldMessage;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	private HttpServletRequest request;
	
	@Override
	public void initialize(ClienteUpdate arg0) {
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean isValid(ClienteDTO dto, ConstraintValidatorContext context) {
		
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Long uriId = Long.parseLong(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<FieldMessage>();
		
		Cliente aux = repository.findByEmail(dto.getEmail());
		if ( aux != null && aux.getId().equals(uriId) == false) {
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