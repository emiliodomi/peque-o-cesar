package pequeno_cesar.TadeoPostres;

import javax.swing.*;
import pequeno_cesar.MainMenu;
import pequeno_cesar.GestorHistorial;
import java.awt.event.*;

public class InterfazElegirPastel extends JFrame {
   
    private static final long serialVersionUID = 1L;
    JLabel textoStock; 
    
    public InterfazElegirPastel(Postres p, JFrame menuAtras) {
        setTitle("Vender Pastel");
        setSize(300, 250);
        setLayout(null); 
        setLocationRelativeTo(null); // Centrar la ventana

        textoStock = new JLabel("Stock de pastel: " + p.getCantidad_almacen());
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

        // --- EVENTO VENDER ---
        btnVender.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(p.getCantidad_almacen() > 0) {
                    p.setCantidad_almacen(p.getCantidad_almacen() - 1);
                    p.vender();
                    textoStock.setText("Stock de pastel: " + p.getCantidad_almacen());
                    
                    // 1. Historial en archivo (tu código original)
                    GestorHistorial gestor = new GestorHistorial();
                    gestor.agregarVenta("Se vendió 1 " + p.getNombre() + " po $" + p.getPrecio());
                    
                    // 2. --- INTEGRACIÓN CON EL TICKET DEL MAIN MENU ---
                    // Sumamos el pastel al total acumulado y al JTextArea
                    MainMenu.agregarAlPedido(p.getNombre(), p.getPrecio());
                    
                    JOptionPane.showMessageDialog(null, "¡Venta de pastel registrada!");
                } else {
                    JOptionPane.showMessageDialog(null, "Ya no hay stock de pastel");
                }
            }
        });

        btnHacer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                p.setCantidad_almacen(p.getCantidad_almacen() + 10);
                textoStock.setText("Stock de pastel: " + p.getCantidad_almacen());
            }
        });

        // --- EVENTO VOLVER ---
        btnVolver.addActionListener(e -> {
            // Regresamos al menú principal (que reconstruirá el historial gracias a las variables static)
            new MainMenu().setVisible(true);
            this.dispose();
        });
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}