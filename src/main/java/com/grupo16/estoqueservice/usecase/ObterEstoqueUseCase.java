package com.grupo16.estoqueservice.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import com.grupo16.estoqueservice.domain.Estoque;
import com.grupo16.estoqueservice.gateway.EstoqueRepositoryGateway;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ObterEstoqueUseCase {
	
	private EstoqueRepositoryGateway estoqueRepositoryGateway;

	public List<Estoque> obter(List<Long> idsProdutos) {
		return estoqueRepositoryGateway.obter(idsProdutos);
	}

}
