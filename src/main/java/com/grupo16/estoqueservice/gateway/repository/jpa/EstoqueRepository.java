package com.grupo16.estoqueservice.gateway.repository.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grupo16.estoqueservice.gateway.repository.jpa.entity.EstoqueEntity;

public interface EstoqueRepository extends JpaRepository<EstoqueEntity, Long> {

	List<EstoqueEntity> findByIdProdutoIn(List<Long> idsProdutos);

	Optional<EstoqueEntity> findByIdProduto(Long idProduto);

}
