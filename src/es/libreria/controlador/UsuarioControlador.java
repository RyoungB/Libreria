package es.libreria.controlador;

import es.libreria.vista.MenuPrincipalVista;
import es.libreria.vista.UsuarioVista;

public class UsuarioControlador {
   private final UsuarioVista vista;
   
   
   public UsuarioControlador(UsuarioVista vista) {
	   this.vista = vista;
	   inicializarEventos();
   }
   
   public void iniciar() {
	   vista.hacerVisible();
   }
   
   private void inicializarEventos() {
	   vista.getBtnIngresar().addActionListener(e  -> validarAcceso());
   }
   
   
   private void validarAcceso() {
	   String usuario = vista.getUsuarioTexto();
	   String password = vista.getContrasenaTexto();
	   
	   
	   //Validación simple de credenciales de prueba 
	   // coregui luegi conn el DAO para usar la de la base de datos
	   
	   if(usuario.equals("admin") && password.equals("1234")) {
		   vista.dispose(); // cerramos la ventana del login
		   
		   
		   //Abre el menu principal usando el MenuprincipalVista
		   MenuPrincipalVista menuVista = new MenuPrincipalVista();
		   MenuPrincipalControlador menuControlador = new MenuPrincipalControlador(menuVista);
		   menuControlador.iniciar();
	   }else {
		   vista.mostrarMensajeError("Usuario o Contraseña incorrecto, vuelve intentarlo!!!");
	   }
   }
}

