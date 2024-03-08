package com.grupo16.estoqueservice.usecase;

import com.grupo16.estoqueservice.domain.Estoque;
import com.grupo16.estoqueservice.gateway.EstoqueRepositoryGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Bruno Gomes Damascena dos santos (bruno-gds) < brunog.damascena@gmail.com >
 * Date: 07/03/2024
 * Project Name: estoque-service
 */

@Service
@AllArgsConstructor
public class CriarAlterarEstoqueUseCase {

    private EstoqueRepositoryGateway estoqueRepositoryGateway;


    public Long atualizar(Estoque estoque) {
        return estoqueRepositoryGateway.atualizar(estoque);
    }
}
