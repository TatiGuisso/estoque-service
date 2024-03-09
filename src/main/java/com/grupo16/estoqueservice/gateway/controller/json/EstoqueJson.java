package com.grupo16.estoqueservice.gateway.controller.json;

import com.grupo16.estoqueservice.domain.Estoque;

import lombok.*;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EstoqueJson {
	
	private Long id;
	private Long idProduto;
	private Long quantidade;
	
	public EstoqueJson(Estoque estoque) {
		id = estoque.getId();
		idProduto = estoque.getIdProduto();
		quantidade = estoque.getQuantidadeDisponivel(); 
	}

	public Estoque mapperToDomain() {
		return Estoque.builder()
				.id(id)
				.idProduto(idProduto)
				.quantidade(quantidade)
				.build();
	}
}
