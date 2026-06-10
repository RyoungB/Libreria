package es.libreria.config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import es.libreria.config.Conexion;

public class Conexion {
	 	private static String url;
	    private static String usuario;
	    private static String clave;
	    
	    
	    static {
	        Properties props = new Properties();
	        try (InputStream input = Conexion.class.getResourceAsStream("/config.properties")) {
	            if (input == null) {
	                System.err.println("Error: No se encontró config.properties en la raíz de src.");
	            } else {
	                props.load(input);
	                url = props.getProperty("bd.url");
	                usuario = props.getProperty("bd.usuario");
	                clave = props.getProperty("bd.clave");
	            }
	        } catch (IOException ex) {
	            System.err.println("Error al leer el archivo de configuración: " + ex.getMessage());
	        }
	    }

	    public static Connection getConexion() throws SQLException {
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	        } catch (ClassNotFoundException e) {
	            System.err.println("Driver MySQL no encontrado en las librerías de Eclipse.");
	        }
	        return DriverManager.getConnection(url, usuario, clave);
	    }

}
