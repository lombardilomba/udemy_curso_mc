package com.udmc.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udmc.app.model.Categoria;
import com.udmc.app.repository.CategoriaRepository;
import com.udmc.app.service.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository dao;
	
	public Categoria find(Long categoriaId) {
		Categoria categoria = dao.findOne(categoriaId);
		
		if(categoria == null) {
			throw new ObjectNotFoundException("Objeto não encontrado id: " + categoriaId + ", tipo " + Categoria.class.getName());
		}
		
		return categoria;
	}
	
}