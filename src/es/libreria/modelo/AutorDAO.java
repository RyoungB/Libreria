package es.libreria.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.libreria.config.Conexion;

public class AutorDAO {
	 public boolean insertar(Autor autor) {
	        String sql = "INSERT INTO autores (nombre, apellido1, apellido2, fechaNacimiento,nacionalidad, biografia) VALUES (?, ?, ?, ?, ?, ?)";
	     
	        try (Connection conexion = Conexion.getConexion(); 
	             PreparedStatement sentencia = conexion.prepareStatement(sql)) {
	            
	            sentencia.setString(1, autor.getNombre());
	            sentencia.setString(2,autor.getApellido1());
	            sentencia.setString(3,autor.getApellido2());
	            sentencia.setString(4, autor.getFechaNacimiento());
	            sentencia.setString(5, autor.getNacionalidad());
	            sentencia.setString(6, autor.getBiografia());
	           
	            
	            
	            
	            return sentencia.executeUpdate() > 0;
	        } catch (SQLException e) {
	            System.err.println("Error al insertar autor: " + e.getMessage());
	            return false;
	        }
	    }

	    
	    
	  /**
	   * 
	   * Devuelve la lista con todos los autores que estan en la base de datos
	   * @return list de libros
	   */
	    // obtener un listado de todos los libros
	    public List<Autor> obtenerListado() {
	        List<Autor> lista = new ArrayList<>();
	        String sql = "SELECT idAutor, nombre, apellido1, apellido2, fechaNacimiento, nacionalidad,biografia  FROM libreria.AUTORES";
	        
	        try (Connection conexion = Conexion.getConexion(); 
	             PreparedStatement sentencia = conexion.prepareStatement(sql); 
	        		
	            ResultSet resultado = sentencia.executeQuery()) {
	            
	            while (resultado.next()) {
	                lista.add(new Autor(
	                        resultado.getInt("idAutor"),
	                        resultado.getString("nombre"),
	                        resultado.getString("apellido1"),
	                        resultado.getString("apellido2"),
	                        resultado.getString("fechaNacimiento"),
	                        resultado.getString("nacionalidad"),
	                        resultado.getString("biografia")
	                      
	                    ));
	            }
	        } catch (SQLException e) {
	            System.err.println("Error al listar autores: " + e.getMessage());
	        }
	        return lista;
	    }
	    
	    /**
	     * 
	     * 
	     * @param autor
	     * @return true si la actualizacion fue exitosa
	     */
	    
	 // Modificar autores
	    public boolean modificar(Autor autor) {
	        // Buscamos al alumno por su ID y actualizamos sus otros campos
	    	String sql = "UPDATE libreria.AUTORES SET nombre = ?, apellido1 = ?, apellido2 = ?, fechaNacimiento = ?, nacionalidad = ?, biografia = ? WHERE idAutor = ?";
	        
	        try (Connection conexion = Conexion.getConexion(); 
	             PreparedStatement sentencia = conexion.prepareStatement(sql)) {
	            
	            // El orden de los parámetros debe coincidir con el establecido en sql de arriba:
	            sentencia.setString(1, autor.getNombre());
	            sentencia.setString(2, autor.getApellido1());
	            sentencia.setString(3, autor.getApellido2());
	            sentencia.setString(4, autor.getFechaNacimiento());
	            sentencia.setString(5, autor.getNacionalidad());
	            sentencia.setString(6, autor.getBiografia());
	            sentencia.setInt(7, autor.getIdAutor()); // Clave de búsqueda
	           
	            
	            
	            
	            return sentencia.executeUpdate() > 0; // Devuelve true si modificó la fila
	        } catch (SQLException e) {
	            System.err.println("Error al modificar autor: " + e.getMessage());
	            return false;
	        }
	    }
	    
	     /**
	      * 
	      * Elimina el autor usando el id
	      * @param idAutor ID numero del autor a eliminar
	      * @return true si borro el autor correctamente
	      */

	    // Eliminar autores
	    public boolean eliminar(int idAutor) {
	        // Para eliminar solo necesitamos el ID del autor
	       String sql = "DELETE FROM libreria.AUTORES WHERE idAutor = ?";
	        
	        try (Connection conexion = Conexion.getConexion(); 
	             PreparedStatement sentencia = conexion.prepareStatement(sql)) {
	            
	            sentencia.setInt(1, idAutor);
	            
	            return sentencia.executeUpdate() > 0;
	        } catch (SQLException e) {
	            System.err.println("Error al eliminar autor: " + e.getMessage());
	            return false;
	        }
	    }
	    
	    
	    
	    /**
	     * Comprueba la existencia de un autor mediante su isbn 
	     * @param isbn codigo de bara del libro
	     * @return true si ya existe ese libro con ese ISBN en el sistema
	     */
	    // Comprobar si un código de autor existe en la base de datos
	    public boolean existeId(int idAutor) {
	        String sql = "SELECT COUNT(*) FROM libreria.autores WHERE idAutor = ?";
	        
	        try (Connection conexion = Conexion.getConexion(); 
	             PreparedStatement sentencia = conexion.prepareStatement(sql)) {
	            
	            sentencia.setInt(1, idAutor);
	            try (ResultSet resultado = sentencia.executeQuery()) {
	                if (resultado.next()) {
	                    // Si el conteo es mayor que 0, es que el alumno existe
	                    return resultado.getInt(1) > 0;
	                }
	            }
	        } catch (SQLException e) {
	            System.err.println("Error al verificar la existencia del idAutor: " + e.getMessage());
	        }
	        return false;
	    }
	

}
