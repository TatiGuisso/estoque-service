package com.grupo16.estoqueservice.gateway;

import java.util.List;

import com.grupo16.estoqueservice.domain.Estoque;

public interface EstoqueRepositoryGateway {

	List<Estoque> obter(List<Long> idProdutos);

}
