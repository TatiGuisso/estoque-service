package com.grupo16.estoqueservice.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Estoque {
	
	private Long id;
	private Long idProduto;
	private Long quantidade;

}
