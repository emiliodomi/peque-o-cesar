package HistorialVentas;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestorHistorial {
    private List<String> listaVentas;
    private final String archivoVentas = "ventas.dat"; // El mismo truco del profe

    public GestorHistorial() {
        listaVentas = cargarVentas();
    }

    public void agregarVenta(String venta) {
        listaVentas.add(venta);
        guardarVentas();
    }

    public List<String> obtenerTodasLasVentas() {
        return listaVentas;
    }

  
    private void guardarVentas() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivoVentas))) {
            oos.writeObject(listaVentas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

  
    @SuppressWarnings("unchecked")
    private List<String> cargarVentas() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivoVentas))) {
            return (List<String>) ois.readObject();
        } catch (Exception e) {
       
            return new ArrayList<>();
        }
    }
}