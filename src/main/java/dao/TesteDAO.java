package dao;

import model.Rebelde;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TesteDAO {

    ConexaoDAO conexaoDAO;

    public TesteDAO(ConexaoDAO conexaoDAO) {
        this.conexaoDAO = conexaoDAO;
    }


}
