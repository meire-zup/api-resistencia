package dao;

import model.Delator;
import model.Inventario;
import model.Produto;
import model.Relatorio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DelatorDAO {
    ConexaoDAO conexaoDAO;
    RebeldeDAO rebeldeDAO;

    public DelatorDAO(ConexaoDAO conexaoDAO, RebeldeDAO rebeldeDAO) {

        this.conexaoDAO = conexaoDAO;
        this.rebeldeDAO = rebeldeDAO;

    }

    // Método para verificar se já existe uma denúncia do delator para o rebelde

    public boolean existeDenuncia (String delator, String rebelde) {
        Boolean existe = false;

        Long idDelator = rebeldeDAO.buscaIdDoRebelde(delator);

        Long idRebelde = rebeldeDAO.buscaIdDoRebelde(rebelde);

        if (conexaoDAO.getConexao() != null) {

            String sql = "SELECT * FROM rebelde_delator WHERE delator_id = ? AND rebelde_id = ?";

            try {

                PreparedStatement statement = conexaoDAO.getConexao().prepareStatement(sql);

                statement.setLong(1, idDelator);
                statement.setLong(2, idRebelde);

               try (ResultSet resultSet = statement.executeQuery()) {

                   existe = resultSet.next();

               }

            } catch (SQLException e) {

                throw new RuntimeException(e);

            }

        }

        return existe;
    }

    // Método atualiza tabela delator com o nome do rebelde que ele delatou
    public void atualizarDelator(Long idDelator, Long idRebelde) {

        if(conexaoDAO.getConexao() != null) {

            String sql = "UPDATE delator SET rebelde_id = ? WHERE id = ?";
            try {

                PreparedStatement statement = conexaoDAO.getConexao().prepareStatement(sql);
                statement.setLong(1, idRebelde);
                statement.setLong(2, idDelator);
                int linhasAtualizadas = statement.executeUpdate();

                if(linhasAtualizadas > 0) {

                    System.out.println("Atualização realizada com sucesso!");

                } else {

                    System.out.println("Erro ao atualizar 1.");

                }


            } catch (SQLException e) {

                System.out.println("Erro ao atualizar 2!");
                e.getMessage();
                e.printStackTrace();

                //throw new RuntimeException(e);

            }

        }

    }

    // Método atualiza tabela delator - rebelde
    public void atualizarDelatorRebelde(Long idDelator, Long idRebelde) {


        if (idDelator != null && idRebelde != null && conexaoDAO.getConexao() != null) {

            String sql = "INSERT INTO rebelde_delator (delator_id, rebelde_id) VALUES (?, ?)";

            try (PreparedStatement statement = conexaoDAO.getConexao().prepareStatement(sql)) {

                statement.setLong(1, idDelator);
                statement.setLong(2, idRebelde);
                statement.executeUpdate();

            } catch (SQLException e) {

                e.printStackTrace();

            }
        }
    }


    // Método cria um objeto delator, seta o id e nome e salva no banco de dados -  testado
    public Delator criarDelator(String nome) {
        Delator delator = null;
        String sql = "INSERT INTO delator (nome) VALUES (?) RETURNING id";

        if (conexaoDAO.getConexao() != null) {
            try (PreparedStatement statement = conexaoDAO.getConexao().prepareStatement(sql)) {
                statement.setString(1, nome);

                try (ResultSet resultSet = statement.executeQuery()) {

                    if (resultSet.next()) {
                        long delatorId = resultSet.getLong("id");
                        delator = new Delator();
                        delator.setId(delatorId);
                        delator.setNome(nome);
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return delator;
    }
}
