package pequeno_cesar.TadeoPostres;

import javax.swing.*;
import pequeno_cesar.GestorHistorial;
import pequeno_cesar.MainMenu;
import java.awt.event.*;

public class InterfazElegirGelatina extends JFrame {
  
    private static final long serialVersionUID = 1L;
    JLabel textoStock; 
    
    public InterfazElegirGelatina(Postres p, JFrame menuAtras) {
        setTitle("Vender Gelatina");
        setSize(300, 250);
        setLayout(null); 
        setLocationRelativeTo(null); // Centra la ventana

        textoStock = new JLabel("Stock de gelatina: " + p.getCantidad_almacen());
        textoStock.setBounds(20, 20, 200, 30); 
        add(textoStock);

        JButton btnVender = new JButton("vender 1");
        btnVender.setBounds(20, 60, 120, 30);
        add(btnVender);

        JButton btnHacer = new JButton("hacer mas");
        btnHacer.setBounds(20, 100, 120, 30);
        add(btnHacer);
        
        JButton btnVolver = new JButton("regresar");
        btnVolver.setBounds(20, 150, 120, 30);
        add(btnVolver);

        // --- LÓGICA PARA VENDER ---
        btnVender.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(p.getCantidad_almacen() > 0) {
                    p.setCantidad_almacen(p.getCantidad_almacen() - 1);
                    p.vender();
                    textoStock.setText("Stock de gelatina: " + p.getCantidad_almacen());
                    
                    // 1. Guardado en tu gestor de historial personal
                    GestorHistorial gestor = new GestorHistorial();
                    gestor.agregarVenta("Se vendió 1x " + p.getNombre() + " por $" + p.getPrecio());
                    
                    // 2. --- CONEXIÓN CON EL TICKET DEL MAIN MENU ---
                    // Registra la gelatina en el área de texto central y suma al total
                    MainMenu.agregarAlPedido(p.getNombre(), p.getPrecio());
                    
                    JOptionPane.showMessageDialog(null, "¡Gelatina vendida y agregada al ticket!");
                } else {
                    JOptionPane.showMessageDialog(null, "Ya no hay gelatina disponible");
                }
            }
        });

        // --- LÓGICA PARA REABASTECER ---
        btnHacer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                p.setCantidad_almacen(p.getCantidad_almacen() + 10);
                textoStock.setText("Stock de gelatina: " + p.getCantidad_almacen());
            }
        });

        // --- LÓGICA PARA REGRESAR ---
        btnVolver.addActionListener(e -> {
            // Abrimos el menú principal (que cargará el historial estático automáticamente)
            new MainMenu().setVisible(true);
            this.dispose();
        });
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}