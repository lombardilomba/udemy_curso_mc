package com.udmc.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udmc.app.model.Categoria;
import com.udmc.app.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository dao;
	
	public Categoria find(Long categoriaId) {
		return dao.findOne(categoriaId);
	}
	
}