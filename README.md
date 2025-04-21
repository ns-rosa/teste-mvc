# Projeto de teste de API Rest

Este é um projeto simples desenvolvido em Java no padrão MVC utilizando o Spring Boot. O objetivo é demonstrar a estrutura básica de uma API com separação de responsabilidades entre camadas.

## Estrutura do Projeto

- **handler/**: Contém um exemplo simples de handler global para tratamento de exceptions.
- **model/**: Contém a classe de Produto com getters e setters, e outros modelos relacionados à tratativa de erros (modelos e métodos para mensagens de erro e uma classe de exceção personalizada).
- **repository/**: Contém a classe responsável pela interação com o banco de dados.
- **services/**: Contém a lógica de negócios. É o intermediário entre o Controller e o Repository
- **shared/**: Diretório para recursos compartilhados entre diferentes partes da aplicação. Adicionei aqui uma classe que serve como DTO, para ser utilizada na camada de Services da aplicação.
- **view/**: Diretório destinado para componentes relacionados à interface ou visualização. Tendo em vista que se trata de um teste de API, aproveitei para adicionar o Controller com os métodos de requisição aqui dentro. Além disso, para evitar ficar acionando diretamente a classe Produto, criei os modelos ProdutoRequest e ProdutoResponse para padronizar o que deve ser recebido e retornado ao usuário final.  

## Tecnologias utilizadas

- **Java 17**
- **Maven**
- **Spring Boot**
- **MySQL**
