package pequeno_cesar.EmilioBebidas;

import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.*;
import pequeno_cesar.MainMenu; // Asegúrate de que la importación esté presente

public class InterfazElegirRefresco extends JFrame {
    
    private Bebidas bebidaLocal;

    public InterfazElegirRefresco(Bebidas bebidaRecibida) {
        this.bebidaLocal = bebidaRecibida;
        
        setTitle("Venta de Refresco - " + bebidaLocal.getNombre());
        setSize(400, 300); 
        // Cambiado a DISPOSE para no cerrar toda la aplicación
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setLocationRelativeTo(null);
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        // Mostramos el precio en el botón para que el usuario lo vea
        JButton btnComprar = new JButton("Comprar " + bebidaLocal.getNombre() + " ($" + bebidaLocal.getPrecio() + ")");
        btnComprar.setFont(new Font("Arial", Font.BOLD, 14));

        btnComprar.addActionListener(e -> {
            if (bebidaLocal.getCantidad_almacen() > 0) {
                // 1. Modificar los datos en memoria
                bebidaLocal.setCantidad_almacen(bebidaLocal.getCantidad_almacen() - 1);
                bebidaLocal.vender(); 
                
                // 2. Guardar el cambio en el archivo de texto
                guardarEnArchivo();
                
                // --- INTEGRACIÓN CON MAIN MENU ---
                // Enviamos el nombre y el precio al ticket central
                MainMenu.agregarAlPedido(bebidaLocal.getNombre(), bebidaLocal.getPrecio());
                
                JOptionPane.showMessageDialog(this, "Refresco agregado al pedido.\nStock restante: " + bebidaLocal.getCantidad_almacen());
            } else {
                JOptionPane.showMessageDialog(this, "No hay stock suficiente.");
            }
        });

        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> {
            // Regresa al menú principal, el cual se reconstruirá con el historial guardado
            new MainMenu().setVisible(true);
            this.dispose();
        });

        add(btnComprar);
        add(btnVolver);
    }

    // MÉTODO PARA ESCRIBIR EN EL ARCHIVO .TXT
    private void guardarEnArchivo() {
        try (FileWriter fw = new FileWriter("inventario_bebidas.txt", false); 
             PrintWriter pw = new PrintWriter(fw)) {
            
            pw.println("=== ESTADO DEL INVENTARIO ===");
            pw.println("Producto: " + bebidaLocal.getNombre());
            pw.println("Cantidad en Almacen: " + bebidaLocal.getCantidad_almacen());
            pw.println("Total Vendidas: " + bebidaLocal.getVendidas());
            pw.println("----------------------------");
            
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo: " + e.getMessage());
        }
    }
}