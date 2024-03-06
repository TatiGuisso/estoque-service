package com.grupo16.estoqueservice.gateway.repository.mysql;

import java.util.List;

import org.springframework.stereotype.Component;

import com.grupo16.estoqueservice.domain.Estoque;
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
		
		List<EstoqueEntity> estoqueEntityList = estoqueRepository.findAllById(idsProdutos);
		return estoqueEntityList.stream().map(EstoqueEntity::mapperToDomain).toList();
	}

}
