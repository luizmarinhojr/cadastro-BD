package cadastrobd.model.util;

import java.sql.*;


public class ConectorBD {

    private static final String URL = "jdbc:sqlserver://172.17.0.2:1433;databaseName=Loja;encrypt=true;trustServerCertificate=true;";
    private static final String USER = "loja";
    private static final String PASSWORD = "loja";

    public Connection getConnection() throws SQLException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public PreparedStatement getPrepared(Connection conexao, String sql) throws SQLException {
        return conexao.prepareStatement(sql);
    }

    public ResultSet getSelect(PreparedStatement declaracao) throws SQLException {
        return declaracao.executeQuery();
    }

    public void close(PreparedStatement declaracao) {
        if (declaracao != null) {
            try {
                declaracao.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void close(ResultSet resultado) {
        if (resultado != null) {
            try {
                resultado.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void close(Connection conexao) {
        if (conexao != null) {
            try {
                conexao.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void close(Connection conexao, PreparedStatement declaracao, ResultSet resultado) {
        close(resultado);
        close(declaracao);
        close(conexao);
    }
}
