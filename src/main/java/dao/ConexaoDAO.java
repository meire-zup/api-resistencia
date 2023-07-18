package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDAO {

    // Método faz a conexão com o banco de dados
    public Connection getConexao(){

        Connection conexao = null;

        try{

            conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/api2",
                    "postgres", "puc@2015");

            if (conexao != null) {

                System.out.println("Conexão realizada com sucesso");

            }

            return conexao;

        }catch (SQLException e){

            e.printStackTrace();

            return null;

        }
    }
}
