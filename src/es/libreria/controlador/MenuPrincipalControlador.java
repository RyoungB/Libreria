package es.libreria.controlador;



import es.libreria.modelo.AutorDAO;
import es.libreria.modelo.LibroDAO;
import es.libreria.vista.AutorVista;
import es.libreria.vista.LibroVista;
import es.libreria.vista.MenuPrincipalVista;

public class MenuPrincipalControlador {
	
	private final MenuPrincipalVista vistaMenu;
	
	public MenuPrincipalControlador(MenuPrincipalVista vistaMenu) {
		this.vistaMenu = vistaMenu;
		inicializarEventos();
	}
	
	 public void iniciar() {
		 vistaMenu.hacerVisible();
	 }
	
	private void inicializarEventos() {
	    // Al pulsar "Gestión de Libros", instanciamos y arrancamos su MVC de forma independiente
        vistaMenu.getBtnModuloLibros().addActionListener(e -> {
            LibroVista vistaLibro = new LibroVista();
            LibroDAO daoLibro = new LibroDAO();
            LibrosControlador controladorLibros = new LibrosControlador(vistaLibro, daoLibro);
            
            controladorLibros.iniciar(); // Abre la ventana de libros
        });
        
        
        // Al pulsar "Gestión de Autores"
        vistaMenu.getBtnModuloAutores().addActionListener(e -> {
            /**
             *  Cuando crees AutorVista, AutorDAO y AutoresControlador, los inicializarás aquí idénticamente:
             *   AutorVista vistaAutor = new AutorVista();
             	AutorDAO daoAutor = new AutorDAO();
            	AutoresControlador conAutores = new AutoresControlador(vistaAutor, daoAutor);
             	conAutores.iniciar();
             */
            AutorVista vistaAutor = new AutorVista();
            AutorDAO daoAutor = new AutorDAO();
            AutoresControlador controladorAutores = new AutoresControlador(vistaAutor,daoAutor);
            
            controladorAutores.iniciar();
            
            /**
             *  si dejamos el mensaje de abajo hace que se duplique el mensaje de entrada en el modulo de autores y 
             *  no sale el mensaje de "Módulo de autores en desarrollo (Falta crear su MVC)"
             */
            //JOptionPane.showMessageDialog(vistaMenu, "Módulo de autores en desarrollo (Falta crear su MVC).");
        });
        
        
        
        
	}
}

