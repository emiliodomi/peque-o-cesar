package pequeño_cesar.TadeoPostres;


import javax.swing.*;

import pequeño_cesar.EmilioBebidas.InterfazElegirAgua;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Postres_interfaz extends JFrame {
    
   
	
	private static final long serialVersionUID = 1L;
	RefrigeradorPostres refri = new RefrigeradorPostres();
    
    public Postres_interfaz() {
        setTitle("Menu postres");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel p = new JPanel();
        
        List<Postres> lista = refri.getListaPostres();
        
        // Un for normalito en vez del for-each elegante
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
                        dispose();}
                    
                }
         });
            p.add(b);
        }
        
        add(p);
    }
}