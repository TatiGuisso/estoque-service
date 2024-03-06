package com.grupo16.estoqueservice.gateway.repository.mysql;

import java.util.List;

import org.springframework.stereotype.Component;

import com.grupo16.estoqueservice.domain.Estoque;
import com.grupo16.estoqueservice.gateway.EstoqueRepositoryGateway;

@Component
public class EstoqueMySqlGateway implements EstoqueRepositoryGateway{

	@Override
	public List<Estoque> obter(List<Long> idProdutos) {
		// TODO Auto-generated method stub
		return null;
	}

}
