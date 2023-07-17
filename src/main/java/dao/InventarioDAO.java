package dao;

import model.Inventario;
import model.Produto;
import model.Rebelde;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class InventarioDAO {

        private ConexaoDAO conexaoDAO;

    public InventarioDAO(ConexaoDAO conexaoDAO) {

        this.conexaoDAO = conexaoDAO;

    }

    // Método cria um novo inventário testado
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

        return inventarioId;
    }

    // Método cria um inventário para um rebelde
    public void criarInventario(Rebelde rebelde, List<Produto> recursos) {
        if (rebelde.getInventario() == null) {
            Inventario inventario = new Inventario(rebelde, recursos);
            if (conexaoDAO.getConexao() != null) {
                String sql = "INSERT INTO inventario (rebelde_id) VALUES (?) RETURNING id";
                try (PreparedStatement statement = conexaoDAO.getConexao().prepareStatement(sql)) {
                    statement.setLong(1, rebelde.getId());
                    try (ResultSet resultSet = statement.executeQuery()) {
                        if (resultSet.next()) {
                            long inventarioId = resultSet.getLong("id");

                            // Cria uma nova instância de Inventario e define o ID
                            Inventario novoInventario = new Inventario();
                            novoInventario.setId(inventarioId);

                            // Atualiza o atributo inventario do rebelde
                            rebelde.setInventario(novoInventario);

                            // Atualizar o campo inventario_id na tabela rebelde
                            String updateSql = "UPDATE rebelde SET inventario_id = ? WHERE id = ?";
                            try (PreparedStatement updateStatement = conexaoDAO.getConexao().prepareStatement(updateSql)) {
                                updateStatement.setLong(1, inventarioId);
                                updateStatement.setLong(2, rebelde.getId());
                                updateStatement.executeUpdate();
                            }

                            System.out.println("Inventário do rebelde " + rebelde.getNome() + " criado com sucesso!");
                        }
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    // Método adiciona produtos no inventário
    public void adicionarProdutoNoInventario(Long id) {
        Inventario inventario = buscarInventarioPorId(id);

        if (conexaoDAO.getConexao() != null) {
            String sql = "INSERT INTO inventario_produto (inventario_id, produto_id) VALUES (?, ?)";
            try (PreparedStatement statement = conexaoDAO.getConexao().prepareStatement(sql)) {
                for (Produto produto : inventario.getRecursos()) {
                    statement.setLong(1, inventario.getId());
                    statement.setLong(2, produto.getId());
                    statement.executeUpdate();
                }
            } catch (SQLException e) {

                e.printStackTrace();

            }
        }

    }
    // Método busca inventário por id
    public Inventario buscarInventarioPorId(Long id) {
        Inventario inventario = null;

        if (conexaoDAO.getConexao() != null) {
            String sql = "SELECT * FROM inventario WHERE id = ?";
            try (PreparedStatement statement = conexaoDAO.getConexao().prepareStatement(sql)) {
                statement.setLong(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        inventario = new Inventario();
                        inventario.setId(resultSet.getLong("id"));
                        // Obtenha outras informações do inventário, se necessário
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return inventario;
    }

}
