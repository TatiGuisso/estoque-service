package com.grupo16.estoqueservice.exception;

import lombok.Getter;

@Getter
public class ReservaInsuficienteException extends SystemBaseException {
	private static final long serialVersionUID = -7985327274925749395L;
	
	private final String code = "estoque.reservaInsuficiente";//NOSONAR
	private final String message = "Reserva insuficiente.";//NOSONAR
	private final Integer httpStatus = 500;//NOSONAR
}
