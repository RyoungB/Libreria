package es.libreria.aplicacion;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import com.formdev.flatlaf.FlatLightLaf;
import es.libreria.controlador.MenuPrincipalControlador;
import es.libreria.vista.MenuPrincipalVista;

public class LibreriaApp {

	 public static void main(String[] args) {
	        try {
	            UIManager.setLookAndFeel(new FlatLightLaf());
	        } catch (Exception ex) {
	            System.err.println("No se pudo aplicar el tema visual: " + ex.getMessage());
	        }

	        SwingUtilities.invokeLater(() -> {
	            try {
	                // El Main ahora solo inicializa y arranca el ecosistema global desde el Menú
	                MenuPrincipalVista vistaMenu = new MenuPrincipalVista();
	                MenuPrincipalControlador controladorMenu = new MenuPrincipalControlador(vistaMenu);
	                
	                controladorMenu.iniciar();
	                
	            } catch (Exception e) {
	                System.err.println("Error al iniciar el menú principal: " + e.getMessage());
	                e.printStackTrace();
	            }
	        });
	    }
		
		
	
	}



