package Tareas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Tareas.Consola;

public class Tarea3_NuevoAsisitente {

public static void main(String[] args) {
	
	 String url = "jdbc:mysql://localhost:3306/dbeventos";
     String user = "dbeventos";
     String password = "dbeventos";
     
    try (Connection conn = DriverManager.getConnection(url, user, password)) {

        String dni = getDniAsis();

        if (dni.matches("\\d{8}[A-Za-z]")) {

            comparacionDni(conn, dni);
            
            listaEventosPantalla(conn);
            String numEvento = getNumEvento();
            registrarEvento(conn, numEvento, dni);

        } else {
            System.out.println("El formato del DNI no es válido.");
        }
        

    } catch (SQLException e) {
        e.printStackTrace();
    }
}

	private static String getDniAsis() {
	    System.out.println("Introduce el DNI del asistente:");
	    return Consola.readString();
	}
	
	private static String getNumEvento() {
	    System.out.println("Elige el número del evento al que quieres asistir:");
	    return Consola.readString();
	}
	
	private static String getNombre() {
		System.out.println("Introduce el nombre del asistente:");
	    return Consola.readString();
	}
	
	
	private static void insertAsistente(Connection conn, String dni, String nuevoNombre) throws SQLException {
	    String sql2 = "INSERT INTO asistentes (dni, nombre) VALUES (?, ?)";
	    try (PreparedStatement stmt = conn.prepareStatement(sql2)) {
	        stmt.setString(1, dni);
	        stmt.setString(2, nuevoNombre);
	        int numFilas = stmt.executeUpdate();
	        System.out.println("Añadido " + numFilas + " asistente.");
	    }
	}
	
	private static String getAsistenteNombre(Connection conn, String dni) throws SQLException {
	    String sql = "SELECT nombre FROM asistentes WHERE dni= ?";
	    String nom ="";
	    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, dni);
	        try (ResultSet rs = stmt.executeQuery()) {
	            while (rs.next()) {
	                nom = rs.getString("nombre");
	            }
	        }
	    } return nom;
	}
	
	private static void comparacionDni(Connection conn, String dni) throws SQLException {
	    String sql = "SELECT nombre FROM asistentes WHERE dni=?";
	    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, dni); 
	
	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                String nombreAsis = rs.getString(1);
	                System.out.println("Estás realizando una reserva para: " + nombreAsis);
	            } else {
	                //System.out.println("Introduce el nombre del asistente:");
	                String nom = getNombre();
	                insertAsistente(conn, dni, nom);
	                System.out.println("Estás realizando una reserva para: " + nom);
	            }
	        }
	    }
	}
	
	private static void listaEventosPantalla(Connection conexion) throws SQLException {
	    String sql = "SELECT e.id_evento, e.nombre_evento, u.capacidad, count(ae.dni) AS asistencia from eventos e\r\n"
	    		+ "join ubicaciones u on u.id_ubicacion = e.id_ubicacion\r\n"
	    		+ "left join asistentes_eventos ae on e.id_evento = ae.id_evento\r\n"
	    		+ "group by e.id_evento, e.nombre_evento, u.capacidad";
	    try (PreparedStatement sentencia = conexion.prepareStatement(sql);
	        ResultSet rs = sentencia.executeQuery()) {
	    	System.out.println("Lista de eventos: ");
	        while (rs.next()) {
	        	int capacidad =  rs.getInt(3);;
	        	int ocupacion =  rs.getInt(4);;
	        	int disponib = capacidad - ocupacion;
	        	
	            System.out.println(rs.getInt(1) + ". " + rs.getString(2) + " - Espacios disponibles: " + disponib);
	        }
	    }
	}
	
	private static void registrarEvento(Connection conn, String idEvento, String dni) throws SQLException {
	    String sqlCapacidad = "SELECT u.capacidad, COUNT(ae.dni) AS asistencia " 
	            + "FROM eventos e " 
	            + "JOIN ubicaciones u ON u.id_ubicacion = e.id_ubicacion " 
	            + "LEFT JOIN asistentes_eventos ae ON e.id_evento = ae.id_evento " 
	            + "WHERE e.id_evento = ?";
	
	    try (PreparedStatement stmt = conn.prepareStatement(sqlCapacidad)) {
	        stmt.setString(1, idEvento);
	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                int capacidad = rs.getInt(1);
	                int ocupacion = rs.getInt(2);
	                if (capacidad > ocupacion) {
	                    String sqlInsertar = "INSERT INTO asistentes_eventos (dni, id_evento) VALUES (?, ?)";
	                    try (PreparedStatement stmtInsertar = conn.prepareStatement(sqlInsertar)) {
	                        stmtInsertar.setString(1, dni);
	                        stmtInsertar.setString(2, idEvento);
	                        stmtInsertar.executeUpdate();
	
	                        String nombre = getAsistenteNombre(conn, dni);
	                        System.out.println(nombre + " ha sido registrado para el evento seleccionado.");
	                    } 
	                } else {
	                    System.out.println("El evento alcanzó su capacidad máxima.");
	                }
	            }
	        }
	    }
	
	}
}



