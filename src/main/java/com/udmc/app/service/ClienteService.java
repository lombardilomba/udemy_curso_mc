package com.udmc.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.udmc.app.dto.ClienteDTO;
import com.udmc.app.dto.ClienteNewDTO;
import com.udmc.app.model.Cidade;
import com.udmc.app.model.Cliente;
import com.udmc.app.model.Endereco;
import com.udmc.app.model.enums.TipoCliente;
import com.udmc.app.repository.CidadeRepository;
import com.udmc.app.repository.ClienteRepository;
import com.udmc.app.repository.EnderecoRepository;
import com.udmc.app.service.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository dao;
	
	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public Cliente find(Long clienteId) {
		Cliente cliente = dao.findOne(clienteId);
		
		if(cliente == null) {
			throw new ObjectNotFoundException("Objeto não encontrado id: " + clienteId + ", tipo " + Cliente.class.getName());
		}
		
		return cliente;
	}
	
	public Cliente inserir(Cliente clienteNew) {
		clienteNew.setId(null);
		clienteNew = dao.save(clienteNew);
		enderecoRepository.save(clienteNew.getEnderecos());
		return clienteNew;
	}
	
	public Cliente update(Cliente obj) {
		Cliente cliente = find(obj.getId());
		updateDataObj(cliente, obj);
		return dao.save(cliente);
	}

	private void updateDataObj(Cliente cliente, Cliente obj) {
		cliente.setNome(obj.getNome());
		cliente.setEmail(obj.getEmail());
	}

	public void delete(Long id) {
		find(id);
		try {
			dao.delete(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Não é possível excluir um cliente com Pedidos");
		}
	}

	public List<Cliente> listarTodos() {
		return dao.findAll();
	}
	
	public Page<Cliente> buscaPagina(Integer pagina, Integer itemsPagina, String orderBy, String direcao) {
		PageRequest pageRequest = new PageRequest(pagina, itemsPagina, Direction.valueOf(direcao), orderBy);
		return dao.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO dto) {
		return new Cliente(dto.getId(), dto.getNome(), dto.getEmail(), null, null);
	}

	public Cliente fromDTO(ClienteNewDTO dto) {
		Cliente cliente = new Cliente(null, dto.getNome(), dto.getEmail(), dto.getCpfOuCnpj(), TipoCliente.toEnum(dto.getTipo()));
		
		Cidade cidade = cidadeRepository.findOne(dto.getCidadeId());
		
		Endereco endereco = new Endereco(
										null, dto.getLogradouro(), dto.getNumero(), dto.getComplemento(), dto.getBairro(),
										dto.getCep(), cliente, cidade
				);
		
		cliente.getEnderecos().add(endereco);
		cliente.getTelefones().add(dto.getTelefone());
		
		if(dto.getTelefone2() != null)
			cliente.getTelefones().add(dto.getTelefone2());

		if(dto.getTelefone3() != null)
			cliente.getTelefones().add(dto.getTelefone3());

		return cliente;
	}
	
}