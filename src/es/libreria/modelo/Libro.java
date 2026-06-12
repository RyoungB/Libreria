package es.libreria.modelo;

public class Libro {
	private int idLibro;
	private String isbn;
	private String titulo;
	private int fechapublicacion;
	private double precio;
	private String sinopsis;
	private String portadaURL;
	private String encuadernacion;
	private int numeroPaginas;
	private int idEditorial;
	private int idGenero;
	
	
	
	//Constructor vacio
	public Libro() {
		
	}
	
	
	/**
	 * Constructor sin IdLibro solo se usara para registrar libros nuevos / insert
	 * @param isbn
	 * @param titulo
	 * @param fechapublicacion
	 * @param precio
	 * @param sinopsis
	 * @param portadaURL
	 * @param encuadernacion
	 * @param idEditorial
	 * @param idGenero
	 * @param numeroPaginas
	 */
	public Libro(String isbn, String titulo, int fechapublicacion, double precio,String sinopsis,String portadaURL, String encuadernacion, int numeroPaginas ,int idEditorial,
			int idGenero) {
		super();
		
		this.isbn = isbn;
		this.titulo = titulo;
		this.fechapublicacion = fechapublicacion;
		this.precio = precio;
		this.sinopsis = sinopsis;
		this.portadaURL = portadaURL;
		this.encuadernacion = encuadernacion;
		this.numeroPaginas = numeroPaginas;
		this.idEditorial = idEditorial;
		this.idGenero = idGenero;
	}
	
	
	/**
	 * constructor idLibro para usar select y update 
	 * @param idLibro
	 * @param isbn
	 * @param titulo
	 * @param fechapublicacion
	 * @param precio
	 * @param sinopsis
	 * @param portadaURL
	 * @param encuadernacion
	 * @param idEditorial
	 * @param idGenero
	 * @param numeroPaginas
	 */
	
	public Libro(int idLibro,String isbn, String titulo, int fechapublicacion, double precio,String sinopsis,String portadaURL, String encuadernacion, int numeroPaginas, int idEditorial,
			int idGenero) {
		super();
		this.idLibro = idLibro;
		this.isbn = isbn;
		this.titulo = titulo;
		this.fechapublicacion = fechapublicacion;
		this.precio = precio;
		this.sinopsis = sinopsis;
		this.portadaURL = portadaURL;
		this.encuadernacion = encuadernacion;
		this.numeroPaginas = numeroPaginas;
		this.idEditorial = idEditorial;
		this.idGenero = idGenero;
	}


	//getters y setters
	public int getIdLibro() {
		return idLibro;
	}


	public void setIdLibro(int idLibro) {
		this.idLibro = idLibro;
	}


	public String getIsbn() {
		return isbn;
	}


	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public int getFechapublicacion() {
		return fechapublicacion;
	}


	public void setFechapublicacion(int fechapublicacion) {
		this.fechapublicacion = fechapublicacion;
	}


	public double getPrecio() {
		return precio;
	}


	public void setPrecio(double precio) {
		this.precio = precio;
	}


	public String getSinopsis() {
		return sinopsis;
	}


	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}


	public String getPortadaURL() {
		return portadaURL;
	}


	public void setPortadaURL(String portadaURL) {
		this.portadaURL = portadaURL;
	}


	public int getIdGenero() {
		return idGenero;
	}


	public void setIdGenero(int idGenero) {
		this.idGenero = idGenero;
	}


	public int getIdEditorial() {
		return idEditorial;
	}


	public void setIdEditorial(int idEditorial) {
		this.idEditorial = idEditorial;
	}


	public String getEncuadernacion() {
		return encuadernacion;
	}


	public void setEncuadernacion(String encuadernacion) {
		this.encuadernacion = encuadernacion;
	}


	public int getNumeroPaginas() {
		return numeroPaginas;
	}


	public void setNumeroPaginas(int numeroPaginas) {
		this.numeroPaginas = numeroPaginas;
	}


	@Override
	public String toString() {
		return "Libro [idLibro=" + idLibro + ", isbn=" + isbn + ", titulo=" + titulo + ", fechapublicacion="
				+ fechapublicacion + ", precio=" + precio + ", sinopsis=" + sinopsis + ", portadaURL=" + portadaURL
				+ ", encuadernacion=" + encuadernacion + ", numeroPaginas=" + numeroPaginas + ", idEditorial="
				+ idEditorial + ", idGenero=" + idGenero + "]";
	}


	

}
