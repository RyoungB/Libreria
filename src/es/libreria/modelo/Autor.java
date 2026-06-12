package es.libreria.modelo;

public class Autor {
	private int idAutor;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String fechaNacimiento;
	private String nacionalidad;
	private String biografia;
	
	
	//Constructot vacio
	public Autor() {
		
	}

    /**
     * 
     * @param nombre
     * @param apellido1
     * @param apellido2
     * @param fechaNacimiento
     * @param nacionalidad
     * @param biografia
     */
	public Autor(String nombre, String apellido1, String apellido2, String fechaNacimiento, String nacionalidad,
			String biografia) {
		super();
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
		this.biografia = biografia;
	}

	public Autor(int idAutor,String nombre, String apellido1, String apellido2, String fechaNacimiento, String nacionalidad,
			String biografia) {
		super();
		this.idAutor = idAutor;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
		this.biografia = biografia;
	}

	
	
	
	/**
	 * gettters y setters
	 * @return
	 */
	public int getIdAutor() {
		return idAutor;
	}
	
	public void setIdAutor(int idAutor) {
		this.idAutor = idAutor;
	}
	
	
	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getBiografia() {
		return biografia;
	}

	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}

	/**
	 * toString
	 */
	@Override
	public String toString() {
		return "Autor [idAutor=" + idAutor + ", nombre=" + nombre + ", apellido1=" + apellido1 + ", apellido2="
				+ apellido2 + ", fechaNacimiento=" + fechaNacimiento + ", nacionalidad=" + nacionalidad + ", biografia="
				+ biografia + "]";
	}

	
	
	
	
	
	
	
	

}
