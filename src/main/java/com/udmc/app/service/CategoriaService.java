package com.udmc.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.udmc.app.dto.CategoriaDTO;
import com.udmc.app.model.Categoria;
import com.udmc.app.repository.CategoriaRepository;
import com.udmc.app.service.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository dao;
	
	public Categoria find(Long objId) {
		Categoria obj = dao.findOne(objId);
		
		if(obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado id: " + objId + ", tipo " + Categoria.class.getName());
		}
		
		return obj;
	}
	
	public Categoria inserir(Categoria categoriaNew) {
		categoriaNew.setId(null);
		return dao.save(categoriaNew);
	}

	public Categoria update(Categoria obj) {
		Categoria categoria = find(obj.getId());
		updateDataObj(categoria, obj);
		return dao.save(categoria);
	}

	private void updateDataObj(Categoria categoria, Categoria obj) {
		categoria.setNome(obj.getNome());
	}

	public void delete(Long id) {
		find(id);
		try {
			dao.delete(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Não é possível excluir uma categoria com Produto(s)");
		}
	}

	public List<Categoria> listarTodos() {
		return dao.findAll();
	}
	
	public Page<Categoria> buscaPagina(Integer pagina, Integer itemsPagina, String orderBy, String direcao) {
		PageRequest pageRequest = new PageRequest(pagina, itemsPagina, Direction.valueOf(direcao), orderBy);
		return dao.findAll(pageRequest);
	}
	
	public Categoria fromDTO(CategoriaDTO dto) {
		return new Categoria(dto.getId(), dto.getNome());
	}
	
}