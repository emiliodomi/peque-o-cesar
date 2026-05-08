package pequeño_cesar.TadeoPostres;

import javax.swing.*;
import java.awt.event.*;

public class InterfazElegirFlan extends JFrame {
    JLabel textoStock; 
    
    public InterfazElegirFlan(Postres p, JFrame menuAtras) {
        setTitle("Vender Flan");
        setSize(300, 250);
        setLayout(null); 

        textoStock = new JLabel("Stock de flan: " + p.getCantidad_almacen());
        textoStock.setBounds(20, 20, 200, 30); // X, Y, Ancho, Alto
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

        //  vender
        btnVender.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(p.getCantidad_almacen() > 0) {
                    p.setCantidad_almacen(p.getCantidad_almacen() - 1);
                    p.vender();
                    textoStock.setText("Stock de flan: " + p.getCantidad_almacen());
                    JOptionPane.showMessageDialog(null, "vendiste un flan!");
                } else {
                    JOptionPane.showMessageDialog(null, "ya no hay flan");
                }
            }
        });

        //  hacer mas
        btnHacer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                p.setCantidad_almacen(p.getCantidad_almacen() + 10);
                textoStock.setText("Stock de flan: " + p.getCantidad_almacen());
            }
        });

        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menuAtras.setVisible(true);
                dispose();}
            
        });
    }
}