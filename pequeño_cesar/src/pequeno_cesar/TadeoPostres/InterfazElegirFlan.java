package pequeno_cesar.TadeoPostres;

import javax.swing.*;
import pequeno_cesar.GestorHistorial;
import pequeno_cesar.MainMenu;
import java.awt.event.*;

public class InterfazElegirFlan extends JFrame {
    
    private static final long serialVersionUID = 1L;
    JLabel textoStock; 
    
    public InterfazElegirFlan(Postres p, JFrame menuAtras) {
        setTitle("Vender Flan");
        setSize(300, 250);
        setLayout(null); 
        setLocationRelativeTo(null); // Centra la ventana en pantalla

        textoStock = new JLabel("Stock de flan: " + p.getCantidad_almacen());
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

        // --- LÓGICA VENDER ---
        btnVender.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(p.getCantidad_almacen() > 0) {
                    p.setCantidad_almacen(p.getCantidad_almacen() - 1);
                    p.vender();
                    textoStock.setText("Stock de flan: " + p.getCantidad_almacen());
                    
                    // 1. Registro en el historial de archivos
                    GestorHistorial gestor = new GestorHistorial();
                    gestor.agregarVenta("Se vendió 1x " + p.getNombre() + " por $" + p.getPrecio());
                    
                    // 2. --- CONEXIÓN CON MAIN MENU ---
                    // Registramos el flan en el ticket estático
                    MainMenu.agregarAlPedido(p.getNombre(), p.getPrecio());
                    
                    JOptionPane.showMessageDialog(null, "¡Flan vendido con éxito!");
                } else {
                    JOptionPane.showMessageDialog(null, "No hay stock de flan");
                }
            }
        });

        // --- LÓGICA HACER MÁS ---
        btnHacer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                p.setCantidad_almacen(p.getCantidad_almacen() + 10);
                textoStock.setText("Stock de flan: " + p.getCantidad_almacen());
            }
        });

        // --- LÓGICA VOLVER ---
        btnVolver.addActionListener(e -> {
            // Regresa al MainMenu que ya tiene guardados los datos estáticos
            new MainMenu().setVisible(true);
            this.dispose();
        });

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}