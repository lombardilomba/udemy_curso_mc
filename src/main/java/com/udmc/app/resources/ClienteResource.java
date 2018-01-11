package com.udmc.app.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.udmc.app.dto.ClienteDTO;
import com.udmc.app.dto.ClienteNewDTO;
import com.udmc.app.model.Cliente;
import com.udmc.app.service.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> listar(@PathVariable Long id) {
		return ResponseEntity.ok(service.find(id));
	}
	
	/**
	 * Cria categoria e retorna a URI da categoria que foi inserida
	 * <b>@ResquestBody</b> transforma Json em Java
	 * @param categoria
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> inserir(@Valid @RequestBody ClienteNewDTO dto) {
		Cliente cliente = service.inserir(service.fromDTO(dto));
		URI uri = ServletUriComponentsBuilder
							.fromCurrentRequest()
							.path("/{id}")
							.buildAndExpand(cliente.getId())
							.toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody ClienteDTO dto, @PathVariable Long id) {
		Cliente cliente = service.fromDTO(dto);
		cliente.setId(id);
		cliente = service.update(cliente);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>> listarTodos() {
		List<Cliente> list = service.listarTodos();
		//JAVA 8
		List<ClienteDTO> listDTO = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<ClienteDTO>> listarTodos(
															@RequestParam(value="page", defaultValue="0") Integer pagina,
															@RequestParam(value="itemsLinha", defaultValue="24") Integer itemsPagina,
															@RequestParam(value="orderBy", defaultValue="nome") String orderBy,
															@RequestParam(value="direcao", defaultValue="ASC") String direcao
														 ) {
		Page<Cliente> list = service.buscaPagina(pagina, itemsPagina, orderBy, direcao);
		//JAVA 8
		Page<ClienteDTO> listDTO = list.map(obj -> new ClienteDTO(obj));
		return ResponseEntity.ok().body(listDTO);
	}

}