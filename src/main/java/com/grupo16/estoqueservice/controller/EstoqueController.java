package com.grupo16.estoqueservice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grupo16.estoqueservice.controller.json.EstoqueJson;
import com.grupo16.estoqueservice.domain.Estoque;
import com.grupo16.estoqueservice.usecase.ObterEstoqueUseCase;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class EstoqueController {
	
	private ObterEstoqueUseCase obterEstoqueUseCase;
	
	public List<EstoqueJson> obter(
			@RequestParam(name = "idProduto")List<Long> idProdutos){
		log.trace("Start idProduto={}", idProdutos);
		
		List<Estoque> estoques = obterEstoqueUseCase.obter(idProdutos);
		List<EstoqueJson> estoquesJson = estoques.stream().map(EstoqueJson::new).toList();
		
		log.trace("End estoquesJson={}", estoquesJson);
		return estoquesJson;
	}

}
