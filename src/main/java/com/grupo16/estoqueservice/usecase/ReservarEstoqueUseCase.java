package com.grupo16.estoqueservice.usecase;

import com.grupo16.estoqueservice.domain.Estoque;
import com.grupo16.estoqueservice.gateway.EstoqueRepositoryGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Bruno Gomes Damascena dos santos (bruno-gds) < brunog.damascena@gmail.com >
 * Date: 06/03/2024
 * Project Name: estoque-service
 */

@Service
@AllArgsConstructor
public class ReservarEstoqueUseCase {

    private EstoqueRepositoryGateway estoqueRepositoryGateway;


    public List<Estoque> reservar(List<Estoque> estoqueList) {
    	
    	//FIXME Aqui buscar o estoque dos produtos chamando OBTER 
    	/*
    	 * fazer aqui a regra para conferir estoque x reserva
    	 */
    	
    	
        return estoqueRepositoryGateway.reservar(estoqueList);
    }
}
