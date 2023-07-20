package dao;

import model.Localizacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LocalizacaoDAO {

    ConexaoDAO conexaoDAO;

    public LocalizacaoDAO(ConexaoDAO conexaoDAO) {

        this.conexaoDAO = conexaoDAO;

    }

    // Método busca localização no banco de dados passando o ip - testado
    public Localizacao buscarLocalizacaoPorIp(String ip) {

        Localizacao localizacao = null;

        if (conexaoDAO.getConexao() != null) {

            String sql = "SELECT * FROM localizacao WHERE ip = ?";

            try (PreparedStatement statement = conexaoDAO.getConexao().prepareStatement(sql)) {

                statement.setString(1, ip);

                try (ResultSet resultSet = statement.executeQuery()) {

                    if (resultSet.next()) {

                        localizacao = new Localizacao();
                        localizacao.setId(resultSet.getLong("id"));
                        localizacao.setIp(resultSet.getString("ip"));

                    } else {

                        throw new RuntimeException("Localização não encontrado no banco de dados.");

                    }
                }
            } catch (SQLException e) {

                throw new RuntimeException("Erro ao encontrar localização no banco de dados. " + e.getMessage());
            }
        }

        return localizacao;
    }

    // Adiciona no banco de dados e cria um objeto do tipo localizacao setando seus atributos - testado
    public Localizacao adicionarLocalizacao(String ip) {

        Localizacao localizacao = null;

        if (conexaoDAO.getConexao() != null) {

            String sql = "INSERT INTO localizacao (ip) VALUES (?) RETURNING id";

            try (PreparedStatement statement = conexaoDAO.getConexao().prepareStatement(sql)) {

                statement.setString(1, ip);

                try (ResultSet resultSet = statement.executeQuery()) {

                    if (resultSet.next()) {

                        long localizacaoId = resultSet.getLong("id");
                        localizacao = new Localizacao();
                        localizacao.setId(localizacaoId);
                        localizacao.setIp(ip);
                        System.out.println("Localização " + ip + " adicionada com sucesso!");

                    }
                }
            } catch (SQLException e) {

                throw new RuntimeException(e);
            }
        }

        return localizacao;
    }


    // Método busca a localização por ip e devolve seu id - testado
    public Long buscaIdDaLocalizacao(String ip) {

        Long id = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {

            connection = conexaoDAO.getConexao();

            if (connection != null) {

                String sql = "SELECT id FROM localizacao WHERE ip = ?";
                statement = connection.prepareStatement(sql);
                statement.setString(1, ip);
                resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    id = resultSet.getLong("id");
                    //System.out.println("ID encontrado: " + id);

                } else {

                    System.out.println("IP não encontrado no banco de dados.");

                }
            }
        } catch (SQLException e) {

            System.out.println("Erro ao buscar IP: " + e.getMessage());
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
