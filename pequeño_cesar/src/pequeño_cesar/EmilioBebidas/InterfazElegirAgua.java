package pequeño_cesar.EmilioBebidas;

import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.*;
import pequeño_cesar.MainMenu;

public class InterfazElegirAgua extends JFrame {
    
    private Bebidas bebidaLocal;

    public InterfazElegirAgua(Bebidas bebidaRecibida) {
        this.bebidaLocal = bebidaRecibida;
        
        setTitle("Venta de Agua");
        setSize(400, 300); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        JButton btnComprar = new JButton("Comprar " + bebidaLocal.getNombre());
        btnComprar.setFont(new Font("Arial", Font.BOLD, 14));

        btnComprar.addActionListener(e -> {
            if (bebidaLocal.getCantidad_almacen() > 0) {
                // 1. Modificar los datos en memoria
                bebidaLocal.setCantidad_almacen(bebidaLocal.getCantidad_almacen() - 1);
                bebidaLocal.vender(); 
                
                // 2. Guardar el cambio en el archivo de texto
                guardarEnArchivo();
                
                JOptionPane.showMessageDialog(this, "Venta realizada.\nQuedan: " + bebidaLocal.getCantidad_almacen());
            } else {
                JOptionPane.showMessageDialog(this, "No hay stock suficiente.");
            }
        });

        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> {
            new MainMenu().setVisible(true);
            this.dispose();
        });

        add(btnComprar);
        add(btnVolver);
    }

    // MÉTODO PARA ESCRIBIR EN EL ARCHIVO .TXT
    private void guardarEnArchivo() {
        // Usamos try-with-resources para que el archivo se cierre solo al terminar
        try (FileWriter fw = new FileWriter("inventario_bebidas.txt", false); // 'false' para sobreescribir con el dato más nuevo
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