package config;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class InicializacaoDados {

    private final DataSource dataSource;
    /*
        Tentativa de ler arquivo sql
        Connection connection = conexaoDAO.getConexao();
        SimpleDataSource dataSource = new SimpleDataSource();
        dataSource.setConnectTimeout(5);
        InicializacaoDados inicializacaoDados = new InicializacaoDados(dataSource);
        inicializacaoDados.initializeDatabase();
        */

    public InicializacaoDados(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void initializeDatabase() {
        System.out.println("Entrando");
        try (Connection connection = dataSource.getConnection()) {
            System.out.println("Entrando2");
            // Drop e criação da tabela
            executeSqlScript(connection, "schema.sql");

            // População da tabela
            executeSqlScript(connection, "data.sql");
        } catch (SQLException e) {
            System.out.println("Erro ao inicializar o banco de dados: " + e.getMessage());
        }
    }

    private void executeSqlScript(Connection connection, String scriptPath) {
        try (InputStream inputStream = getClass().getResourceAsStream(scriptPath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
             Statement statement = connection.createStatement()) {

            StringBuilder scriptContent = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                scriptContent.append(line);
                scriptContent.append("\n");
            }

            String[] statements = scriptContent.toString().split(";");

            for (String sql : statements) {
                if (!sql.trim().isEmpty()) {
                    statement.execute(sql);
                }
            }
        } catch (IOException | SQLException e) {
            System.out.println("Erro ao executar o script SQL: " + e.getMessage());
        }
    }

}
