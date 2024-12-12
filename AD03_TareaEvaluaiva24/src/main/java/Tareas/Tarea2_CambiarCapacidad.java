package Tareas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Tareas.Consola;

public class Tarea2_CambiarCapacidad {

	public static void main(String[] args) {
		
		 String url = "jdbc:mysql://localhost:3306/dbeventos";
	     String user = "dbeventos";
	     String password = "dbeventos";
	     
	     
	     try (Connection con = DriverManager.getConnection(url, user, password)){    	 
	         	
	    	 String ubi = getUbicacion();
	    	 getCapacidad(con, ubi);

	    	 cambiarCapacidad(con, ubi);
	    	 
	    	 con.close();	

			} catch (SQLException e) {
		        System.err.println("Error al conectarse o trabajar con la base de datos: " + e.getMessage());
		    }
	       
		 }
	
	private static String getUbicacion() {
		System.out.println("Introduce el nombre de la ubicación:");
        return Consola.readString();
	}
	
	private static int getCapacidadNueva() {
		System.out.println("Introduce la nueva capacidad máxima:");
        return Consola.readInt();
	}
	
	private static int getCapacidad(Connection con, String nombreUbicacion) throws SQLException{
		String sql1 = "Select capacidad from ubicaciones where nombre = ?";
		try(PreparedStatement st2= con.prepareStatement(sql1)){
			st2.setString(1, nombreUbicacion);
			
			try (ResultSet rs2 = st2.executeQuery()){
				if(rs2.next()) {
					int capacidad = rs2.getInt("capacidad");
					System.out.println("La capacidad actual de la ubicación '" + nombreUbicacion + "' es: " + capacidad);
					return capacidad;
				} else {
					System.out.println("La ubicación no existe elija otra");
				}
			}
		}
		return 0;
	}
	
	private static void cambiarCapacidad(Connection con, String nombreUbicacion) throws SQLException {
		
		int capNueva = getCapacidadNueva();
		String sql2 = "UPDATE ubicaciones SET capacidad = ? WHERE nombre= ?";
		
		try(PreparedStatement st3= con.prepareStatement(sql2)){
			st3.setInt(1, capNueva);
			st3.setString(2, nombreUbicacion);
			st3.executeUpdate();
			System.out.println("Capacidad actualizada correctamente");
			}
	}
		
	
}

