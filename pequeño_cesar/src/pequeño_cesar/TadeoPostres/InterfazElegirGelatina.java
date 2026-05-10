package pequeño_cesar.TadeoPostres;

import javax.swing.*;

import HistorialVentas.GestorHistorial;

import java.awt.event.*;

public class InterfazElegirGelatina extends JFrame {
  
	private static final long serialVersionUID = 1L;
	JLabel textoStock; 
    
    public InterfazElegirGelatina(Postres p, JFrame menuAtras) {
        setTitle("Vender Gelatina");
        setSize(300, 250);
        setLayout(null); 

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

        btnVender.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(p.getCantidad_almacen() > 0) {
                    p.setCantidad_almacen(p.getCantidad_almacen() - 1);
                    p.vender();
                    textoStock.setText("Stock de gelatina: " + p.getCantidad_almacen());
                    
               //aqui esta el gestor de historia para mi 
                    GestorHistorial gestor = new GestorHistorial();
                    gestor.agregarVenta("Se vendió 1x " + p.getNombre() + " por $" + p.getPrecio());
                    
                    
                    JOptionPane.showMessageDialog(null, "vendiste una gelatina!");
                } else {
                    JOptionPane.showMessageDialog(null, "ya no hay gelatina");
                }
            }
        });

        btnHacer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                p.setCantidad_almacen(p.getCantidad_almacen() + 10);
                textoStock.setText("Stock de gelatina: " + p.getCantidad_almacen());
            }
        });

        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menuAtras.setVisible(true);
                dispose();
            }
        });
    }
}