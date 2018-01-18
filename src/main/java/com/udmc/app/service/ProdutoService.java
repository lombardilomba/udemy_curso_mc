package com.udmc.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.udmc.app.model.Categoria;
import com.udmc.app.model.Produto;
import com.udmc.app.repository.CategoriaRepository;
import com.udmc.app.repository.ProdutoRepository;
import com.udmc.app.service.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository dao;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Produto find(Long objId) {
		Produto obj = dao.findOne(objId);
		
		if(obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado id: " + objId + ", tipo " + Produto.class.getName());
		}
		
		return obj;
	}
	
	public Page<Produto> search(String nome, List<Long> ids, Integer pagina, Integer itemsPagina, String orderBy, String direcao) {
		PageRequest pageRequest = new PageRequest(pagina, itemsPagina, Direction.valueOf(direcao), orderBy);
		List<Categoria> categorias = categoriaRepository.findAll(ids);
		return dao.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
	}
	
}