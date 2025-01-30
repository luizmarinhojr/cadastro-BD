package cadastrobd.model;

import cadastrobd.model.util.ConectorBD;
import cadastrobd.model.util.SequenceManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PessoaJuridicaDAO {

    private static final ConectorBD conector = new ConectorBD();

    public PessoaJuridica getPessoa(int id) {
        String sqlGetPessoa = "SELECT * FROM Pessoas p JOIN PessoasJuridicas pf ON p.pessoaID = pf.pessoaID WHERE p.pessoaID = ?";
        PessoaJuridica pessoaJuridica = null;
        Connection conexao = null;
        PreparedStatement declaracao = null;
        ResultSet resultado = null;

        try {
            conexao = conector.getConnection();
            declaracao = conector.getPrepared(conexao, sqlGetPessoa);
            declaracao.setInt(1, id);
            resultado = conector.getSelect(declaracao);
            if (resultado.next()) {
                pessoaJuridica = new PessoaJuridica(
                        resultado.getString("cnpj"),
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

        return pessoaJuridica;
    }

    public List<PessoaJuridica> getPessoas() {
        String sql = "SELECT * FROM Pessoas p JOIN PessoasJuridicas pf ON p.pessoaID = pf.pessoaID";
        List<PessoaJuridica> pessoasJuridicas = new ArrayList<>();
        Connection conexao = null;
        PreparedStatement declaracao = null;
        ResultSet resultado = null;

        try {
            conexao = conector.getConnection();
            declaracao = conector.getPrepared(conexao, sql);
            resultado = conector.getSelect(declaracao);
            while (resultado.next()) {
                pessoasJuridicas.add(new PessoaJuridica(
                        resultado.getString("cnpj"),
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
            System.out.println(e.getMessage());
        } finally {
            conector.close(conexao, declaracao, resultado);
        }

        return pessoasJuridicas;
    }

    public void incluir(PessoaJuridica pessoaJuridica) {
        String sqlPessoa = "INSERT INTO Pessoas (pessoaID, nome, logradouro, cidade, estado, telefone, email) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        String sqlPessoaJuridica = "INSERT INTO PessoasJuridicas (pessoaID, cnpj) " +
                "VALUES (?, ?)";

        int id = SequenceManager.getValue("Seq_PessoaID");
        Connection conexao = null;
        PreparedStatement declaracaoPessoa = null;
        PreparedStatement declaracaoPessoaJuridica = null;

        try {
            conexao = conector.getConnection();
            declaracaoPessoa = conector.getPrepared(conexao, sqlPessoa);
            declaracaoPessoaJuridica = conector.getPrepared(conexao, sqlPessoaJuridica);

            // INSERIR NA TABELA DE PESSOA
            declaracaoPessoa.setInt(1, id);
            declaracaoPessoa.setString(2, pessoaJuridica.getNome());
            declaracaoPessoa.setString(3, pessoaJuridica.getLogradouro());
            declaracaoPessoa.setString(4, pessoaJuridica.getCidade());
            declaracaoPessoa.setString(5, pessoaJuridica.getEstado());
            declaracaoPessoa.setString(6, pessoaJuridica.getTelefone());
            declaracaoPessoa.setString(7, pessoaJuridica.getEmail());
            declaracaoPessoa.executeUpdate();

            // INSERIR NA TABELA DE PESSOA FISICA
            declaracaoPessoaJuridica.setInt(1, id);
            declaracaoPessoaJuridica.setString(2, pessoaJuridica.getCnpj());
            declaracaoPessoaJuridica.executeUpdate();

            System.out.println("\nPessoa jurídica adicionada com sucesso!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            conector.close(conexao);
            conector.close(declaracaoPessoaJuridica);
            conector.close(declaracaoPessoa);
        }
    }

    public void alterar(PessoaJuridica pessoaJuridica) {
        String sqlPessoa = "UPDATE Pessoas SET nome = ?, logradouro = ?, cidade = ?, estado = ?, telefone = ?, email = ? " +
                "WHERE pessoaID = ?";
        String sqlPessoaJuridica = "UPDATE PessoasJuridicas SET cnpj = ? WHERE pessoaID = ?";

        Connection conexao = null;
        PreparedStatement declaracaoPessoa = null;
        PreparedStatement declaracaoPessoaJuridica = null;

        try {
            conexao = conector.getConnection();
            declaracaoPessoa = conector.getPrepared(conexao, sqlPessoa);
            declaracaoPessoaJuridica = conector.getPrepared(conexao, sqlPessoaJuridica);

            // ATUALIZAR A TABELA DE PESSOA
            declaracaoPessoa.setString(1, pessoaJuridica.getNome());
            declaracaoPessoa.setString(2, pessoaJuridica.getLogradouro());
            declaracaoPessoa.setString(3, pessoaJuridica.getCidade());
            declaracaoPessoa.setString(4, pessoaJuridica.getEstado());
            declaracaoPessoa.setString(5, pessoaJuridica.getTelefone());
            declaracaoPessoa.setString(6, pessoaJuridica.getEmail());
            declaracaoPessoa.setInt(7, pessoaJuridica.getId());
            declaracaoPessoa.executeUpdate();

            // ATUALIZAR A TABELA DE PESSOA FISICA
            declaracaoPessoaJuridica.setString(1, pessoaJuridica.getCnpj());
            declaracaoPessoaJuridica.setInt(2, pessoaJuridica.getId());
            declaracaoPessoaJuridica.executeUpdate();

            System.out.println("\nPessoa jurídica alterada com sucesso!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            conector.close(conexao);
            conector.close(declaracaoPessoaJuridica);
            conector.close(declaracaoPessoa);
        }
    }

    public void excluir(PessoaJuridica pessoaJuridica) {
        String sqlPessoaJuridica = "DELETE FROM PessoasJuridicas WHERE pessoaID = ?";
        String sqlPessoa = "DELETE FROM Pessoas WHERE pessoaID = ?";

        Connection conexao = null;
        PreparedStatement declaracaoPessoa = null;
        PreparedStatement declaracaoPessoaJuridica = null;

        try {
            conexao = conector.getConnection();
            declaracaoPessoaJuridica = conector.getPrepared(conexao, sqlPessoaJuridica);
            declaracaoPessoa = conector.getPrepared(conexao, sqlPessoa);

            // EXCLUIR UMA PESSOA FISICA DA TABELA
            declaracaoPessoaJuridica.setInt(1, pessoaJuridica.getId());
            declaracaoPessoaJuridica.executeUpdate();

            // EXCLUIR UMA PESSOA DA TABELA
            declaracaoPessoa.setInt(1, pessoaJuridica.getId());
            declaracaoPessoa.executeUpdate();

            System.out.println("\nPessoa jurídica excluida com sucesso!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            conector.close(conexao);
            conector.close(declaracaoPessoaJuridica);
            conector.close(declaracaoPessoa);
        }
    }
}

