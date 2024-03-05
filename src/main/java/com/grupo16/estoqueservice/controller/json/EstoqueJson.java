package com.grupo16.estoqueservice.controller.json;

import com.grupo16.estoqueservice.domain.Estoque;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class EstoqueJson {
	
	private Long id;
	private Long idProduto;
	private Long quantidade;
	
	public EstoqueJson(Estoque estoque) {
		id = estoque.getId();
		idProduto = estoque.getIdProduto();
		quantidade = estoque.getQuantidade();
	}
}
