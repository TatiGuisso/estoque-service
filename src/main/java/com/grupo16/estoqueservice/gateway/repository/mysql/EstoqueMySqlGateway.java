package com.grupo16.estoqueservice.gateway.repository.mysql;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.grupo16.estoqueservice.domain.Estoque;
import com.grupo16.estoqueservice.gateway.EstoqueRepositoryGateway;
import com.grupo16.estoqueservice.gateway.repository.jpa.EstoqueRepository;
import com.grupo16.estoqueservice.gateway.repository.jpa.entity.EstoqueEntity;

import jakarta.transaction.Transactional;
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
						.reserva(e.getQuantidade())
						.build();
				estoqueRepository.save(newEntity);
			}
		});

		return estoqueEntityList.stream().map(EstoqueEntity::mapperToDomain).toList();
	}

	@Override
	public Long atualizar(Estoque estoque) {

		Optional<EstoqueEntity> exists = estoqueRepository.findByIdProduto(estoque.getIdProduto());

		if (!exists.isEmpty()) {
			EstoqueEntity entity = exists.get();

			EstoqueEntity newEntityRefresh = EstoqueEntity.builder()
					.id(entity.getId())
					.idProduto(entity.getIdProduto())
					.quantidade(estoque.getQuantidade())
					.reserva(entity.getReserva())
					.build();
			estoqueRepository.save(newEntityRefresh);

			return newEntityRefresh.getId();
		}

		EstoqueEntity newEntity = EstoqueEntity.builder()
				.idProduto(estoque.getIdProduto())
				.quantidade(estoque.getQuantidade())
				.reserva(0L)
				.build();
		estoqueRepository.save(newEntity);

		return newEntity.getId();
	}

	@Override
	@Transactional
	public void atualizarQuantidadeEReserva(List<Estoque> estoques) {
		
		estoques.forEach(e -> {
			EstoqueEntity estoqueEntity = estoqueRepository.findById(e.getId()).orElseThrow();
			estoqueEntity.setQuantidade(e.getQuantidade());
			estoqueEntity.setReserva(e.getReserva());
			
			estoqueRepository.save(estoqueEntity);
		});
	}

}
