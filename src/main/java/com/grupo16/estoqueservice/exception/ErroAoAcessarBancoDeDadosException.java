package com.grupo16.estoqueservice.exception;

import lombok.Getter;

@Getter
public class ErroAoAcessarBancoDeDadosException extends SystemBaseException {
	private static final long serialVersionUID = -2498069334501318941L;
	
	private final String code = "pagamento.erroAcessarDatabase";//NOSONAR
	private final String message = "Erro ao acessar o banco de dados.";//NOSONAR
	private final Integer httpStatus = 500;//NOSONAR
}
