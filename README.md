# Sistema cadastral em Java com persistência de dados utilizando o Microsoft SQL Server

- Essa missão prática foi dividida em dois procedimentos. 
- O 1ª Procedimento envolve o Mapeamento Objeto-Relacional e DAO, enquanto que o 2ª Procedimento envolve Alimentar a Base. O desenvolvimento dos procedimentos estão organizados por branch. 
- O 1ª Procedimento está na branch PrimeiroProcedimento, e o 2ª Procedimento está na branch SegundoProcedimento.
- Essa branch **PrimeiroProcedimento** se refere aos dois procedimentos.

## Objetivos da prática
1. Implementar persistência com base no middleware JDBC.
2. Utilizar o padrão DAO (Data Access Object) no manuseio de dados.
3. Implementar o mapeamento objeto-relacional em sistemas Java.
4. Criar sistemas cadastrais com persistência em banco relacional.
5. No final do exercício, o aluno terá criado um aplicativo cadastral com uso do SQL Server na persistência de dados.

## Relatório discente de acompanhamento

O relatórios dos Procedimentos podem ser encontrados através desse próprio repositório, na branch específica do procedimento, ou através do link a seguir:
<br><br>
[https://github.com/luizmarinhojr/cadastro-BD/blob/main/relatorio-discente-de-acompanhamento.pdf](https://github.com/luizmarinhojr/cadastro-BD/blob/main/relatorio-discente-de-acompanhamento.pdf)
<br><br>

## Tecnologias utilizadas
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![NetBeans IDE](https://img.shields.io/badge/NetBeansIDE-1B6AC6.svg?style=for-the-badge&logo=apache-netbeans-ide&logoColor=white)
![MicrosoftSQLServer](https://img.shields.io/badge/Microsoft%20SQL%20Server-CC2927?style=for-the-badge&logo=microsoft%20sql%20server&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)

## Diagrama de classes
```mermaid
---
title: Cadastro POO
---

classDiagram
    direction TB
    class Pessoa
    class PessoaFisica
    class PessoaJuridica
    class PessoaFisicaDAO
    class PessoaJuridicaDAO
    class ConectorBD
    
    PessoaFisicaDAO --> PessoaFisica
    PessoaJuridicaDAO --> PessoaJuridica
    PessoaJuridicaDAO --> ConectorBD
    PessoaFisicaDAO --> ConectorBD
    Pessoa <|-- PessoaFisica
    Pessoa <|-- PessoaJuridica

    ConectorBD : -String URL
    ConectorBD : -String USER
    ConectorBD : -String PASSWORD
    ConectorBD : +getConnection() Connection
    ConectorBD : +getPrepared(conexao, sql) PreparedStatement
    ConectorBD : +getSelect(declaracao) ResultSet
    ConectorBD : +close(declaracao) void
    ConectorBD : +close(resultado) void
    ConectorBD : +close(conexao) void
    ConectorBD : +close(conexao, declaracao, resultado) void
    
    <<Abstract>> Pessoa
    Pessoa : -int id
    Pessoa : -String nome
    Pessoa : -String logradouro
    Pessoa : -String cidade
    Pessoa : -String estado
    Pessoa : -String telefone
    Pessoa : -String email
    Pessoa : +exibir()* String
    
    PessoaFisica : -String cpf
    PessoaFisica : -int idade
    PessoaFisica : +exibir() String
    
    PessoaJuridica : -String cnpj
    PessoaJuridica : +exibir() String
    
    PessoaFisicaDAO : -ConectorBD conector
    PessoaFisicaDAO : +getPessoa(id) PessoaFisica
    PessoaFisicaDAO : +getPessoas() List~PessoaFisica~
    PessoaFisicaDAO : +incluir(pessoaFisica) void
    PessoaFisicaDAO : +alterar(pessoaFisica) void
    PessoaFisicaDAO : +excluir(pessoaFisica) void
    
    PessoaJuridicaDAO : -ConectorBD conector
    PessoaJuridicaDAO : +getPessoa(id) PessoaJuridica
    PessoaJuridicaDAO : +getPessoas() List~PessoaJuridica~
    PessoaJuridicaDAO : +incluir(pessoaJuridica) void
    PessoaJuridicaDAO : +alterar(pessoaJuridica) void
    PessoaJuridicaDAO : +excluir(pessoaJuridica) void
```
<br>
<br>
