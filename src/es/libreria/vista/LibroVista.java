package es.libreria.vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List;


import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import es.libreria.modelo.Libro;

public class LibroVista extends JFrame {
    
    /**
	 * seriazable
	 */
	private static final long serialVersionUID = 1L;
	/**
     *  Componentes del Formulario
     */
    private JTextField txtId, txtIsbn, txtTitulo, txtFecha, txtPrecio, txtPortada,  txtPaginas, txtGenero, txtEditorial;
    private JComboBox<String> txtEncuadernacion;
    private JTextArea txtSinopsis;
    
    /**
     *  Botones de registrar, modificar, eliminar y limpiar
     */
    private JButton btnRegistrar, btnModificar, btnEliminar, btnLimpiar;
    
    /**
     * Tabla para enlistar Libros
     */
    private JTable tablaLibros;
    private DefaultTableModel modeloTabla;

    public LibroVista() {
        /**
         * Configuración  de la ventana principal
         */
        setTitle("Gestión de la Librería - Módulo Libros");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 650);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        /**
         *  PANEL SUPERIOR: Tabla de contenido
         */
        String[] columnas = {"ID", "ISBN", "Título", "Año", "Precio", "Páginas", "Género", "Editorial"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; } // Celdas no editables directamente
        };
        tablaLibros = new JTable(modeloTabla);
        JScrollPane scrollTabla = new JScrollPane(tablaLibros);
        scrollTabla.setBorder(BorderFactory.createTitledBorder("Listado Actual de Libros"));
        add(scrollTabla, BorderLayout.NORTH);

        /**
         *  PANEL CENTRAL: Formulario de Datos
         */
        JPanel panelFormulario = new JPanel(new GridLayout(6, 4, 10, 10));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos del Libro"));

        /**
         *  Inicialización de campos de texto
         */
        txtId = new JTextField(); txtId.setEditable(false); // El ID no se escribe a mano
        txtIsbn = new JTextField();
        txtTitulo = new JTextField();
        txtFecha = new JTextField();
        txtPrecio = new JTextField();
        txtPortada = new JTextField();
        //txtEncuadernacion = new JTextField();
        txtPaginas = new JTextField();
        txtGenero = new JTextField();
        txtEditorial = new JTextField();
        txtSinopsis = new JTextArea(3, 20);
        txtSinopsis.setLineWrap(true);
        txtSinopsis.setWrapStyleWord(true);
        JScrollPane scrollSinopsis = new JScrollPane(txtSinopsis);
        
        /**
         * Añadimos encuadernacion en un JComboBox 
         * debido a que asi evitaremos que el usuario comenta errores tipograficos
         * a la hora de elegir el tipo de tapa (Blanda o Dura)
         * 
         */
        
         String[] opcionesEncuadernacion = {"Tapa Blanda", "Tapa dura"};
         txtEncuadernacion = new JComboBox<String>(opcionesEncuadernacion);

        /**
         * Distribución ordenada en la rejilla (Label + Componente)
         */
        panelFormulario.add(new JLabel("ID Libro (Autor):")); panelFormulario.add(txtId);
        panelFormulario.add(new JLabel("ISBN:")); panelFormulario.add(txtIsbn);
        
        panelFormulario.add(new JLabel("Título:")); panelFormulario.add(txtTitulo);
        panelFormulario.add(new JLabel("Año Publicación:")); panelFormulario.add(txtFecha);
        
        panelFormulario.add(new JLabel("Precio (€):")); panelFormulario.add(txtPrecio);
        panelFormulario.add(new JLabel("Nº Páginas:")); panelFormulario.add(txtPaginas);
        
        panelFormulario.add(new JLabel("ID Género:")); panelFormulario.add(txtGenero);
        panelFormulario.add(new JLabel("ID Editorial:")); panelFormulario.add(txtEditorial);
        
        panelFormulario.add(new JLabel("Encuadernación:")); panelFormulario.add(txtEncuadernacion);
        panelFormulario.add(new JLabel("URL Portada:")); panelFormulario.add(txtPortada);
        
        panelFormulario.add(new JLabel("Sinopsis:")); panelFormulario.add(scrollSinopsis);
        panelFormulario.add(new JLabel("")); panelFormulario.add(new JLabel("")); // Rellenos vacíos para cuadrar

        add(panelFormulario, BorderLayout.CENTER);

        /**
         *  PANEL INFERIOR: Botones
         */
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        btnRegistrar = new JButton("Registrar");
        btnModificar = new JButton("Modificar");
        btnEliminar = new JButton("Eliminar");
        btnLimpiar = new JButton("Limpiar Campos");
        
        panelBotones.add(btnRegistrar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);
        
        add(panelBotones, BorderLayout.SOUTH);
    }

    // --- MÉTODOS DE INTERACCIÓN PARA EL CONTROLADOR ---

    public void hacerVisible() {
        setVisible(true);
    }

    /**
     *  Carga la lista de libros en la JTable visual
     * @param lista
     */
    public void mostrarLibros(List<Libro> lista) {
        modeloTabla.setRowCount(0); // Limpiar tabla antigua
        for (Libro l : lista) {
            Object[] fila = {
                l.getIdLibro(), l.getIsbn(), l.getTitulo(), 
                l.getFechapublicacion(), l.getPrecio(), l.getNumeroPaginas(),
                l.getIdGenero(), l.getIdEditorial()
            };
            modeloTabla.addRow(fila);
        }
    }

    /*
     * Extrae un objeto Libro mapeando todos los campos de la pantalla
     */
    public Libro obtenerDatosFormulario() {
        // Validar campos numéricos vacíos de forma segura antes de parsear
        int anio = txtFecha.getText().trim().isEmpty() ? 0 : Integer.parseInt(txtFecha.getText().trim());
        double precio = txtPrecio.getText().trim().isEmpty() ? 0.0 : Double.parseDouble(txtPrecio.getText().trim());
        int paginas = txtPaginas.getText().trim().isEmpty() ? 0 : Integer.parseInt(txtPaginas.getText().trim());
        int genero = txtGenero.getText().trim().isEmpty() ? 0 : Integer.parseInt(txtGenero.getText().trim());
        int editorial = txtEditorial.getText().trim().isEmpty() ? 0 : Integer.parseInt(txtEditorial.getText().trim());

        String encuadernacionSeleccionada = txtEncuadernacion.getSelectedItem().toString();        
        Libro libro = new Libro(
            txtIsbn.getText().trim(),
            txtTitulo.getText().trim(),
            anio,
            precio,
            txtSinopsis.getText().trim(),
            txtPortada.getText().trim(),
            //txtEncuadernacion.getText().trim(),
            encuadernacionSeleccionada,
            paginas,
            genero,
            editorial
        );

        /**
         * Si el campo ID tiene contenido, se lo inyectamos (para modificaciones)
         */
        if (!txtId.getText().trim().isEmpty()) {
            libro.setIdLibro(Integer.parseInt(txtId.getText().trim()));
        }
        return libro;
    }

    /**
     *  Pone los datos de un libro en los cuadros de texto
     * @param l
     */
    public void mapearLibroAlFormulario(Libro l) {
        txtId.setText(String.valueOf(l.getIdLibro()));
        txtIsbn.setText(l.getIsbn());
        txtTitulo.setText(l.getTitulo());
        txtFecha.setText(String.valueOf(l.getFechapublicacion()));
        txtPrecio.setText(String.valueOf(l.getPrecio()));
        txtPortada.setText(l.getPortadaURL());
        //txtEncuadernacion.setText(l.getEncuadernacion());
        txtEncuadernacion.setSelectedItem(l.getEncuadernacion());
        txtPaginas.setText(String.valueOf(l.getNumeroPaginas()));
        txtGenero.setText(String.valueOf(l.getIdGenero()));
        txtEditorial.setText(String.valueOf(l.getIdEditorial()));
        txtSinopsis.setText(l.getSinopsis());
    }

    public void limpiarCampos() {
        txtId.setText(""); txtIsbn.setText(""); txtTitulo.setText("");
        txtFecha.setText(""); txtPrecio.setText(""); txtPortada.setText("");
        //txtEncuadernacion.setText("");
        txtEncuadernacion.setSelectedIndex(0);
        txtPaginas.setText("");
        txtGenero.setText(""); txtEditorial.setText(""); txtSinopsis.setText("");
    }

    /**
     * Cuadros de diálogo Swing (JOptionPane) 
     * @param mensaje
     */
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    public boolean confirmarEliminacion() {
        int respuesta = JOptionPane.showConfirmDialog(this, 
            "¿Estás seguro de que deseas eliminar este libro?", 
            "Confirmar Borrado", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        return respuesta == JOptionPane.YES_OPTION;
    }

    /**
     * Controlador puede escuchar  las pulsaciones de botones y clics en la tabla
     * @return
     */
    public JButton getBtnRegistrar() { return btnRegistrar; }
    public JButton getBtnModificar() { return btnModificar; }
    public JButton getBtnEliminar() { return btnEliminar; }
    public JButton getBtnLimpiar() { return btnLimpiar; }
    public JTable getTablaLibros() { return tablaLibros; }
}