package com.grupo16.estoqueservice.exception;

import lombok.Getter;

@Getter
public class EstoqueInsuficienteException extends SystemBaseException {
	private static final long serialVersionUID = -5080503656342392591L;
	
	private final String code = "estoque.estoqueIndisponivel";//NOSONAR
	private final String message = "Não há estoque para o produto selecionado.";//NOSONAR
	private final Integer httpStatus = 422;//NOSONAR
}
