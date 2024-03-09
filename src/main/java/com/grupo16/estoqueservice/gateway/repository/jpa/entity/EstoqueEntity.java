package com.grupo16.estoqueservice.gateway.repository.jpa.entity;

import com.grupo16.estoqueservice.domain.Estoque;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Estoque")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EstoqueEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long idProduto;
	
	@Setter
	private Long quantidade;
	
	@Setter
	private Long reserva;
	
	public EstoqueEntity(Estoque estoque) {
		id = estoque.getId();
		idProduto = estoque.getIdProduto();
		quantidade = estoque.getQuantidade();
		reserva = estoque.getReserva();
	}
	
	public Estoque mapperToDomain() {
		return Estoque.builder()
				.id(id)
				.idProduto(idProduto)
				.quantidade(quantidade)
				.reserva(reserva == null ? 0L : reserva)
				.build();
	}

}
