package Tareas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Tarea1_Listado {
	
	public static void main(String[] args) {
		
		 String url = "jdbc:mysql://localhost:3306/dbeventos";
	     String user = "dbeventos";
	     String password = "dbeventos";
	     String sql = "SELECT e.nombre_evento, count(ae.dni) AS Asistencia, u.nombre, u.direccion\r\n"
	    		 	  + "FROM eventos e\r\n"
	    		      + "JOIN ubicaciones u on e.id_ubicacion = u.id_ubicacion\r\n"
	    		      + "left join asistentes_eventos ae on e.id_evento = ae.id_evento\r\n"
	    		      + "group by e.nombre_evento, u.nombre, u.direccion";
	     
	     
	     
	     
	     
	     try (Connection con = DriverManager.getConnection(url, user, password);
	    	  Statement st = con.createStatement(); 
	    	  ResultSet rs = st.executeQuery(sql)){
	    	 
	         	System.out.println(" Evento                         | Asistentes | Ubicación                           | Dirección              ");
	            System.out.println("--------------------------------|------------|-------------------------------------|------------------------");
	        	
	        	while (rs.next()) {
	        		String nombre_even = rs.getString("e.nombre_evento");
	        		int asistencia = rs.getInt("asistencia");
	        		String nombre_ubi = rs.getString("u.nombre");
	        		String direc = rs.getString("u.direccion");
	        		
	        		System.out.printf(" %-30s | %-10d | %-35s | %-27s %n", nombre_even, asistencia, nombre_ubi, direc);
	        		
	        	}
	        	con.close();
	        	st.close();
	        	rs.close();
	        }catch (SQLException e) {
	        	e.printStackTrace();
	        } 
	       
		 }
		}

