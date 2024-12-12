package Conexiones;

import java.sql.Connection;
import java.sql.SQLException;

import org.vibur.dbcp.ViburDBCPDataSource;

public class ConexionVibur {

public static void main(String[] args) {
		
		ViburDBCPDataSource ds = new ViburDBCPDataSource();
		// Configuración del controlador JDBC
        ds.setDriverClassName("org.hsqldb.jdbc.JDBCDriver");

        // Configuración de la URL de conexión
        ds.setJdbcUrl("jdbc:hsqldb:mem:testdb");

        // Configuración del usuario y la contraseña
        ds.setUsername("sa");
        ds.setPassword("");

        // Otras configuraciones opcionales según tus necesidades
        ds.setPoolInitialSize(10);
        ds.setPoolMaxSize(100);
        // ... otras configuraciones ...

        // Inicialización del DataSource
        ds.start();

        // Uso del DataSource para obtener una conexión
        try (Connection conne = ds.getConnection()) {
            System.out.println("¡Conexión exitosa!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Detener el DataSource cuando ya no sea necesario
            ds.close();
        }
    }
}

