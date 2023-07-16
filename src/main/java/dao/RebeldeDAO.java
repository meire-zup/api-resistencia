package dao;

import model.Localizacao;
import model.Rebelde;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RebeldeDAO {

    ConexaoDAO conexaoDAO;
    LocalizacaoDAO localizacaoDAO;


    public RebeldeDAO(ConexaoDAO conexaoDAO, LocalizacaoDAO localizacaoDAO) {

        this.conexaoDAO = conexaoDAO;
        this.localizacaoDAO = localizacaoDAO;

    }

    public void adicionarRebelde(String nome, String genero, Integer idade) {

        Rebelde rebelde = new Rebelde();

        if (conexaoDAO.getConexao() != null) {

            String sql = "INSERT INTO rebelde (nome, genero, idade) VALUES (?, ?, ?)";
            PreparedStatement statement = null;
            try {

                statement = conexaoDAO.getConexao().prepareStatement(sql);

                statement.setString(1, nome);
                statement.setString(2, genero);
                statement.setInt(3, idade);
                statement.executeUpdate();

                System.out.println("Rebelde " + nome + " adicionado(a) com sucesso!");

            } catch (SQLException e) {

                e.getMessage();

                e.printStackTrace();

            }

        }
    }

    public Rebelde buscarRebeldePorNome(String nome) {
        Rebelde rebelde = null;

        if (conexaoDAO.getConexao() != null) {
            String sql = "SELECT * FROM rebelde WHERE nome = ?";
            try (PreparedStatement statement = conexaoDAO.getConexao().prepareStatement(sql)) {
                statement.setString(1, nome);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        rebelde = new Rebelde();
                        rebelde.setId(resultSet.getLong("id"));
                        rebelde.setNome(resultSet.getString("nome"));
                        rebelde.setGenero(resultSet.getString("genero"));
                        rebelde.setStatus(resultSet.getBoolean("status"));
                        // Obtém as informações da localização
                        //long localizacaoId = resultSet.getLong("localizacao_id");
                        //String localizacao = resultSet.getString("ip");
                        //Localizacao localizacaoObj = buscarLocalizacaoPorIp(localizacao);
                        //Localizacao localizacaoObj = localizacaoDAO.buscarLocalizacaoPorIp(localizacaoId);

                        //rebelde.setLocalizacao(localizacaoObj);

                        rebelde.setIdade(resultSet.getInt("idade"));

                    } else {

                        throw new RuntimeException("Rebelde não encontrado no banco de dados.");

                    }
                }
            } catch (SQLException e) {

                throw new RuntimeException("Erro ao encontrar rebelde no banco de dados. " + e.getMessage());
            }
        }

        return rebelde;
    }

}
