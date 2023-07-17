package dao;

import model.Localizacao;
import model.Rebelde;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LocalizacaoDAO {

    ConexaoDAO conexaoDAO;

    public LocalizacaoDAO(ConexaoDAO conexaoDAO) {

        this.conexaoDAO = conexaoDAO;

    }
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
    // Adiciona localização no banco de dados
    public void adicionarLocalizacao(String ip) {


        if (conexaoDAO.getConexao() != null) {

            String sql = "INSERT INTO localizacao (ip) VALUES (?)";
            PreparedStatement statement = null;
            try {

                statement = conexaoDAO.getConexao().prepareStatement(sql);

                statement.setString(1, ip);

                statement.executeUpdate();

                System.out.println("Localização " + ip + " adicionada com sucesso!");

            } catch (SQLException e) {

                e.getMessage();

                e.printStackTrace();

            }

        }
    }

    // Método busca a localização por ip e devolve seu id
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
                    System.out.println("IP não encontrado.");
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

    public void adicionarLocalizacaoParaRebelde(String ip) {

        Long id = buscaIdDaLocalizacao(ip);

        Rebelde rebelde = new Rebelde();
        Boolean status = true;

        if (conexaoDAO.getConexao() != null) {

            String sql = "INSERT INTO rebelde (id_localizacao) VALUES (?)";

            PreparedStatement statement = null;

            try {

                statement = conexaoDAO.getConexao().prepareStatement(sql);

                statement.setLong(1, id);

                statement.executeUpdate();

                System.out.println("Ip " + ip + " adicionado com sucesso!");

            } catch (SQLException e) {

                e.getMessage();

                e.printStackTrace();

            }

        }
    }


}
