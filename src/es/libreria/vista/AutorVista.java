package es.libreria.vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import es.libreria.modelo.Autor;


public class AutorVista extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * componentes
	 */
	private JTextField txtId,txtNombre, txtApellido1, txtApellido2,txtFechaNacimiento,txtNacionalidad;
	private JTextArea txtBiografia;
	
	 /**
     *  Botones de registrar, modificar, eliminar y limpiar
     */
    private JButton btnRegistrar, btnModificar, btnEliminar, btnLimpiar;
	
    /**
     * Tabla para enlistar Autores
     */
    private JTable tablaAutores;
    private DefaultTableModel modeloTabla;
    
    
    
    
    
    
    
    public AutorVista() {
        /**
         * Configuración  de la ventana principal
         */
        setTitle("Gestión de la Librería - Módulo Autores");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 650);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        /**
         *  PANEL SUPERIOR: Tabla de contenido
         */
        String[] columnas = {"ID", "Nombre", "Apellido 1", "Apellido 2", "Fecha de Nacimiento", "Nacioanlidad"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; } // Celdas no editables directamente
        };
        tablaAutores = new JTable(modeloTabla);
        JScrollPane scrollTabla = new JScrollPane(tablaAutores);
        scrollTabla.setBorder(BorderFactory.createTitledBorder("Listado Actual de Autores"));
        add(scrollTabla, BorderLayout.NORTH);

        /**
         *  PANEL CENTRAL: Formulario de Datos
         *  Usamos GridLayout colocamos la distribucion de los elementos del formulario y las medidas
         */
        JPanel panelFormulario = new JPanel(new GridLayout(5, 5, 10, 10));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos del Autor"));

        /**
         *  Inicialización de campos de texto
         */
        txtId = new JTextField(); txtId.setEditable(false); // El ID no se escribe a mano
        txtNombre = new JTextField();
        txtApellido1 = new JTextField();
        txtApellido2 = new JTextField();
        txtFechaNacimiento = new JTextField();
        txtNacionalidad = new JTextField();
        
        txtBiografia = new JTextArea(3, 20);
        txtBiografia.setLineWrap(true);
        txtBiografia.setWrapStyleWord(true);
        JScrollPane scrollSinopsis = new JScrollPane(txtBiografia);

        /**
         * Distribución ordenada en la rejilla (Label + Componente)
         */
        panelFormulario.add(new JLabel("ID del Autor (Auto):")); panelFormulario.add(txtId);
        panelFormulario.add(new JLabel("Nombre:")); panelFormulario.add(txtNombre);
        
        panelFormulario.add(new JLabel("Apellido 1 :")); panelFormulario.add(txtApellido1);
        panelFormulario.add(new JLabel("Apellido 2 :")); panelFormulario.add(txtApellido2);
        
        panelFormulario.add(new JLabel("Fecha de Nacimiento:")); panelFormulario.add(txtFechaNacimiento);
        panelFormulario.add(new JLabel("Nacionalidad :")); panelFormulario.add(txtNacionalidad);
        
       
        
        panelFormulario.add(new JLabel("Biografía:")); panelFormulario.add(scrollSinopsis);
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
    public void mostrarAutores(List<Autor> lista) {
        modeloTabla.setRowCount(0); // Limpiar tabla antigua
        for (Autor a : lista) {
            Object[] fila = {
                a.getIdAutor(), a.getNombre(), a.getApellido1(), 
                a.getApellido2(), a.getFechaNacimiento(), a.getNacionalidad()
                
            };
            modeloTabla.addRow(fila);
        }
    }

    /*
     * Extrae un objeto Libro mapeando todos los campos de la pantalla
     */
    public Autor obtenerDatosFormulario() {
     

        Autor autor = new Autor(
            txtNombre.getText().trim(),
            txtApellido1.getText().trim(),
            txtApellido2.getText().trim(),
            txtFechaNacimiento.getText().trim(),
            txtNacionalidad.getText().trim(),
            txtBiografia.getText().trim()
        		);
           
        /**
         * Si el campo ID tiene contenido, se lo inyectamos (para modificaciones)
         */
        if (!txtId.getText().trim().isEmpty()) {
            autor.setIdAutor(Integer.parseInt(txtId.getText().trim()));
        }
        return autor;
    }

    /**
     *  Pone los datos de un libro en los cuadros de texto
     * @param l
     */
    public void mapearAutorAlFormulario(Autor a) {
        txtId.setText(String.valueOf(a.getIdAutor()));
        txtNombre.setText(a.getNombre());
        txtApellido1.setText(a.getApellido1());
        txtApellido2.setText(a.getApellido2());
        txtFechaNacimiento.setText(String.valueOf(a.getFechaNacimiento()));
        txtNacionalidad.setText(a.getNacionalidad());
        txtBiografia.setText(a.getBiografia());
       
    }

    public void limpiarCampos() {
        txtId.setText(""); txtNombre.setText(""); txtApellido1.setText("");
        txtApellido2.setText(""); txtFechaNacimiento.setText(""); txtNacionalidad.setText("");
        txtNacionalidad.setText(""); 
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
            "¿Estás seguro de que deseas eliminar este autor?", 
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
    public JTable  getTablaAutores() { return tablaAutores; }
    
}
