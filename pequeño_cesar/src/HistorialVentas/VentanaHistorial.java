package HistorialVentas;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VentanaHistorial extends JFrame {
    
    
	private static final long serialVersionUID = 1L;
	private GestorHistorial gestor;
    private DefaultListModel<String> modelVentas; 
    private JList<String> listaVentasVisibles;

    public VentanaHistorial() {
        gestor = new GestorHistorial();
        modelVentas = new DefaultListModel<>();

        setTitle("Historial de Ventas - Pequeño César");
        setSize(400, 300);
        setLayout(new BorderLayout()); 

        List<String> ventas = gestor.obtenerTodasLasVentas();
        for (int i = 0; i < ventas.size(); i++) {
            modelVentas.addElement(ventas.get(i));
        }

        listaVentasVisibles = new JList<>(modelVentas);
        JScrollPane scroll = new JScrollPane(listaVentasVisibles);
        
        add(scroll, BorderLayout.CENTER);
        
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                dispose();
            }
        });
        add(btnCerrar, BorderLayout.SOUTH);
    }
}