package es.libreria.controlador;

import java.util.List;

import es.libreria.modelo.Libro;
import es.libreria.modelo.LibroDAO;
import es.libreria.vista.LibroVista;

public class LibrosControlador {
	   private final LibroDAO dao;
	   private final LibroVista vista;

	    public LibrosControlador(LibroVista vista, LibroDAO dao) {
	        this.vista = vista;
	        this.dao = dao;
	        inicializarEventos();
	    }

	    
	    
	    
	    
	    public void iniciar() {
	    	refrescarTabla();
	    	vista.hacerVisible();
	    }
	

	    
	
	    /**
	     *  Vincula cada botón y componente de la vista con su lógica correspondiente
	     */
	    private void inicializarEventos() {
	        //  registrar un nuevo libro
	        vista.getBtnRegistrar().addActionListener(e -> registrarLibro());

	        //  modificr un libro seleccionado
	        vista.getBtnModificar().addActionListener(e -> modificarLibro());

	        // elimina un libro
	        vista.getBtnEliminar().addActionListener(e -> eliminarLibro());

	        //  limpiar campos del formulario
	        vista.getBtnLimpiar().addActionListener(e -> vista.limpiarCampos());

	        // Detectar clics en las filas de la tabla para autorrellenar el formulario
	        vista.getTablaLibros().getSelectionModel().addListSelectionListener(e -> {
	            // Evita que el evento se dispare dos veces 
	            if (!e.getValueIsAdjusting()) {
	                mapearSeleccionTablaAFormulario();
	            }
	        });
	    }

	    /**
	     *  Consulta la base de datos y actualiza el listado visual
	     */
	    private void refrescarTabla() {
	        List<Libro> listado = dao.obtenerListado();
	        vista.mostrarLibros(listado);
	    }

	    /**
	     *  Inserta un nuevo libro a partir de los textos del formulario
	     */
	    private void registrarLibro() {
	        try {
	            Libro nuevoLibro = vista.obtenerDatosFormulario();
	            
	            // Validación básica del lado del controlador
	            if (nuevoLibro.getIsbn().isEmpty() || nuevoLibro.getTitulo().isEmpty()) {
	                vista.mostrarMensaje("Error: El ISBN y el Título son campos obligatorios.");
	                return;
	            }

	            boolean exito = dao.insertar(nuevoLibro);
	            if (exito) {
	                vista.mostrarMensaje("¡Libro guardado con éxito!");
	                vista.limpiarCampos();
	                refrescarTabla(); // Actualizar la JTable de forma reactiva
	            } else {
	                vista.mostrarMensaje("Error: No se pudo guardar el libro en el sistema.");
	            }
	        } catch (NumberFormatException ex) {
	            vista.mostrarMensaje("Error de formato: Asegúrate de que el año, precio y páginas contengan números válidos.");
	        }
	    }

	    /**
	     *  Modifica un libro existente
	     *  se selecciona haciendo clic en la tabla
	     */
	    private void modificarLibro() {
	        int filaSeleccionada = vista.getTablaLibros().getSelectedRow();
	        if (filaSeleccionada == -1) {
	            vista.mostrarMensaje("Por favor, selecciona un libro de la tabla superior para modificar.");
	            return;
	        }

	        try {
	            Libro libroAModificar = vista.obtenerDatosFormulario();
	            
	            if (dao.modificar(libroAModificar)) {
	                vista.mostrarMensaje("¡Libro modificado correctamente!");
	                vista.limpiarCampos();
	                refrescarTabla();
	            } else {
	                vista.mostrarMensaje("Error al intentar actualizar los datos del libro.");
	            }
	        } catch (NumberFormatException ex) {
	            vista.mostrarMensaje("Error: Comprueba que los campos numéricos sean correctos.");
	        }
	    }

	    /**
	     * Elimina el libro activo en el formulario tras pedir confirmación emergente
	     */
	    private void eliminarLibro() {
	        int filaSeleccionada = vista.getTablaLibros().getSelectedRow();
	        if (filaSeleccionada == -1) {
	            vista.mostrarMensaje("Por favor, selecciona de la tabla el libro que deseas eliminar.");
	            return;
	        }

	        // Obtener el idLibro de la primera columna (índice 0) de la fila seleccionada
	        int idLibro = (int) vista.getTablaLibros().getValueAt(filaSeleccionada, 0);

	        if (vista.confirmarEliminacion()) {
	            if (dao.eliminar(idLibro)) {
	                vista.mostrarMensaje("El libro ha sido borrado correctamente.");
	                vista.limpiarCampos();
	                refrescarTabla();
	            } else {
	                vista.mostrarMensaje("Error: No se pudo eliminar el libro seleccionado.");
	            }
	        } else {
	            vista.mostrarMensaje("Operación de borrado cancelada por el usuario.");
	        }
	    }

	    /**
	     * Toma el ID de la tabla, busca los datos completos de la lista y los muesres en el formulario
	     */
	    private void mapearSeleccionTablaAFormulario() {
	        int filaSeleccionada = vista.getTablaLibros().getSelectedRow();
	        if (filaSeleccionada != -1) {
	            int idLibro = (int) vista.getTablaLibros().getValueAt(filaSeleccionada, 0);
	            
	            // Buscar en el listado actual el libro que coincide con ese ID
	            List<Libro> listado = dao.obtenerListado();
	            for (Libro l : listado) {
	                if (l.getIdLibro() == idLibro) {
	                    vista.mapearLibroAlFormulario(l);
	                    break;
	                }
	            }
	        }
	    }

}
