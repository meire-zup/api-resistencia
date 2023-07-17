package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ValidacaoDAO {

    ConexaoDAO conexaoDAO;

    public ValidacaoDAO(ConexaoDAO conexaoDAO) {

        this.conexaoDAO = conexaoDAO;
    }

    public Long buscaIdDoRebelde(String nome) {
        Long id = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = conexaoDAO.getConexao();
            if (connection != null) {
                String sql = "SELECT id FROM rebelde WHERE nome = ?";
                statement = connection.prepareStatement(sql);
                statement.setString(1, nome);
                resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    id = resultSet.getLong("id");
                    System.out.println("ID encontrado: " + id);
                } else {
                    System.out.println("Nome não encontrado.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar nome: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
                e.printStackTrace();
            }
        }

        return id;
    }


}
