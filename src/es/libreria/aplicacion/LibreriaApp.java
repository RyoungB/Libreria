
package es.libreria.aplicacion;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import com.formdev.flatlaf.FlatLightLaf;
import es.libreria.controlador.MenuPrincipalControlador;
import es.libreria.controlador.UsuarioControlador;
import es.libreria.vista.MenuPrincipalVista;
import es.libreria.vista.UsuarioVista;

public class LibreriaApp {

	 public static void main(String[] args) {
	        try {
	            UIManager.setLookAndFeel(new FlatLightLaf());
	        } catch (Exception ex) {
	            System.err.println("No se pudo aplicar el tema visual: " + ex.getMessage());
	        }

	        SwingUtilities.invokeLater(() -> {
	        	
	        	
	        	/*
	            try {
	                // El Main ahora solo inicializa y arranca el ecosistema global desde el Menú
	                MenuPrincipalVista vistaMenu = new MenuPrincipalVista();
	                MenuPrincipalControlador controladorMenu = new MenuPrincipalControlador(vistaMenu);
	                
	                controladorMenu.iniciar();
	                
	            } catch (Exception e) {
	                System.err.println("Error al iniciar el menú principal: " + e.getMessage());
	                e.printStackTrace();
	            }
	        });*/
	        	
	        	
	        	/**
	        	 * implementamos el login
	        	 * mostramos primero el login  para que introduzca el usuario y contraseña correctamente 
	        	 * si falla lanza error 
	        	 * 
	        	 */
	        	
	            try {
	                // El punto de partida definitivo del software es la ventana de autenticación
	                UsuarioVista usuarioVista = new UsuarioVista();
	                UsuarioControlador usuarioControlador = new UsuarioControlador(usuarioVista);
	                
	                usuarioControlador.iniciar();
	                
	            } catch (Exception e) {
	                System.err.println("Error fatal al inicializar el login: " + e.getMessage());
	                e.printStackTrace();
	            }
	        });
	    }
		
		
	
	}



