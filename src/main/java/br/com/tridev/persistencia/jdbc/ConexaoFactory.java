package br.com.tridev.persistencia.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {

	public static Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:postgresql://localhost:5432/fabricaweb","postgres","582200");
		} catch (SQLException e) {
			//Relaçando a exception 
			throw new RuntimeException(e);
		}
	}

}
