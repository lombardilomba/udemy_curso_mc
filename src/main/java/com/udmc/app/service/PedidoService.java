package com.udmc.app.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udmc.app.model.ItemPedido;
import com.udmc.app.model.PagamentoComBoleto;
import com.udmc.app.model.Pedido;
import com.udmc.app.model.enums.EstadoPagamento;
import com.udmc.app.repository.ItemPedidoRepository;
import com.udmc.app.repository.PagamentoRepository;
import com.udmc.app.repository.PedidoRepository;
import com.udmc.app.repository.ProdutoRepository;
import com.udmc.app.service.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository dao;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	public Pedido find(Long objId) {
		Pedido obj = dao.findOne(objId);
		
		if(obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado id: " + objId + ", tipo " + Pedido.class.getName());
		}
		
		return obj;
	}

	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setData(new Date());
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		
		if(obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto boleto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.gerarDataVencimento(boleto, obj.getData());
		}
		
		obj = dao.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		
		for(ItemPedido item : obj.getItems()) {
			item.setDesconto(0D);
			item.setPreco(produtoRepository.findOne(item.getProduto().getId()).getPreco());
			item.setPedido(obj);
		}
		
		itemPedidoRepository.save(obj.getItems());
		
		return null;
	}
	
}