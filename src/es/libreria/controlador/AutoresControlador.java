package es.libreria.controlador;

import java.util.List;

import es.libreria.modelo.Autor;
import es.libreria.modelo.AutorDAO;

import es.libreria.vista.AutorVista;


public class AutoresControlador {
	
	private final AutorDAO dao;
	private final AutorVista vista;
	
	 public AutoresControlador(AutorVista vista, AutorDAO dao) {
	        this.vista = vista;
	        this.dao = dao;
	        inicializarEventos();
	    }
	
	 
	 
	 
	 
	   public void iniciar() {
		   refrescarTabla();
		   vista.hacerVisible();
	   }
	 /**
	  * para inicicar los eventos 
	  * con las acciones de lo botones
	  */
	    private void inicializarEventos() {
	        //  registrar un nuevo autor
	        vista.getBtnRegistrar().addActionListener(e -> registrarAutor());

	        //  modificr un autor seleccionado
	        vista.getBtnModificar().addActionListener(e -> modificarAutor());

	        // elimina un autor
	        vista.getBtnEliminar().addActionListener(e -> eliminarAutor());

	        //  limpiar campos del formulario
	        vista.getBtnLimpiar().addActionListener(e -> vista.limpiarCampos());

	        // Detectar clics en las filas de la tabla para autorrellenar el formulario
	        vista.getTablaAutores().getSelectionModel().addListSelectionListener(e -> {
	            // Evita que el evento se dispare dos veces 
	            if (!e.getValueIsAdjusting()) {
	                mapearSeleccionTablaAFormulario();
	            }
	        });
	    }
	    
	    
	    /**
	     * Consulta la base de dtos y actualiza el listado
	     */
	    
	    private void refrescarTabla() {
	    	 List<Autor> listado = dao.obtenerListado();
		        vista.mostrarAutores(listado);
	    	
	    }
	    
	    
	    /**
	     *  Inserta un nuevo autor  a partir de los textos del formulario
	     */
	    private void registrarAutor() {
	        try {
	            Autor nuevoAutor = vista.obtenerDatosFormulario();
	            
	            // Validación básica del lado del controlador
	            if (nuevoAutor.getNombre().isEmpty() || nuevoAutor.getApellido1().isEmpty()) {
	                vista.mostrarMensaje("Error: El nombre y el apellido 1 son campos obligatorios.");
	                return;
	            }

	            boolean exito = dao.insertar(nuevoAutor);
	            if (exito) {
	                vista.mostrarMensaje("¡Autor guardado con éxito!");
	                vista.limpiarCampos();
	                refrescarTabla(); // Actualizar la JTable de forma reactiva
	            } else {
	                vista.mostrarMensaje("Error: No se pudo guardar el Autor en el sistema.");
	            }
	        } catch (NumberFormatException ex) {
	            vista.mostrarMensaje("Error de formato: Asegúrate de que la fecha de Nacimiento contengan números válidos.");
	        }
	    }
	    
	    /**
	     *  Modifica un autor existente
	     *  se selecciona haciendo clic en la tabla
	     */
	    private void modificarAutor() {
	        int filaSeleccionada = vista.getTablaAutores().getSelectedRow();
	        if (filaSeleccionada == -1) {
	            vista.mostrarMensaje("Por favor, selecciona un autor de la tabla superior para modificar.");
	            return;
	        }

	        try {
	            Autor AutorAModificar = vista.obtenerDatosFormulario();
	            
	            if (dao.modificar(AutorAModificar)) {
	                vista.mostrarMensaje("¡Autor modificado correctamente!");
	                vista.limpiarCampos();
	                refrescarTabla();
	            } else {
	                vista.mostrarMensaje("Error al intentar actualizar los datos del autor");
	            }
	        } catch (NumberFormatException ex) {
	            vista.mostrarMensaje("Error: Comprueba que los campos numéricos sean correctos.");
	        }
	    }

	    
	    /**
	     * Elimina el libro activo en el formulario tras pedir confirmación emergente
	     */
	    private void eliminarAutor() {
	        int filaSeleccionada = vista.getTablaAutores().getSelectedRow();
	        if (filaSeleccionada == -1) {
	            vista.mostrarMensaje("Por favor, selecciona de la tabla el autor que deseas eliminar.");
	            return;
	        }

	        // Obtener el idLibro de la primera columna (índice 0) de la fila seleccionada
	        int idAutor = (int) vista.getTablaAutores().getValueAt(filaSeleccionada, 0);

	        if (vista.confirmarEliminacion()) {
	            if (dao.eliminar(idAutor)) {
	                vista.mostrarMensaje("El autor ha sido borrado correctamente.");
	                vista.limpiarCampos();
	                refrescarTabla();
	            } else {
	                vista.mostrarMensaje("Error: No se pudo eliminar el autor seleccionado.");
	            }
	        } else {
	            vista.mostrarMensaje("Operación de borrado cancelada por el usuario.");
	        }
	    }

	    
	    
	    /**
	     * Toma el ID de la tabla, busca los datos completos de la lista y los muesres en el formulario
	     */
	    private void mapearSeleccionTablaAFormulario() {
	        int filaSeleccionada = vista.getTablaAutores().getSelectedRow();
	        if (filaSeleccionada != -1) {
	            int idAutor = (int) vista.getTablaAutores().getValueAt(filaSeleccionada, 0);
	            
	            // Buscar en el listado actual el libro que coincide con ese ID
	            List<Autor> listado = dao.obtenerListado();
	            for (Autor a : listado) {
	                if (a.getIdAutor() == idAutor) {
	                    vista.mapearAutorAlFormulario(a);
	                    break;
	                }
	            }
	        }
	    }

	    

}
