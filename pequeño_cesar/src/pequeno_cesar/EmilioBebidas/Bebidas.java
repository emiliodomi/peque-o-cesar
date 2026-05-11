package pequeno_cesar.EmilioBebidas;

public class Bebidas {
	protected String nombre;
	protected String tipo;
	protected int cantidad_almacen;
	protected int vendidas;
	protected double precio;
	
	public Bebidas(String nombre, String tipo, int cantidad_almacen, double precio) {
		super();
		this.nombre = nombre;
		this.tipo = tipo;
		this.cantidad_almacen = cantidad_almacen;
		this.vendidas = 0;
		this.precio = precio;
	}
	
	protected void vender() {
		vendidas += 1;
	}
	
	protected void reabastecer(int sumar_cantidad) {
		cantidad_almacen += sumar_cantidad;
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
		return cantidad_almacen;
	}

	public void setCantidad_almacen(int cantidad_almacen) {
		this.cantidad_almacen = cantidad_almacen;
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



