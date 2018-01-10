package com.udmc.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udmc.app.model.Pedido;
import com.udmc.app.repository.PedidoRepository;
import com.udmc.app.service.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository dao;
	
	public Pedido find(Long categoriaId) {
		Pedido categoria = dao.findOne(categoriaId);
		
		if(categoria == null) {
			throw new ObjectNotFoundException("Objeto não encontrado id: " + categoriaId + ", tipo " + Pedido.class.getName());
		}
		
		return categoria;
	}
	
}