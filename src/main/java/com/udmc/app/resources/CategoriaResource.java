package com.udmc.app.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.udmc.app.model.Categoria;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {

	@RequestMapping(method=RequestMethod.GET)
	public List<Categoria> listar() {
		List<Categoria> categoriaList = new ArrayList<Categoria>();
		categoriaList.add(new Categoria(1L, "INFORMATICA"));
		categoriaList.add(new Categoria(2L, "ESCRITORIO"));
		return categoriaList;
	}
	
}
