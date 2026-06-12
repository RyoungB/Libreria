package es.libreria.vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class UsuarioVista extends JFrame {
	
	/**
	 *  declaramos las variales de 
	 *  usuario , contraseña y button 
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtUsuario;
	private JPasswordField txtPassword;
	private JButton btnIngresar;
	
	/**
	 * 
	 */
	public UsuarioVista() {
		setTitle("Acceso al Sistema - Librería");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setSize(220, 340);
	    setLocationRelativeTo(null); // Centra en pantalla
	    setResizable(false);
	    setLayout(new BorderLayout());
	    
        // Panel de encabezado con título 
        JPanel panelNorte = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 15));
        JLabel lblTitulo = new JLabel("Iniciar Sesión");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        panelNorte.add(lblTitulo);
        add(panelNorte, BorderLayout.NORTH);
        
        
     // Panel Central: Formulario estructurado
        JPanel panelCentral = new JPanel(new GridLayout(2, 2, 10, 15));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(20, 30, 10, 30));
     
        Font labelFont = new Font("Segoe UI", Font.BOLD, 13);
        
        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setFont(labelFont);
        txtUsuario = new JTextField();
        
        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setFont(labelFont);
        txtPassword = new JPasswordField();

        panelCentral.add(lblUsuario);
        panelCentral.add(txtUsuario);
        panelCentral.add(lblPassword);
        panelCentral.add(txtPassword);
        add(panelCentral, BorderLayout.CENTER);

        // Panel Inferior: Botones
        JPanel panelSur = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 15));
        btnIngresar = new JButton("Ingresar al Sistema");
        btnIngresar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnIngresar.putClientProperty("JButton.buttonType", "default"); // Resaltado azul de FlatLaf
        
        panelSur.add(btnIngresar);
        add(panelSur, BorderLayout.SOUTH);
    }

    public void hacerVisible() {
        setVisible(true);
    }

    // Métodos para interactuar de forma segura
    public String getUsuarioTexto() {
        return txtUsuario.getText().trim();
    }

    public String getContrasenaTexto() {
        return new String(txtPassword.getPassword()).trim();
    }

    public void mostrarMensajeError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error de Acceso", JOptionPane.ERROR_MESSAGE);
    }

    public JButton getBtnIngresar() {
        return btnIngresar;
    }
        
        
	    
	    
		
	}


