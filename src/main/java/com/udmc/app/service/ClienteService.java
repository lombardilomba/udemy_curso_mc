package com.udmc.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udmc.app.model.Cliente;
import com.udmc.app.repository.ClienteRepository;
import com.udmc.app.service.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository dao;
	
	public Cliente find(Long clienteId) {
		Cliente cliente = dao.findOne(clienteId);
		
		if(cliente == null) {
			throw new ObjectNotFoundException("Objeto não encontrado id: " + clienteId + ", tipo " + Cliente.class.getName());
		}
		
		return cliente;
	}
	
}