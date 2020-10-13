## Tecnologias ##

- **Java**: OPENJDK_11
- **SPRING BOOT**: versão 2.3.3
- **Banco de dados**: PostgreSQL
- **Front**: React
- **Build tool**: Maven

## Aplicação a ser desenvolvida:

### Funcionalidades:

- Cadastrar departamentos
- Editar departamentos
- Deletar departamentos
- Listar departamentos pelo código, de forma ascendente

## Protótipo:

![](./img/cadastro-depto.jpg)

- Nessa arquitetura um `client` faz uma requisição `REST` para a `API` solicitando um pagamento, a `API` publica esse evento no `Redis`.
- Um `consumer` do grupo de `consumers` processará esse evento chamando a `API` da `ADIQ` e a resposta gerará um novo evento `payment_authorization` que será publicado no `Redis`. O `client` então poderá consumir esses eventos para obtrem a resposta da chamada (não implementado). O `consumer` do evento `solicitar pagamento` poderia armazenar essas informações em uma base de dados noSQL ou em uma base relacional (não implementado). 
