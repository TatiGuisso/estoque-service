package com.grupo16.estoqueservice.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Estoque {
	
	private Long id;
	private Long idProduto;
	private Long quantidade;
	private Long reserva;

	public Long getQuantidadeDisponivel() {
		return quantidade - reserva;
	}
	
	public boolean contemReserva(Long quantidade) {
		return reserva >= quantidade;
	}

	public void efetuarBaixa(Long quantidadeADebitar) {
		quantidade = quantidade - quantidadeADebitar; 
		reserva = reserva - quantidadeADebitar; 
	}
}
