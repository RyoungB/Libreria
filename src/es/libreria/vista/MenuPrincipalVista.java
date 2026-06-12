package es.libreria.vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuPrincipalVista extends JFrame {
	/**
	 *  añadimos los botones
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnModuloLibros;
	private JButton btnModuloAutores;
	private JLabel lblBienvenida;
	
	
	public MenuPrincipalVista() {
		setTitle("Sistema de Gestión de Librería - Menú Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Esta sí cierra la app completa
        setSize(500, 350);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(20, 20));

        /**
         *  Cabecera de bienvenida
         */
        JPanel panelNorte = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
        lblBienvenida = new JLabel("Panel de Administración");
        lblBienvenida.setFont(new Font("Segoe UI", Font.BOLD, 22));
        panelNorte.add(lblBienvenida);
        add(panelNorte, BorderLayout.NORTH);

        /**
         *  Panel Central con los botones de los módulos Libros y Autores
         */
        JPanel panelCentral = new JPanel(new GridLayout(1, 2, 25, 25));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(20, 40, 40, 40));

        Font btnFont = new Font("Segoe UI", Font.BOLD, 14);
        
        /**
         * iconos y estilos de los botones
         */
        btnModuloLibros = new JButton(" Gestión de Libros");
        btnModuloLibros.setFont(btnFont);
        btnModuloLibros.putClientProperty("JButton.buttonType", "default"); // Estilo destacado de FlatLaf
        btnModuloLibros.setIcon(new ImageIcon("C:/JavaCurso/Workspace/Libreria/imagenes/icono/book.png"));
        
        
        btnModuloAutores = new JButton(" Gestión de Autores");
        btnModuloAutores.setFont(btnFont);
        btnModuloAutores.setIcon(new ImageIcon("C:/JavaCurso/Workspace/Libreria/imagenes/icono/author.png"));

        panelCentral.add(btnModuloLibros);
        panelCentral.add(btnModuloAutores);
        add(panelCentral, BorderLayout.CENTER);
    }

    public void hacerVisible() {
        setVisible(true);
    }

    /**
     *  Getters para que el controlador escuche los clics
     * @return
     */
    public JButton getBtnModuloLibros() { return btnModuloLibros; }
    public JButton getBtnModuloAutores() { return btnModuloAutores; }
    public JLabel getLblBienvenida() { return lblBienvenida; }

}
