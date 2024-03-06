package com.grupo16.estoqueservice.gateway.controller.json;

import com.grupo16.estoqueservice.domain.Estoque;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EstoqueJson {
	
	private Long id;
	private Long idProduto;
	private Long quantidade;
	
	public EstoqueJson(Estoque estoque) {
		id = estoque.getId();
		idProduto = estoque.getIdProduto();
		quantidade = estoque.getQuantidade();
	}

	public Estoque mapperToDomain() {
		return Estoque.builder()
				.id(id)
				.idProduto(idProduto)
				.quantidade(quantidade)
				.build();
	}
}
