package cadastrobd.model;

import cadastrobd.model.util.ConectorBD;
import cadastrobd.model.util.SequenceManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PessoaFisicaDAO {

    private static final ConectorBD conector = new ConectorBD();

    public PessoaFisica getPessoa(int id) {
        String sqlGetPessoa = "SELECT * FROM Pessoas p JOIN PessoasFisicas pf ON p.pessoaID = pf.pessoaID WHERE p.pessoaID = ?";
        PessoaFisica pessoaFisica = null;
        Connection conexao = null;
        PreparedStatement declaracao = null;
        ResultSet resultado = null;

        try {
            conexao = conector.getConnection();
            declaracao = conector.getPrepared(conexao, sqlGetPessoa);
            declaracao.setInt(1, id);
            resultado = conector.getSelect(declaracao);
            if (resultado.next()) {
                pessoaFisica = new PessoaFisica(
                    resultado.getString("cpf"),
                    resultado.getInt("pessoaID"),
                    resultado.getString("nome"),
                    resultado.getString("logradouro"),
                    resultado.getString("cidade"),
                    resultado.getString("estado"),
                    resultado.getString("telefone"),
                    resultado.getString("email")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            conector.close(conexao, declaracao, resultado);
        }

        return pessoaFisica;
    }

    public List<PessoaFisica> getPessoas() {
        String sql = "SELECT * FROM Pessoas p JOIN PessoasFisicas pf ON p.pessoaID = pf.pessoaID";
        List<PessoaFisica> pessoasFisicas = new ArrayList<>();
        Connection conexao = null;
        PreparedStatement declaracao = null;
        ResultSet resultado = null;

        try {
            conexao = conector.getConnection();
            declaracao = conector.getPrepared(conexao, sql);
            resultado = conector.getSelect(declaracao);
            while (resultado.next()) {
                pessoasFisicas.add(new PessoaFisica(
                        resultado.getString("cpf"),
                        resultado.getInt("pessoaID"),
                        resultado.getString("nome"),
                        resultado.getString("logradouro"),
                        resultado.getString("cidade"),
                        resultado.getString("estado"),
                        resultado.getString("telefone"),
                        resultado.getString("email")
                ));
            }
        } catch (SQLException e) {
            System.out.println("ERRO" + e.getMessage());
        } finally {
            conector.close(conexao, declaracao, resultado);
        }

        return pessoasFisicas;
    }

    public void incluir(PessoaFisica pessoaFisica) {
        String sqlPessoa = "INSERT INTO Pessoas (pessoaID, nome, logradouro, cidade, estado, telefone, email) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        String sqlPessoaFisica = "INSERT INTO PessoasFisicas (pessoaID, cpf) " +
                "VALUES (?, ?)";

        int id = SequenceManager.getValue("Seq_PessoaID");
        Connection conexao = null;
        PreparedStatement declaracaoPessoa = null;
        PreparedStatement declaracaoPessoaFisica = null;

        try {
            conexao = conector.getConnection();
            declaracaoPessoa = conector.getPrepared(conexao, sqlPessoa);
            declaracaoPessoaFisica = conector.getPrepared(conexao, sqlPessoaFisica);

            // INSERIR NA TABELA DE PESSOA
            declaracaoPessoa.setInt(1, id);
            declaracaoPessoa.setString(2, pessoaFisica.getNome());
            declaracaoPessoa.setString(3, pessoaFisica.getLogradouro());
            declaracaoPessoa.setString(4, pessoaFisica.getCidade());
            declaracaoPessoa.setString(5, pessoaFisica.getEstado());
            declaracaoPessoa.setString(6, pessoaFisica.getTelefone());
            declaracaoPessoa.setString(7, pessoaFisica.getEmail());
            declaracaoPessoa.executeUpdate();

            // INSERIR NA TABELA DE PESSOA FISICA
            declaracaoPessoaFisica.setInt(1, id);
            declaracaoPessoaFisica.setString(2, pessoaFisica.getCpf());
            declaracaoPessoaFisica.executeUpdate();

            System.out.println("\nPessoa física adicionada com sucesso!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            conector.close(conexao);
            conector.close(declaracaoPessoaFisica);
            conector.close(declaracaoPessoa);
        }
    }

    public void alterar(PessoaFisica pessoaFisica) {
        String sqlPessoa = "UPDATE Pessoas SET nome = ?, logradouro = ?, cidade = ?, estado = ?, telefone = ?, email = ? " +
                "WHERE pessoaID = ?";
        String sqlPessoaFisica = "UPDATE PessoasFisicas SET cpf = ? WHERE pessoaID = ?";

        Connection conexao = null;
        PreparedStatement declaracaoPessoa = null;
        PreparedStatement declaracaoPessoaFisica = null;

        try {
            conexao = conector.getConnection();
            declaracaoPessoa = conector.getPrepared(conexao, sqlPessoa);
            declaracaoPessoaFisica = conector.getPrepared(conexao, sqlPessoaFisica);

            // ATUALIZAR A TABELA DE PESSOA
            declaracaoPessoa.setString(1, pessoaFisica.getNome());
            declaracaoPessoa.setString(2, pessoaFisica.getLogradouro());
            declaracaoPessoa.setString(3, pessoaFisica.getCidade());
            declaracaoPessoa.setString(4, pessoaFisica.getEstado());
            declaracaoPessoa.setString(5, pessoaFisica.getTelefone());
            declaracaoPessoa.setString(6, pessoaFisica.getEmail());
            declaracaoPessoa.setInt(7, pessoaFisica.getId());
            declaracaoPessoa.executeUpdate();

            // ATUALIZAR A TABELA DE PESSOA FISICA
            declaracaoPessoaFisica.setString(1, pessoaFisica.getCpf());
            declaracaoPessoaFisica.setInt(2, pessoaFisica.getId());
            declaracaoPessoaFisica.executeUpdate();

            System.out.println("\nPessoa física alterada com sucesso!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            conector.close(conexao);
            conector.close(declaracaoPessoaFisica);
            conector.close(declaracaoPessoa);
        }
    }

    public void excluir(PessoaFisica pessoaFisica) {
        String sqlPessoaFisica = "DELETE FROM PessoasFisicas WHERE pessoaID = ?";
        String sqlPessoa = "DELETE FROM Pessoas WHERE pessoaID = ?";

        Connection conexao = null;
        PreparedStatement declaracaoPessoa = null;
        PreparedStatement declaracaoPessoaFisica = null;

        try {
            conexao = conector.getConnection();
            declaracaoPessoaFisica = conector.getPrepared(conexao, sqlPessoaFisica);
            declaracaoPessoa = conector.getPrepared(conexao, sqlPessoa);

            // EXCLUIR UMA PESSOA FISICA DA TABELA
            declaracaoPessoaFisica.setInt(1, pessoaFisica.getId());
            declaracaoPessoaFisica.executeUpdate();

            // EXCLUIR UMA PESSOA DA TABELA
            declaracaoPessoa.setInt(1, pessoaFisica.getId());
            declaracaoPessoa.executeUpdate();

            System.out.println("\nPessoa física excluída com sucesso!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            conector.close(conexao);
            conector.close(declaracaoPessoaFisica);
            conector.close(declaracaoPessoa);
        }
    }
}
