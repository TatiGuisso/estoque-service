package com.grupo16.estoqueservice.gateway.repository.mysql;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.grupo16.estoqueservice.domain.Estoque;
import com.grupo16.estoqueservice.exception.EstoqueInsuficienteException;
import com.grupo16.estoqueservice.gateway.EstoqueRepositoryGateway;
import com.grupo16.estoqueservice.gateway.repository.jpa.EstoqueRepository;
import com.grupo16.estoqueservice.gateway.repository.jpa.entity.EstoqueEntity;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class EstoqueMySqlGateway implements EstoqueRepositoryGateway{
	
	private EstoqueRepository estoqueRepository;

	@Override
	public List<Estoque> obter(List<Long> idsProdutos) {
		
		List<EstoqueEntity> estoqueEntityList = estoqueRepository.findByIdProdutoIn(idsProdutos);
		return estoqueEntityList.stream().map(EstoqueEntity::mapperToDomain).toList();
	}

	@Override
	public List<Estoque> reservar(List<Estoque> estoque) {

		List<EstoqueEntity> estoqueEntityList = estoque.stream().map(EstoqueEntity::new).toList();

		estoqueEntityList.forEach(e -> {
			Optional<EstoqueEntity> exists = estoqueRepository.findByIdProduto(e.getIdProduto());

			if(!exists.isEmpty()) {
				EstoqueEntity entity = exists.get();

				EstoqueEntity newEntity = EstoqueEntity.builder()
						.id(entity.getId())
						.idProduto(entity.getIdProduto())
						.quantidade(entity.getQuantidade())
						.reserva(e.getQuantidade() + entity.getReserva())
						.build();
				
				if(newEntity.getReserva() > newEntity.getQuantidade()) {
					throw new EstoqueInsuficienteException();
				}
				
				estoqueRepository.save(newEntity);
			}
		});

		return estoqueEntityList.stream().map(EstoqueEntity::mapperToDomain).toList();
	}

}
