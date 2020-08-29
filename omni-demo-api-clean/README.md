# Departamento API

Implementação de uma API para cadastro, alteração, listagem e remoção de departamentos.

# Arquitetura

A arquitetura utilizada nesse projeto foi baseada na `Clean Architecture` porém simplificada, utilizando apenas duas camadas.

- `core` - Representa o domínio do negócio, toda a lógica e regras de negócios ficam aqui. Essa camada fica totalmente isolada de frameworks e tecnologias utilizadas pela aplicação
- `application` - Nessa camada está tudo aquilo que não faz parte do domínio. API's Rest, frameworks, acesso a banco de dados e etc.

A implementação dessa arquitetura foi baseada em dois principais artigos: 

* [Descomplicando a Clean Architecture](https://medium.com/luizalabs/descomplicando-a-clean-architecture-cf4dfc4a1ac6)
* [Clean Architecture Example with Java and Spring Boot](https://medium.com/swlh/clean-architecture-java-spring-fea51e26e00)


O projeto é um projeto maven multi-module com dois módulos que representam as camadas propostas:

- `omni-demo-core` - Java 11
- `omni-demo-application` - Aplicação Springboot

# Tecnologias

- `Java/OpenJDK 11` - JDK 11
- `Spring boot` - Framework utilizado para desenvolver a API 
- `Postgresql` - Base de dados relacional
- `Flyway` - Utilizado para versionar e realizar as migrações automáticas da base de dados
- `Swagger` - Documentação da API
- `Junit e Mockito` - Para testes de unidade


