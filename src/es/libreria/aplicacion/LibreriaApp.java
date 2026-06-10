package es.libreria.aplicacion;

import javax.swing.SwingUtilities;

import es.libreria.controlador.LibrosControlador;
import es.libreria.modelo.LibroDAO;
import es.libreria.vista.LibroVista;

public class LibreriaApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
		SwingUtilities.invokeLater(() ->{
		     try {
	               
	                LibroVista vista = new LibroVista();
	                
	                // 2. Instanciamos el DAO (la capa que habla con Workbench)
	                LibroDAO dao = new LibroDAO();
	                
	                // 3. Instanciamos el Controlador inyectando los componentes anteriores
	                LibrosControlador controlador = new LibrosControlador(vista, dao);
	                
	                // 4. Arrancamos el programa (carga la tabla y muestra la ventana)
	                controlador.iniciar();
	                
	            } catch (Exception e) {
	                System.err.println("Error crítico al iniciar la aplicación gráfica: " + e.getMessage());
	                e.printStackTrace();
	            }
		});
		
	
	}
}
