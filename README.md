# 📚 Sistema de Cadastro de Livros

## 📝 Descrição do Projeto

Este projeto é um sistema simples de cadastro e gerenciamento de informações sobre livros. Desenvolvido em Java, ele serve como uma base para aprender e demonstrar conceitos de Programação Orientada a Objetos (POO), manipulação de dados e interações básicas com o usuário.

O objetivo principal é permitir o registro, consulta e, potencialmente, a atualização e exclusão de dados de livros, simulando um pequeno catálogo.

## ✨ Funcionalidades

* **Cadastro de Livros:** Permite adicionar novos livros ao sistema, registrando informações como título, autor, ISBN, ano de publicação, etc.

* **Listagem de Livros:** Exibe todos os livros cadastrados de forma organizada.

* **Busca de Livros:** Capacidade de encontrar livros específicos por critérios (ex: título, autor).

* **Validação Básica:** Implementa validações simples para os dados de entrada dos livros.

## 🚀 Tecnologias Utilizadas

* **Linguagem:** Java

* **Estrutura:** Programação Orientada a Objetos (POO)

## 📂 Estrutura do Projeto

O projeto segue uma estrutura de pacotes clara para separar as responsabilidades:
```
CadastrarLivros/
├── src/
│   └── main/
│       └── java/
│           └── application/           # Contém a classe principal de execução
│               └── Main.java
│           └── model/                 # Contém as classes de modelo de dados
│               └── LivroDAO.java      # Representa um livro no sistema   
└── README.md                          # Este arquivo
```
## ⚙️ Como Rodar o Projeto

Para executar o sistema de cadastro de livros em sua máquina local:

1.  **Clone o Repositório:**

    ```bash
    git clone [https://github.com/Gtvnv/CadastrarLivros.git](https://github.com/Gtvnv/CadastrarLivros.git)
    cd CadastrarLivros
    ```

2.  **Compile e Execute (Via IDE - Eclipse/IntelliJ IDEA):**

    * Importe o projeto para sua IDE favorita como um projeto Java existente.

    * Localize a classe `Main.java` (no pacote `application`).

    * Execute o método `main` desta classe.

3.  **Compile e Execute (Via Linha de Comando):**

    * Certifique-se de ter o Java Development Kit (JDK) 8 ou superior instalado.

    * Navegue até a pasta `src/main/java` dentro do projeto clonado.

    * Compile as classes Java:

        ```bash
        javac application/*.java model/*.java
        ```

    * Execute a aplicação:

        ```bash
        java application.Main # Para console applications
        # ou, se o classpath for complexo:
        # java -cp . application.Main
        ```

## 📈 Melhorias Futuras

* Implementar uma interface gráfica (GUI) mais robusta (ex: JavaFX).

* Persistência de dados em banco de dados (SQL ou NoSQL) em vez de memória/arquivo simples.

* Implementar testes unitários para a lógica de negócio.

* Exportar/Importar dados em diferentes formatos (CSV, JSON).

* Criação de usuários com hierarquia (ex: administrador, supervisor, usuário padrão).

## ✉️ Contato

Para dúvidas ou sugestões, sinta-se à vontade para entrar em contato:

* **Seu Nome:** [Gustavo Ventura]
* **GitHub:** [https://github.com/Gtvnv]
* **LinkedIn:** [https://www.linkedin.com/in/gtvnv]
* **Email:** [gutsman1235@gmail.com]
