package dao;


import model.Relatorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RelatorioDAO {

    ConexaoDAO conexaoDAO;

    public RelatorioDAO(ConexaoDAO conexaoDAO) {

        this.conexaoDAO = conexaoDAO;

    }

    // Método cria um objeto relatorio, seta o id e salva no banco de dados -  testado
    public Relatorio criarRelatorio() {
        Relatorio relatorio = null;
        long relatorioId = 0;

        if (conexaoDAO.getConexao() != null) {
            String sql = "INSERT INTO relatorio (quantidade_relatorio) VALUES (0) RETURNING id";
            try (PreparedStatement statement = conexaoDAO.getConexao().prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        relatorioId = resultSet.getLong("id");
                        relatorio = new Relatorio();
                        relatorio.setId(relatorioId);
                        relatorio.setQuantidadeRelatorio(0);
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return relatorio;
    }

    // Método denuncia traidor recebendo o nome do rebelde,
    // se chegar em 3 denúncias é mudado status do rebelde para false
    public void denunciarTraidor(String nomeRebelde) {

            Long idRelatorioRebelde = buscarIdRelatorioPorNomeRebelde(nomeRebelde);

            Integer quantidadeRelatorio = buscarQuantidadeRelatorioPorId(idRelatorioRebelde);

            if (quantidadeRelatorio >= 2) {

                atualizarStatusRebelde(nomeRebelde);

                System.out.println(nomeRebelde + "agora é um traidor.");

            } else {

                quantidadeRelatorio++;
                atualizarQuantidadeRelatorio(idRelatorioRebelde, quantidadeRelatorio);

            }
    }

    // Método busca id do relatorio de um rebelde - testado
    public Long buscarIdRelatorioPorNomeRebelde(String nomeRebelde) {

        Long relatorioId = null;

        if (conexaoDAO.getConexao() != null) {

            String sql = "SELECT relatorio_id FROM rebelde WHERE nome = ?";

            try (PreparedStatement statement = conexaoDAO.getConexao().prepareStatement(sql)) {

                statement.setString(1, nomeRebelde);

                try (ResultSet resultSet = statement.executeQuery()) {

                    if (resultSet.next()) {

                        relatorioId = resultSet.getLong("relatorio_id");

                    }
                }
            } catch (SQLException e) {

                e.printStackTrace();

            }
        }

        return relatorioId;

    }

    // Método busca a quantidade de relatorio que o rebelde possui
    private int buscarQuantidadeRelatorioPorId(Long idRelatorio) {
        int quantidadeRelatorio = 0;

        if (conexaoDAO.getConexao() != null) {
            String sql = "SELECT quantidade_relatorio FROM relatorio WHERE id = ?";
            try (PreparedStatement statement = conexaoDAO.getConexao().prepareStatement(sql)) {
                statement.setLong(1, idRelatorio);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        quantidadeRelatorio = resultSet.getInt("quantidade_relatorio");
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return quantidadeRelatorio;
    }

    // Método atualiza a quantidade de relatório do rebelde
    private void atualizarQuantidadeRelatorio(Long idRelatorio, int novaQuantidade) {
        if (conexaoDAO.getConexao() != null) {
            String sql = "UPDATE relatorio SET quantidade_relatorio = ? WHERE id = ?";
            try (PreparedStatement statement = conexaoDAO.getConexao().prepareStatement(sql)) {
                statement.setInt(1, novaQuantidade);
                statement.setLong(2, idRelatorio);
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // Método atualiza status do rebelde para traidor - false
    public void atualizarStatusRebelde(String nome) {
        Connection conexao = conexaoDAO.getConexao();

        if (conexao != null) {
            String sql = "UPDATE rebelde SET status = false WHERE nome = ?";

            try (PreparedStatement statement = conexao.prepareStatement(sql)) {
                statement.setString(1, nome);
                int linhasAfetadas = statement.executeUpdate();

                System.out.println("Linhas afetadas: " + linhasAfetadas);
            } catch (SQLException e) {
                System.out.println("Erro ao atualizar o status do rebelde: " + e.getMessage());
            }
        }
    }
}
