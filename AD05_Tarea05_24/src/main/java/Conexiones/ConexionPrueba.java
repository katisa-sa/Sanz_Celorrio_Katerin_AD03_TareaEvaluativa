package Conexiones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionPrueba {

	public static void main(String[] args) throws SQLException {
		try(Connection conn = DriverManager.getConnection("jdbc:hsqldb:mem")){
			System.out.println("conn.isValid(0) = " + conn.isValid(0));
		} 
	}

}