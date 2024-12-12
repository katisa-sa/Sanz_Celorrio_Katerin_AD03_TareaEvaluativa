package Tareas;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Tarea4_Procedimiento {

	public static void main(String[] args) {
		
		 String url = "jdbc:mysql://localhost:3306/dbeventos";
	     String user = "dbeventos";
	     String password = "dbeventos";
	     
	     
	     try (Connection con = DriverManager.getConnection(url, user, password);
	    	  CallableStatement cs = con.prepareCall("{? = call obtener_numero_asistentes(?)}")){
	    	 
	    	 listaEventosPantalla(con);
	    	 int numEvento = getNumEvento();
	    	 
	    	 cs.registerOutParameter(1, java.sql.Types.INTEGER);
	    	 cs.setInt(2, numEvento);
	    	 cs.execute();	
	    	 
	    	 int numAsistentes = cs.getInt(1);
	    	 System.out.println("El número de asistentes para el evento seleccionado es: " + numAsistentes);
	    	 
	     } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private static int getNumEvento() {
	    System.out.println("Elige el número del evento al que quieres asistir:");
	    return Consola.readInt();
	}
	
	private static void listaEventosPantalla(Connection con) throws SQLException {
		String sql ="SELECT id_evento, nombre_evento FROM eventos";
		try (PreparedStatement ps= con.prepareStatement(sql)){
			ResultSet rs = ps.executeQuery();
			
			System.out.println("Lista de eventos:");
			while (rs.next()) {
				System.out.println(rs.getInt(1) + ". " + rs.getString(2));
			}
		}
	}

}
