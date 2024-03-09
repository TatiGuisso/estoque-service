package com.grupo16.estoqueservice.gateway;

import java.util.List;

import com.grupo16.estoqueservice.domain.Estoque;

public interface EstoqueRepositoryGateway {

	List<Estoque> obter(List<Long> idProdutos);

	List<Estoque> reservar(List<Estoque> estoque);

	Long atualizar(Estoque estoque);

	void atualizarQuantidadeEReserva(List<Estoque> estoques);
}
