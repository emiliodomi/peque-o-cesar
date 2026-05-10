package pequeño_cesar;

import javax.swing.*;

import pequeño_cesar.EmilioBebidas.Bebidas_interfaz;
import pequeño_cesar.MarcoPizzas.PizzasInterfaz;
import pequeño_cesar.TadeoPostres.Postres_interfaz;

import java.awt.*;

public class MainMenu extends JFrame {
    
    JPanel Mpanel = new JPanel();
    
    public MainMenu() {
        setTitle("Menú Principal - pequeño cesar");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
       
        Mpanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        
        JButton btnPizzas = crearBotonConImagen("Pizzas", "/imagenes/pizza-illustration.png");
        JButton btnBebidas = crearBotonConImagen("Bebidas", "/imagenes/bebida_illustration.png");
        JButton btnPostres = crearBotonConImagen("Postres", "/imagenes/postre-illustration.png");
        
      
        btnBebidas.addActionListener(e -> {
            new Bebidas_interfaz(); 
            this.dispose();
        });
        
        btnPizzas.addActionListener(e -> {
            new PizzasInterfaz(); 
            this.dispose();
        });
        
        btnPostres.addActionListener(e -> {
            new Postres_interfaz(); 
            this.dispose();
        });


        Mpanel.add(btnPizzas);
        Mpanel.add(btnBebidas);
        Mpanel.add(btnPostres);
        
        JButton btnSalir = new JButton("Salir");
        btnSalir.addActionListener(e -> System.exit(0));
        Mpanel.add(btnSalir);

        this.add(Mpanel);
        this.setVisible(true);
    }

   
    private JButton crearBotonConImagen(String texto, String ruta) {
        java.net.URL url = getClass().getResource(ruta);
        if (url != null) {
            ImageIcon icono = new ImageIcon(url);
            Image img = icono.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            return new JButton(texto, new ImageIcon(img));
        } else {
            System.out.println("No se encontró: " + ruta);
            return new JButton(texto);
        }
    }
}