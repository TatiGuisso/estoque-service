package com.grupo16.estoqueservice.gateway.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grupo16.estoqueservice.gateway.repository.jpa.entity.EstoqueEntity;

public interface EstoqueRepository extends JpaRepository<EstoqueEntity, Long> {

}
