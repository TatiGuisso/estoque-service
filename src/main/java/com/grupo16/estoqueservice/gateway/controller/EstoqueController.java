package com.grupo16.estoqueservice.gateway.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grupo16.estoqueservice.domain.Estoque;
import com.grupo16.estoqueservice.gateway.controller.json.EstoqueJson;
import com.grupo16.estoqueservice.usecase.ObterEstoqueUseCase;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class EstoqueController {
	
	private ObterEstoqueUseCase obterEstoqueUseCase;
	
	public List<EstoqueJson> obter(
			@RequestParam(name = "idProduto")List<Long> idProdutos){
		log.trace("Start idProduto={}", idProdutos);
		
		List<Estoque> estoqueList = obterEstoqueUseCase.obter(idProdutos);
		List<EstoqueJson> estoqueListJson = estoqueList.stream().map(EstoqueJson::new).toList();
		
		log.trace("End estoquesJson={}", estoqueListJson);
		return estoqueListJson;
	}

}