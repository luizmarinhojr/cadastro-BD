package cadastrobd.model.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SequenceManager {

    private static final ConectorBD conector = new ConectorBD();

    //  Pega o próximo valor da chave primária
    public static int getValue(String nomeSequencia) {
        int valor = -1;
        Connection conexao = null;
        PreparedStatement declaracao = null;
        ResultSet resultado = null;

        try {
            conexao = conector.getConnection();
            declaracao = conector.getPrepared(conexao, String.format(
                    "SELECT NEXT VALUE FOR %s AS proximo_id;", nomeSequencia)
            );
            resultado = conector.getSelect(declaracao);
            if (resultado.next()) {
                valor = resultado.getInt("proximo_id");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            conector.close(conexao, declaracao, resultado);
        }

        return valor;

    }
}
