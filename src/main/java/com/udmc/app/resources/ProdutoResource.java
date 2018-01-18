package com.udmc.app.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.udmc.app.dto.ProdutoDTO;
import com.udmc.app.model.Produto;
import com.udmc.app.resources.utils.URL;
import com.udmc.app.service.ProdutoService;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> listar(@PathVariable Long id) {
		return ResponseEntity.ok(service.find(id));
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> listarTodos(
															@RequestParam(value="nome", defaultValue="") String nome,
															@RequestParam(value="categorias", defaultValue="") String categorias,
															@RequestParam(value="page", defaultValue="0") Integer pagina,
															@RequestParam(value="itemsLinha", defaultValue="24") Integer itemsPagina,
															@RequestParam(value="orderBy", defaultValue="nome") String orderBy,
															@RequestParam(value="direcao", defaultValue="ASC") String direcao
														 ) {
		List<Long> ids = URL.decodeLongList(categorias);
		
		String nomeDecoded = URL.decodeParam(nome);
		
		Page<Produto> list = service.search(nomeDecoded, ids, pagina, itemsPagina, orderBy, direcao);
		Page<ProdutoDTO> listDTO = list.map(obj -> new ProdutoDTO(obj));
		return ResponseEntity.ok().body(listDTO);
	}
	
}