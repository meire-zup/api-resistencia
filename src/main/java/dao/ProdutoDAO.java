package dao;

import model.Inventario;
import model.Produto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProdutoDAO {

    ConexaoDAO conexaoDAO;

    public ProdutoDAO(ConexaoDAO conexaoDAO) {

        this.conexaoDAO = conexaoDAO;

    }

    // Método busca produto pelo nome - testado
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

    // Método cria um objeto produto, seta os atributos e salva no banco de dados - testado
    public Produto criarProduto(String nome, Double valor) {

        Produto produto = null;
        long produtoId = 0;

        if (conexaoDAO.getConexao() != null) {

            String sql = "INSERT INTO produto (nome, valor) VALUES (?, ?) RETURNING id";

            try (PreparedStatement statement = conexaoDAO.getConexao().prepareStatement(sql)) {

                statement.setString(1, nome);
                statement.setDouble(2, valor);

                try (ResultSet resultSet = statement.executeQuery()) {

                    if (resultSet.next()) {

                        produtoId = resultSet.getLong("id");
                        produto = new Produto();
                        produto.setId(produtoId);
                        produto.setNome(nome);
                        produto.setValor(valor);

                    }
                }
            } catch (SQLException e) {

                throw new RuntimeException(e);
            }
        }

        return produto;
    }

}


