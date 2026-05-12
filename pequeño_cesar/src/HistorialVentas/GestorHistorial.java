package HistorialVentas; 

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestorHistorial {
    private List<String> listaVentas;
    private final String archivoVentas = "ventas.dat";

    public GestorHistorial() {
        listaVentas = cargarVentas();
    }

    public void agregarVenta(String venta) {
        listaVentas.add(venta);
        guardarVentas();
    }

    public List<String> obtenerVentas() {
        return listaVentas;
    }

    private void guardarVentas() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivoVentas))) {
            oos.writeObject(listaVentas);
        } catch (Exception e) { }
    }

    private List<String> cargarVentas() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivoVentas))) {
            return (List<String>) ois.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}