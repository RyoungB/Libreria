package es.libreria.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.libreria.config.Conexion;

   /**
    * Clase de entidad que representa un libro según el esquema que hemos hecho en la BD Libreria
    * 
    */
public class LibroDAO {
    public boolean insertar(Libro libro) {
        String sql = "INSERT INTO libros (isbn, titulo, fechapublicacion, precio,sinopsis, portadaURL, encuadernación,numeroPaginas,idGenero,idEditorial) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ? , ?)";
     
        try (Connection conexion = Conexion.getConexion(); 
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            
            sentencia.setString(1, libro.getIsbn());
            sentencia.setString(2,libro.getTitulo());
            sentencia.setInt(3,libro.getFechapublicacion());
            sentencia.setDouble(4, libro.getPrecio());
            sentencia.setString(5, libro.getSinopsis());
            sentencia.setString(6, libro.getPortadaURL());
            sentencia.setString(7, libro.getEncuadernacion());
            sentencia.setInt(8, libro.getNumeroPaginas());
            sentencia.setInt(9, libro.getIdGenero());
            sentencia.setInt(10, libro.getIdEditorial());
            
            
            
            return sentencia.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar libro: " + e.getMessage());
            return false;
        }
    }

    
    
  /**
   * 
   * Devuelve la lista con todos los libros que estan en la base de datos
   * @return list de libros
   */
    // obtener un listado de todos los libros
    public List<Libro> obtenerListado() {
        List<Libro> lista = new ArrayList<>();
        String sql = "SELECT idLibro, isbn, titulo, fechaPublicacion, precio, sinopsis, portadaURL, encuadernación, numeroPaginas, idGenero, idEditorial FROM libreria.LIBROS";
        
        try (Connection conexion = Conexion.getConexion(); 
             PreparedStatement sentencia = conexion.prepareStatement(sql); 
        		
            ResultSet resultado = sentencia.executeQuery()) {
            
            while (resultado.next()) {
                lista.add(new Libro(
                        resultado.getInt("idLibro"),
                        resultado.getString("isbn"),
                        resultado.getString("titulo"),
                        resultado.getInt("fechaPublicacion"),
                        resultado.getDouble("precio"),
                        resultado.getString("sinopsis"),
                        resultado.getString("portadaURL"),
                        resultado.getString("encuadernación"),
                        resultado.getInt("numeroPaginas"),
                        resultado.getInt("idGenero"),
                        resultado.getInt("idEditorial")
                    ));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar libros: " + e.getMessage());
        }
        return lista;
    }
    
    /**
     * 
     * 
     * @param libro
     * @return true si la actualizacion fue exitosa
     */
    
 // Modificar libros
    public boolean modificar(Libro libro) {
        // Buscamos al alumno por su ID y actualizamos sus otros campos
    	String sql = "UPDATE libreria.LIBROS SET isbn = ?, titulo = ?, fechaPublicacion = ?, precio = ?, sinopsis = ?, portadaURL = ?, encuadernación = ?, numeroPaginas = ?, idGenero = ?, idEditorial = ? WHERE idLibro = ?";
        
        try (Connection conexion = Conexion.getConexion(); 
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            
            // El orden de los parámetros debe coincidir con el establecido en sql de arriba:
            sentencia.setString(1, libro.getIsbn());
            sentencia.setString(2, libro.getTitulo());
            sentencia.setInt(3, libro.getFechapublicacion());
            sentencia.setDouble(4, libro.getPrecio());
            sentencia.setString(5, libro.getSinopsis());
            sentencia.setString(6, libro.getPortadaURL());
            sentencia.setString(7, libro.getEncuadernacion());
            sentencia.setInt(8, libro.getNumeroPaginas());
            sentencia.setInt(9, libro.getIdGenero());
            sentencia.setInt(10, libro.getIdEditorial());
            sentencia.setInt(11, libro.getIdLibro()); // Clave de búsqueda
            
            
            
            return sentencia.executeUpdate() > 0; // Devuelve true si modificó la fila
        } catch (SQLException e) {
            System.err.println("Error al modificar libro: " + e.getMessage());
            return false;
        }
    }
    
     /**
      * 
      * Elimina el libro usando el id
      * @param idLibro ID numer del libro a eliminar
      * @return true si borro el libro correctamente
      */

    // Eliminar libros
    public boolean eliminar(int idLibro) {
        // Para eliminar solo necesitamos el ID del libro
       String sql = "DELETE FROM libreria.LIBROS WHERE idLibro = ?";
        
        try (Connection conexion = Conexion.getConexion(); 
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            
            sentencia.setInt(1, idLibro);
            
            return sentencia.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar libro: " + e.getMessage());
            return false;
        }
    }
    
    
    
    /**
     * Comprueba la existenia de un libro mediante su isbn 
     * @param isbn codigo de bara del libro
     * @return true si ya existe ese libro con ese ISBN en el sistema
     */
    // Comprobar si un código de alumno existe en la base de datos
    public boolean existeId(int isbn) {
        String sql = "SELECT COUNT(*) FROM libreria.libros WHERE idlibro = ?";
        
        try (Connection conexion = Conexion.getConexion(); 
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            
            sentencia.setInt(1, isbn);
            try (ResultSet resultado = sentencia.executeQuery()) {
                if (resultado.next()) {
                    // Si el conteo es mayor que 0, es que el alumno existe
                    return resultado.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar la existencia del ISBN: " + e.getMessage());
        }
        return false;
    }

}
