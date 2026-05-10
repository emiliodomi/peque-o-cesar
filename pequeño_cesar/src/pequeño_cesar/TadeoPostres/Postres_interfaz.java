package pequeño_cesar.TadeoPostres;

import javax.swing.*;

import HistorialVentas.VentanaHistorial;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Postres_interfaz extends JFrame {
    
    private static final long serialVersionUID = 1L;
    RefrigeradorPostres refri = new RefrigeradorPostres();
    pequeño_cesar.GestorHistorial gestor = new pequeño_cesar.GestorHistorial();
    
    public Postres_interfaz() {
        setTitle("Menu postres");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    
        JMenuBar barraMenu = new JMenuBar();
        JMenu menuOpciones = new JMenu("Opciones");
        
     
        JMenu menuOrdenar = new JMenu("Ordenar");
        JMenuItem itemNombre = new JMenuItem("Nombre");
        
        itemNombre.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<Postres> lista = refri.getListaPostres();
         
                for(int i = 0; i < lista.size() - 1; i++){
                    for(int j = 0; j < lista.size() - 1 - i; j++){
                        if(lista.get(j).getNombre().compareTo(lista.get(j+1).getNombre()) > 0){
                            Postres temporal = lista.get(j);
                            lista.set(j, lista.get(j+1));
                            lista.set(j+1, temporal);
                        }
                    }
                }
                JOptionPane.showMessageDialog(null, "Postres ordenados por Nombre. Cierra y vuelve a abrir para ver los cambios.");
            }
        });
        
        
        menuOrdenar.add(itemNombre);
        menuOpciones.add(menuOrdenar);

              JMenuItem itemHistorial = new JMenuItem("Ver Historial de Ventas");
        itemHistorial.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	//por que me marca error?
            	new VentanaHistorial().setVisible(true);
            }
        });
        
        menuOpciones.add(itemHistorial);
        barraMenu.add(menuOpciones);
        setJMenuBar(barraMenu); 
      
        JPanel p = new JPanel();
        
        List<Postres> lista = refri.getListaPostres();
        
      
        for (int i = 0; i < lista.size(); i++) {
            Postres pos = lista.get(i);
            JButton b = new JButton(pos.getNombre());
            
            b.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    
                    if(pos.getNombre().equals("Flan")) {
                        InterfazElegirFlan vent1 = new InterfazElegirFlan(pos, Postres_interfaz.this);
                        vent1.setVisible(true);
                        dispose(); 
                    }
                    if(pos.getNombre().equals("Pastel")) {
                        InterfazElegirPastel vent2 = new InterfazElegirPastel(pos, Postres_interfaz.this);
                        vent2.setVisible(true);
                        dispose();
                    }
                    if(pos.getNombre().equals("Gelatina")) {
                        InterfazElegirGelatina vent3 = new InterfazElegirGelatina(pos, Postres_interfaz.this);
                        vent3.setVisible(true);
                        dispose();
                    }
                    
                }
         });
            p.add(b);
        }
        
        add(p);
    }
}