package com.grupo16.estoqueservice.gateway.controller;

import java.util.List;

import com.grupo16.estoqueservice.usecase.BaixarEstoqueUseCase;
import com.grupo16.estoqueservice.usecase.CriarAlterarEstoqueUseCase;
import com.grupo16.estoqueservice.usecase.ReservarEstoqueUseCase;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.grupo16.estoqueservice.domain.Estoque;
import com.grupo16.estoqueservice.gateway.controller.json.EstoqueJson;
import com.grupo16.estoqueservice.usecase.ObterEstoqueUseCase;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("estoques")
public class EstoqueController {
	
	private ObterEstoqueUseCase obterEstoqueUseCase;

	private ReservarEstoqueUseCase reservarEstoqueUseCase;

	private CriarAlterarEstoqueUseCase criarAlterarEstoqueUseCase;
	
	private BaixarEstoqueUseCase baixarEstoqueUseCase;


	@GetMapping
	public List<EstoqueJson> obter(
			@RequestParam(name = "idsProdutos")List<Long> idsProdutos){
		log.trace("Start idsProdutos={}", idsProdutos);
		
		List<Estoque> estoqueList = obterEstoqueUseCase.obter(idsProdutos);
		List<EstoqueJson> estoqueListJson = estoqueList.stream().map(EstoqueJson::new).toList();
		
		log.trace("End estoquesJson={}", estoqueListJson);
		return estoqueListJson;
	}

	@PostMapping("atualizar")
	public Long atualizar(@RequestBody EstoqueJson estoqueJson) {
		log.trace("Start estoqueJson={}", estoqueJson);

		Estoque estoque = estoqueJson.mapperToDomain();
		Long id = criarAlterarEstoqueUseCase.atualizar(estoque);

		log.trace("End id={}", id);
		return id;
	}

	@PutMapping("reserva")
	public List<EstoqueJson> reservar(@RequestBody List<EstoqueJson> estoqueJsonList) {
		log.trace("Start estoqueJsonList={}", estoqueJsonList);

		List<Estoque> estoqueList = estoqueJsonList.stream().map(EstoqueJson::mapperToDomain).toList();
		List<EstoqueJson> newEstoqueJsonList = reservarEstoqueUseCase.reservar(estoqueList).stream().map(EstoqueJson::new).toList();

		log.trace("End newEstoqueJsonList={}", newEstoqueJsonList);
		return newEstoqueJsonList;
	}
	
	@PutMapping("efetuar-baixas")
	public void efetuarBaixas(@RequestBody List<EstoqueJson> estoqueJsonList) {
		log.trace("Start estoqueJsonList={}", estoqueJsonList);
		
		List<Estoque> estoqueList = estoqueJsonList.stream().map(EstoqueJson::mapperToDomain).toList();
		baixarEstoqueUseCase.baixar(estoqueList);
		
		log.trace("End");
	}
}
