package pequeño_cesar.TadeoPostres;

public class Postres {
	protected String nombre;
	protected String tipo;
	protected int cantidad;
	protected int vendidas;
	protected double precio;
	
	public Postres(String nombre, int cantidad, double precio) {
		super();
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.vendidas = 0;
		this.precio = precio;
	}
	
	protected void vender() {
		vendidas += 1;
	}
	
	protected void reabastecer(int sumar_cantidad) {
		cantidad += sumar_cantidad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getCantidad_almacen() {
		return cantidad;
	}

	public void setCantidad_almacen(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getVendidas() {
		return vendidas;
	}

	public void setVendidas(int vendidas) {
		this.vendidas = vendidas;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	
	
}



