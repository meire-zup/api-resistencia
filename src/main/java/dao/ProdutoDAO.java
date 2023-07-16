package dao;

import model.Produto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProdutoDAO {

    ConexaoDAO conexaoDAO;

    public ProdutoDAO(ConexaoDAO conexaoDAO) {

        this.conexaoDAO = conexaoDAO;

    }

    public Produto buscarProdutoPorNome(String nome) {
        Produto produto = null;

        if (conexaoDAO.getConexao() != null) {
            String sql = "SELECT * FROM produto WHERE nome = ?";
            try (PreparedStatement statement = conexaoDAO.getConexao().prepareStatement(sql)) {
                statement.setString(1, nome);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        produto = new Produto();
                        produto.setId(resultSet.getLong("id"));
                        produto.setNome(resultSet.getString("nome"));
                        produto.setValor(resultSet.getDouble("valor"));
                    } else {

                        throw new RuntimeException("Produto não encontrado no banco de dados.");

                    }
                }
            } catch (SQLException e) {

                throw new RuntimeException("Erro ao encontrar produto no banco de dados. " + e.getMessage());

            }
        }

        return produto;
    }
}
