package com.udmc.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.udmc.app.model.PagamentoComBoleto;
import com.udmc.app.model.PagamentoComCartao;

@Configuration
public class JacksonConfig {

	@Bean
	public Jackson2ObjectMapperBuilder objectMapperBuilder( ) {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
			public void configure(ObjectMapper mapper) {
				mapper.registerSubtypes(PagamentoComCartao.class);
				mapper.registerSubtypes(PagamentoComBoleto.class);
				super.configure(mapper);
			};
		};
		return builder;
	}
	
}
