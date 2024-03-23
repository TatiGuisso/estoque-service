<a name="readme-top"></a>

# Introdução

O Estoque Service é um sistema de gestão de estoque que permite o gerenciamento e controle de seus produtos, suas devidas quantidades e sua disponibilidade.


## Sumário
* [Instruções](#instruções)
* [Funcionalidades de Estoque Service](#funcionalidades-de-estoque-service)
* [Tecnologias](#-tecnologias)
* [Desafios](#-desafios)


## Instruções

- Maven: Para build do projeto. **Para buildar:** mvn clean install
- Foi utilizado Lombok, Validation e MySql, portanto é necessário adicionar os plugins na IDE
- Antes de iniciar a instância dos Microserviços, é necessário garantir que os seguintes serviços estejam operacionais para garantir a operação adequada:</br>

	* **Service Discovery** - Inicie o Service Discovery. Execute e verifique se pelo menos uma instância do Service Discovery está operacional.</br></br>
	
	* **API Gateway** - Inicie o API Gateway. Verifique se pelo menos uma instância do API Gateway está em execução.

<p align="right">(<a href="#readme-top">Ir ao topo</a>)</p>

---------

### Funcionalidades de Estoque Service

>[ Base URL: http://localhost:porta ] 

`Substitua <porta> pela porta atribuída dinamicamente pelo ambiente.`

### ``GET``
`*Para obter a lista de proutos em estoque pelo seus ID's`

```
	/estoques/{idsProdutos}
```

<details>
  <summary>Exemplo Request:</summary>

```
curl --location 'http://localhost:36339/estoques?idsProdutos=1%2C2'
```
</details>

<details>
  <summary>Exemplo Responses:</summary>

200 - _OK_
`- Será retornada a lista de proutos em estoque de acordo com os ID's passados`

```
[
    {
        "id": 1,
        "idProduto": 1,
        "quantidade": 10
    },
    {
        "id": 2,
        "idProduto": 2,
        "quantidade": 5
    }
]
```

</details>

<p align="right">(<a href="#readme-top">Ir ao topo</a>)</p>

### ``POST``
`*Para atualizar um produto`

```
	/estoques/atualizar
```

<details>
  <summary>Exemplo Request:</summary>

```
curl --location 'http://localhost:36339/estoques/atualizar' \
--header 'Content-Type: application/json' \
--data '{
    "idProduto": 1,
    "quantidade": 20
}'
```
</details>

<details>
  <summary>Exemplo Responses:</summary>

200 - _OK_
`- Será retornado o ID do produto alterado ou criado (caso ele não exista)`

```
1
```
</details>

<p align="right">(<a href="#readme-top">Ir ao topo</a>)</p>

### ``PUT``
`*Para reservar um produto`

```
	/estoques/reserva
```

<details>
  <summary>Exemplo Request:</summary>

```
curl --location --request PUT 'http://localhost:46693/estoques/reserva' \
--header 'Content-Type: application/json' \
--data '[
    {
        "idProduto": 1,
        "quantidade": 10
    },
    {
        "idProduto": 3,
        "quantidade": 1
    }
]'
```
</details>

<details>
  <summary>Exemplo Responses:</summary>

200 - _OK_
`- Os produtos foram reservados com sucesso`

```
[
    {
        "id": 1,
        "idProduto": 1,
        "quantidade": 9
    },
    {
        "id": 3,
        "idProduto": 3,
        "quantidade": 19
    }
]
```

422 - _Unprocessable Entity_

```
{
    "code": "estoque.reservaInsuficiente",
    "message": "Reserva insuficiente."
}
```
</details>

<p align="right">(<a href="#readme-top">Ir ao topo</a>)</p>

### ``PUT``
`*Para efetuar a baixa dos produtos`

```
	/estoques/efetuar-baixas
```

<details>
  <summary>Exemplo Request:</summary>

```
curl --location --request PUT 'http://localhost:42189/estoques/efetuar-baixas' \
--header 'Content-Type: application/json' \
--data '[
    {
        "idProduto": 1,
        "quantidade": 1
    },
    {
        "idProduto": 2,
        "quantidade": 2
    }
]'
```
</details>

<details>
  <summary>Exemplo Responses:</summary>

200 - _OK_
`- Baixa do produto relizada com sucesso`

422 - _Unprocessable Entity_

```
{
    "code": "estoque.reservaInsuficiente",
    "message": "Reserva insuficiente."
}
```
</details>

<p align="right">(<a href="#readme-top">Ir ao topo</a>)</p>

### ``PUT``
`*Para cancelar a reserva dos produtos`

```
	/estoques/cancelar-reservas
```

<details>
  <summary>Exemplo Request:</summary>

```
curl --location --request PUT 'http://localhost:42189/estoques/cancelar-reservas' \
--header 'Content-Type: application/json' \
--data '[
    {
        "idProduto": 1,
        "quantidade": 1
    },
    {
        "idProduto": 2,
        "quantidade": 1
    }
]'
```
</details>

<details>
  <summary>Exemplo Responses:</summary>

200 - _OK_
`- Reserva do produto cancelada com sucesso`

</details>

<p align="right">(<a href="#readme-top">Ir ao topo</a>)</p>

---------

<a name="tecnologias"></a>
## 📍️ Tecnologias

- A API foi construída em Java 18 utilizando Spring Framework 3.2.3
- Padrão REST na construção das rotas e retornos
- SLF4J para registro de logs
- Utilização de código limpo e princípios **SOLID**
- Boas práticas da Linguagem/Framework
- Clean architecture
- Banco de Dados MySql
- Para facilitar a comunicação entre microserviços, o projeto utiliza o Spring Cloud Feign. 
- Service Discovery
- API Gateway

<p align="right">(<a href="#readme-top">Ir ao topo</a>)</p>

---------

<a name="desafios"></a>
## 📍️ Desafios

A arquitetura do Spring Cloud como um todo, alinhado com o uso de Microserviços, nos obrigou a utilizar diversos patterns para que no mundo cloud tudo se conecta-se corretamente e claro, juntamente com as boas práticas, funcionassem perfeitamente como o esperado.

<p align="right">(<a href="#readme-top">Ir ao topo</a>)</p>