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
@AllArgsConstructor
@Slf4j
public class ReservarEstoqueUseCase {

	private EstoqueRepositoryGateway estoqueRepositoryGateway;


	public List<Estoque> reservar(List<Estoque> estoqueList) {

		List<Long> idProdutos = estoqueList.stream().mapToLong(Estoque::getIdProduto).boxed().toList();
		List<Estoque> estoqueFound = estoqueRepositoryGateway.obter(idProdutos);

		estoqueFound.forEach(ef -> {
			for (Estoque estoque : estoqueList) {
				if(ef.getIdProduto().equals(estoque.getIdProduto())) {
					if (ef.getQuantidadeDisponivel() < estoque.getQuantidade()) {
						log.warn("Reserva insuficiente, idProduto={}",estoque.getIdProduto());
						throw new ReservaInsuficienteException();
					}
					break;
				}
			}
		});

		return estoqueRepositoryGateway.reservar(estoqueList);
	}
}
