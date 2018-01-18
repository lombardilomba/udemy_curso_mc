package com.udmc.app.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.udmc.app.model.PagamentoComBoleto;

@Service
public class BoletoService {
	
	public void gerarDataVencimento(PagamentoComBoleto pagamento, Date dataPedido) {
		Calendar c = Calendar.getInstance();
		c.setTime(dataPedido);
		c.add(Calendar.DAY_OF_MONTH, 7);
		pagamento.setDataVencimento(c.getTime());
	}
	
}