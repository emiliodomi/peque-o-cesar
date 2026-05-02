package pequeño_cesar;

public class Bebidas {
	protected String nombre;
	protected String tipo;
	protected int cantidad_almacen;
	protected int vendidas;
	protected double precio;
	
	public Bebidas(String nombre, String tipo, int cantidad_almacen, int vendidas, double precio) {
		super();
		this.nombre = nombre;
		this.tipo = tipo;
		this.cantidad_almacen = cantidad_almacen;
		this.vendidas = 0;
		this.precio = precio;
	}
	
	private void vender() {
		vendidas += 1;
	}
	
	private void reabastecer(int sumar_cantidad) {
		cantidad_almacen += sumar_cantidad;
	}
	
}
