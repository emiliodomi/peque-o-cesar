package pequeno_cesar.EmilioBebidas;

import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.*;
import pequeno_cesar.MainMenu; // Importación para conectar con el menú principal

public class InterfazElegirVino extends JFrame {
    
    private Bebidas bebidaLocal;

    public InterfazElegirVino(Bebidas bebidaRecibida) {
        this.bebidaLocal = bebidaRecibida;
        
        setTitle("Venta de Vino - " + bebidaLocal.getNombre());
        setSize(400, 300); 
        // Cambiado a DISPOSE para no cerrar toda la app por accidente
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setLocationRelativeTo(null);
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        // Añadimos el precio al texto del botón para claridad del usuario
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
                // Registramos el vino en el historial estático de MainMenu
                MainMenu.agregarAlPedido(bebidaLocal.getNombre(), bebidaLocal.getPrecio());
                
                JOptionPane.showMessageDialog(this, "Vino añadido al pedido.\nStock actualizado.");
            } else {
                JOptionPane.showMessageDialog(this, "No hay stock suficiente.");
            }
        });

        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> {
            // Abrimos el menú principal que ya tiene guardado el total y el historial
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