package com.grupo16.estoqueservice.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import com.grupo16.estoqueservice.domain.Estoque;
import com.grupo16.estoqueservice.gateway.EstoqueRepositoryGateway;

import lombok.AllArgsConstructor;

/**
 * @author Bruno Gomes Damascena dos santos (bruno-gds) < brunog.damascena@gmail.com >
 * Date: 06/03/2024
 * Project Name: estoque-service
 */

@Service
@AllArgsConstructor
public class CancelarReservaUseCase {

    private EstoqueRepositoryGateway estoqueRepositoryGateway;


    public void cancelar(List<Estoque> estoqueListCancelada) {
    	
    	List<Long> produtoIds = estoqueListCancelada.stream().mapToLong(e -> e.getIdProduto()).boxed().toList();
    	
    	List<Estoque> estoques = estoqueRepositoryGateway.obter(produtoIds);
    	
    	cancelarReservas(estoqueListCancelada, estoques);
    	
    	estoqueRepositoryGateway.atualizarQuantidadeEReserva(estoques);
    }


	private void cancelarReservas(List<Estoque> estoqueListDesejada, List<Estoque> estoques) {
		estoqueListDesejada.forEach(ed -> {
    		Estoque estoque = estoques.stream().filter(e -> e.getIdProduto().equals(ed.getIdProduto())).findAny().orElseThrow();
    		estoque.cancelarReserva(ed.getQuantidade());
    	});
	}
}
