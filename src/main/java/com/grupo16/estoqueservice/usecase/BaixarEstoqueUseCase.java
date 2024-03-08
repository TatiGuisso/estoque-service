package com.grupo16.estoqueservice.usecase;

import com.grupo16.estoqueservice.domain.Estoque;
import com.grupo16.estoqueservice.exception.ReservaInsuficienteException;
import com.grupo16.estoqueservice.gateway.EstoqueRepositoryGateway;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Bruno Gomes Damascena dos santos (bruno-gds) < brunog.damascena@gmail.com >
 * Date: 06/03/2024
 * Project Name: estoque-service
 */

@Service
@Slf4j
@AllArgsConstructor
public class BaixarEstoqueUseCase {

    private EstoqueRepositoryGateway estoqueRepositoryGateway;


    public void baixar(List<Estoque> estoqueListDesejada) {
    	
    	List<Long> produtoIds = estoqueListDesejada.stream().mapToLong(e -> e.getIdProduto()).boxed().toList();
    	
    	List<Estoque> estoques = estoqueRepositoryGateway.obter(produtoIds);
    	
    	verificaReservaDisponivelDandoBaixa(estoqueListDesejada, estoques);
    	
    	estoqueRepositoryGateway.salvarBaixa(estoques);
    }


	private void verificaReservaDisponivelDandoBaixa(List<Estoque> estoqueListDesejada, List<Estoque> estoques) {
		estoqueListDesejada.forEach(ed -> {
    		Estoque estoque = estoques.stream().filter(e -> e.getIdProduto().equals(ed.getIdProduto())).findAny().orElseThrow();
    		boolean contemReserva = estoque.contemReserva(ed.getQuantidade());
    		if(!contemReserva) {
    			log.warn("Reserva insuficuente. produtoId={}, desejado={}. disponivel={}", ed.getIdProduto(), ed.getQuantidade(), estoque.getReserva());
    			throw new ReservaInsuficienteException();
    		}
    		
    		estoque.efetuarBaixa(ed.getQuantidade());
    	});
	}
}
