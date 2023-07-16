package dao;

import model.Localizacao;

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
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return localizacao;
    }

}
