package dao;

import model.Inventario;
import model.Localizacao;
import model.Rebelde;
import model.Relatorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RebeldeDAO {

    ConexaoDAO conexaoDAO;
    LocalizacaoDAO localizacaoDAO;
    InventarioDAO inventarioDAO;

    RelatorioDAO relatorioDAO;


    public RebeldeDAO(ConexaoDAO conexaoDAO, LocalizacaoDAO localizacaoDAO,
                      InventarioDAO inventarioDAO, RelatorioDAO relatorioDAO) {

        this.conexaoDAO = conexaoDAO;
        this.localizacaoDAO = localizacaoDAO;
        this.inventarioDAO = inventarioDAO;
        this.relatorioDAO = relatorioDAO;

    }

    // Método verifica se rebelde existe no banco de dados - testado
    public boolean verificarSeRebeldeExiste(String nome) {

        boolean existe = false;

        if (conexaoDAO.getConexao() != null) {

            String sql = "SELECT id FROM rebelde WHERE nome = ? LIMIT 1";

            try (PreparedStatement statement = conexaoDAO.getConexao().prepareStatement(sql)) {

                statement.setString(1, nome);

                try {

                    ResultSet resultSet = statement.executeQuery();
                    existe = resultSet.next();


                } catch (SQLException e) {

                    throw new RuntimeException(e);

                }

            } catch (SQLException e) {

                throw new RuntimeException(e);

            }
        }
        return existe;

    }

    // Método cria rebelde, setando os atributos e salvando no banco de dados
    // Cria objeto rebelde, cria objeto inventario e objeto relatorio.
    // Adiciona a localização já salva no banco de dados - testado
    public Rebelde adicionarRebelde(String nome, String genero, Integer idade, String ip) {
        Rebelde rebelde = null;
        long rebeldeId = 0;
        boolean status = true;

        Localizacao localizacao = localizacaoDAO.adicionarLocalizacao(ip);
        Long idLocalizacao = localizacao.getId();

        Inventario inventario = inventarioDAO.criarInventario2();
        Long idInventario = inventario.getId();

        // Criar o relatório apenas uma vez
        Relatorio relatorio = relatorioDAO.criarRelatorio();
        Long idRelatorio = relatorio.getId();

        if (conexaoDAO.getConexao() != null) {
            String sql = "INSERT INTO rebelde (nome, genero, idade, status, localizacao_id, inventario_id, relatorio_id) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement statement = conexaoDAO.getConexao().prepareStatement(sql)) {
                statement.setString(1, nome);
                statement.setString(2, genero);
                statement.setInt(3, idade);
                statement.setBoolean(4, status);
                statement.setLong(5, idLocalizacao);
                statement.setLong(6, idInventario);
                statement.setLong(7, idRelatorio);

                // Não utiliza ResultSet para obter o ID do rebelde

                int linhasInseridas = statement.executeUpdate();
                if (linhasInseridas > 0) {
                    // Obtém o ID gerado automaticamente pelo banco de dados
                    try (ResultSet resultSet = statement.getGeneratedKeys()) {
                        if (resultSet.next()) {
                            rebeldeId = resultSet.getLong(1);
                        }
                    }

                    rebelde = new Rebelde();
                    rebelde.setLocalizacao(localizacao);
                    rebelde.setInventario(inventario);
                    rebelde.setRelatorio(relatorio);
                    rebelde.setNome(nome);
                    rebelde.setGenero(genero);
                    rebelde.setIdade(idade);
                    rebelde.setStatus(status);
                    rebelde.getLocalizacao().setId(idLocalizacao);
                    rebelde.getLocalizacao().setIp(ip);
                    rebelde.getInventario().setId(idInventario);
                    rebelde.getRelatorio().setId(idRelatorio);
                    rebelde.setId(rebeldeId);

                    System.out.println("Rebelde " + rebelde.getNome() + " adicionado(a) com sucesso!");

                }
            } catch (SQLException e) {

                e.printStackTrace();
                System.out.println("Erro ao adicionar rebelde!");
                throw new RuntimeException(e);

            }
        }
        return rebelde;
    }


    // Método adiciona rebelde salva no banco de dados sem setar- testado
    public void adicionarRebelde4(String nome, String genero, Integer idade, String ip) {

        Rebelde rebelde = new Rebelde();

        Boolean status = true;

        Long idLocalizacao = localizacaoDAO.buscaIdDaLocalizacao(ip);

        Long idInventario = inventarioDAO.criarInventario();


        if (conexaoDAO.getConexao() != null) {

            String sql = "INSERT INTO rebelde (nome, genero, idade, status, " +
                    "localizacao_id, inventario_id) VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = null;

            try {

                statement = conexaoDAO.getConexao().prepareStatement(sql);

                statement.setString(1, nome);
                statement.setString(2, genero);
                statement.setInt(3, idade);
                statement.setBoolean(4, status);
                statement.setLong(5, idLocalizacao);
                statement.setLong(6, idInventario);


                statement.executeUpdate();

                System.out.println("Rebelde " + nome + " adicionado(a) com sucesso!");

            } catch (SQLException e) {

                e.getMessage();

                e.printStackTrace();

            }

        }
    }

    // Método busca um rebelde no banco de dados pelo nome e retorna esse cliente
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


    // Método busca o rebelde por nome e devolve seu id - testado
    public Long buscaIdDoRebelde(String nome) {

        Long id = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {

            connection = conexaoDAO.getConexao();

            if (connection != null) {

                String sql = "SELECT id FROM rebelde WHERE nome = ?";
                statement = connection.prepareStatement(sql);
                statement.setString(1, nome);
                resultSet = statement.executeQuery();

                if (resultSet.next()) {

                    id = resultSet.getLong("id");
                    System.out.println("ID encontrado: " + id);

                } else {

                    System.out.println("Nome não encontrado.");

                }
            }

        } catch (SQLException e) {

            System.out.println("Erro ao buscar nome: " + e.getMessage());
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

    // Método atualiza localização do rebelde - testado
    public void atualizarLocalizacaoRebelde(String ip, String nomeRebelde) {

        long localizacaoId = localizacaoDAO.buscaIdDaLocalizacao(ip);
        long rebeldeId = buscaIdDoRebelde(nomeRebelde);

        if (conexaoDAO.getConexao() != null) {

            String sql = "UPDATE rebelde SET localizacao_id = ? WHERE id = ?";

            try (PreparedStatement statement = conexaoDAO.getConexao().prepareStatement(sql)) {

                statement.setLong(1, localizacaoId);
                statement.setLong(2, rebeldeId);
                statement.executeUpdate();
                System.out.println("Localização do rebelde " + nomeRebelde + " atualizada com sucesso!");

            } catch (SQLException e) {

                throw new RuntimeException(e);

            }
        }
    }
}
