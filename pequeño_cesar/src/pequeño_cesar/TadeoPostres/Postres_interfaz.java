package pequeño_cesar.TadeoPostres;

import javax.swing.*;
import HistorialVentas.VentanaHistorial;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import pequeño_cesar.MainMenu;

public class Postres_interfaz extends JFrame {
    
    private static final long serialVersionUID = 1L;
    RefrigeradorPostres refri = new RefrigeradorPostres();
    pequeño_cesar.GestorHistorial gestor = new pequeño_cesar.GestorHistorial();
    
    public Postres_interfaz() {
        setTitle("Menu postres");
        setSize(500, 400); // Un poco más grande para que quepa el diseño
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout()); // Usamos BorderLayout para organizar
        
        // --- CONFIGURACIÓN DE LA BARRA DE MENÚ ---
        JMenuBar barraMenu = new JMenuBar();
        JMenu menuOpciones = new JMenu("Opciones");
        JMenu menuOrdenar = new JMenu("Ordenar");
        JMenuItem itemNombre = new JMenuItem("Nombre");
        
        itemNombre.addActionListener(e -> {
            List<Postres> lista = refri.getListaPostres();
            // Burbuja para ordenar
            for(int i = 0; i < lista.size() - 1; i++){
                for(int j = 0; j < lista.size() - 1 - i; j++){
                    if(lista.get(j).getNombre().compareTo(lista.get(j+1).getNombre()) > 0){
                        Postres temporal = lista.get(j);
                        lista.set(j, lista.get(j+1));
                        lista.set(j+1, temporal);
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Postres ordenados por Nombre. Reinicia la ventana para ver cambios.");
        });
        
        JMenuItem itemHistorial = new JMenuItem("Ver Historial de Ventas");
        itemHistorial.addActionListener(e -> {
            // Se asume que VentanaHistorial recibe el gestor o no recibe nada
            new VentanaHistorial().setVisible(true); 
        });
        
        menuOrdenar.add(itemNombre);
        menuOpciones.add(menuOrdenar);
        menuOpciones.add(itemHistorial);
        barraMenu.add(menuOpciones);
        setJMenuBar(barraMenu); 

        // --- PANEL SUPERIOR PARA EL BOTÓN REGRESAR ---
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton reg = new JButton("← Regresar");
        reg.addActionListener(e -> {
            new MainMenu().setVisible(true);
            this.dispose();
        });
        panelSuperior.add(reg);
        add(panelSuperior, BorderLayout.NORTH);

        // --- PANEL CENTRAL PARA LOS BOTONES DE POSTRES ---
        JPanel p = new JPanel(new FlowLayout()); // FlowLayout acomoda los botones uno tras otro
        List<Postres> lista = refri.getListaPostres();
        
        for (Postres pos : lista) {
            JButton b = new JButton(pos.getNombre());
            b.addActionListener(e -> {
                if(pos.getNombre().equalsIgnoreCase("Flan")) {
                    new InterfazElegirFlan(pos, this).setVisible(true);
                    dispose(); 
                } else if(pos.getNombre().equalsIgnoreCase("Pastel")) {
                    new InterfazElegirPastel(pos, this).setVisible(true);
                    dispose();
                } else if(pos.getNombre().equalsIgnoreCase("Gelatina")) {
                    new InterfazElegirGelatina(pos, this).setVisible(true);
                    dispose();
                }
            });
            p.add(b);
        }
        
        add(p, BorderLayout.CENTER);
        this.setVisible(true);
    }
}