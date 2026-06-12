package es.libreria.modelo;

public class Usuario {
	private String username;
	private String password;
	private String rol;
	
	
	
	//constructor
	public Usuario(String username, String password, String rol) {
		super();
		this.username = username;
		this.password = password;
		this.rol = rol;
	}
	
	
	
	//getters
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getRol() {
		return rol;
	}
	
	

}
