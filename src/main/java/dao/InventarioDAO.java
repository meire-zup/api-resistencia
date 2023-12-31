package dao;

import model.Inventario;
import model.Produto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InventarioDAO {

    private ConexaoDAO conexaoDAO;
    private ProdutoDAO produtoDAO;

    public InventarioDAO(ConexaoDAO conexaoDAO, ProdutoDAO produtoDAO) {

        this.conexaoDAO = conexaoDAO;
        this.produtoDAO = produtoDAO;

    }

    // Método cria um novo inventário - testado
    public long criarInventario() {
        long inventarioId = 0;

        if (conexaoDAO.getConexao() != null) {
            String sql = "INSERT INTO inventario DEFAULT VALUES RETURNING id";
            try (PreparedStatement statement = conexaoDAO.getConexao().prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        inventarioId = resultSet.getLong("id");
                        //System.out.println("Inventário criado com sucesso!");
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        Inventario inventario = new Inventario();
        inventario.setId(inventarioId);

        return inventarioId;
    }


    // Método adiciona produto no inventario recebendo o nome do produto e o nome do rebelde - testado
    public void adicionarProdutoNoInventario(String nomeRebelde, String nomeProduto) {

        Long inventarioId = buscarIdInventarioPorNomeRebelde(nomeRebelde);
        Produto produto = produtoDAO.buscarProdutoPorNome(nomeProduto);

        if (inventarioId != null && produto != null && conexaoDAO.getConexao() != null) {

            String sql = "INSERT INTO inventario_produto (inventario_id, produto_id) VALUES (?, ?)";

            try (PreparedStatement statement = conexaoDAO.getConexao().prepareStatement(sql)) {

                statement.setLong(1, inventarioId);
                statement.setLong(2, produto.getId());
                statement.executeUpdate();

                System.out.println("Produto " + produto + " adicionado aos recursos do rebelde " + nomeRebelde);

            } catch (SQLException e) {

                e.printStackTrace();

            }
        }
    }

    // Método busca id do inventario de um rebelde - testado
    public Long buscarIdInventarioPorNomeRebelde(String nomeRebelde) {

        Long inventarioId = null;

        if (conexaoDAO.getConexao() != null) {

            String sql = "SELECT inventario_id FROM rebelde WHERE nome = ?";

            try (PreparedStatement statement = conexaoDAO.getConexao().prepareStatement(sql)) {

                statement.setString(1, nomeRebelde);

                try (ResultSet resultSet = statement.executeQuery()) {

                    if (resultSet.next()) {

                        inventarioId = resultSet.getLong("inventario_id");

                    }
                }
            } catch (SQLException e) {

                e.printStackTrace();

            }
        }

        return inventarioId;

    }

    // Método cria um objeto inventario, seta o id e salva no banco de dados - testado
    public Inventario criarInventario2() {
        Inventario inventario = null;
        long inventarioId = 0;


        if (conexaoDAO.getConexao() != null) {
            String sql = "INSERT INTO inventario DEFAULT VALUES RETURNING id";
            try (PreparedStatement statement = conexaoDAO.getConexao().prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        inventarioId = resultSet.getLong("id");
                        inventario = new Inventario();
                        inventario.setId(inventarioId);
                        //System.out.println("Inventario  adicionada com sucesso!");
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return inventario;
    }

    public List<Produto> obterRecursosRebelde(String nome) {

        Long idInventario = buscarIdInventarioPorNomeRebelde(nome);

        List<Produto> produtos = new ArrayList<>();

        if (conexaoDAO.getConexao() != null) {

            String sql = "SELECT p.id, p.nome, p.valor " +
                    "FROM produto p " +
                    "INNER JOIN inventario_produto ip ON p.id = ip.produto_id " +
                    "WHERE ip.inventario_id = ?";

            try {

                PreparedStatement statement = conexaoDAO.getConexao().prepareStatement(sql);
                statement.setLong(1, idInventario);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    Long id = resultSet.getLong("id");
                    nome = resultSet.getString("nome");
                    double valor = resultSet.getDouble("valor");

                    Produto produto = new Produto(id, nome, valor);
                    produtos.add(produto);

                }

            } catch (SQLException e) {

                throw new RuntimeException(e);

            }
        }

        return produtos;

    }
}