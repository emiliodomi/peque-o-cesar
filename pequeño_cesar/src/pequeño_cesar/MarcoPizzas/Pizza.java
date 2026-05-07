package pequeño_cesar.MarcoPizzas;

public class Pizza {
    private String especialidad;
    private double precio;
    private int cantidadEnAlmacen;

    // Constructor
    public Pizza(String especialidad, double precio, int cantidadEnAlmacen) {
        this.especialidad = especialidad;
        this.precio = precio;
        this.cantidadEnAlmacen = cantidadEnAlmacen;
    }

    // Método para vender
    public boolean despachar() {
        if (cantidadEnAlmacen > 0) {
            cantidadEnAlmacen--;
            return true;
        } else {
            return false;
        }
    }

    // Método para hornear
    public void hornearNuevas(int cantidad) {
        cantidadEnAlmacen += cantidad;
    }

    // Getters
    public String getEspecialidad() {
        return especialidad;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidadEnAlmacen() {
        return cantidadEnAlmacen;
    }
    
}