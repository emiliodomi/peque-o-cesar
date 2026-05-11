package pequeño_cesar.EmilioBebidas;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import pequeño_cesar.MainMenu;
import java.awt.*;
import java.util.List;

public class Bebidas_interfaz extends JFrame {
    
    JPanel panelB = new JPanel();
    Refrigerador miRefrigerador = new Refrigerador();
    
    Color colorFondo = new Color(245, 245, 245);
    Color colorBoton = new Color(52, 152, 219);
    Color colorTexto = Color.WHITE;

    public Bebidas_interfaz() {
        setTitle("Catálogo de Bebidas");
        setSize(600, 500); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(colorFondo);
        setLayout(new BorderLayout()); 

        JPanel panelNorte = new JPanel();
        panelNorte.setLayout(new BoxLayout(panelNorte, BoxLayout.Y_AXIS));
        panelNorte.setBackground(colorFondo);

        JPanel panelBotonEsquina = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBotonEsquina.setBackground(colorFondo);
        
        JButton btnRegresar = new JButton("← Regresar");
        btnRegresar.setFocusPainted(false);
        
        btnRegresar.addActionListener(e -> {
            new MainMenu().setVisible(true);
            this.dispose();
        });

        panelBotonEsquina.add(btnRegresar);
        
        JLabel titulo = new JLabel("Seleccione una Categoría");
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT); 
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titulo.setBorder(new EmptyBorder(10, 0, 20, 0));

        panelNorte.add(panelBotonEsquina);
        panelNorte.add(titulo);
        add(panelNorte, BorderLayout.NORTH);

        panelB.setLayout(new GridLayout(0, 3, 15, 15));
        panelB.setBackground(colorFondo);
        panelB.setBorder(new EmptyBorder(20, 20, 20, 20));

        List<Bebidas> lista = miRefrigerador.getListaBebidas();
        
        for (Bebidas bebida : lista) {
            JButton botonBebida = crearBoton(bebida.getTipo());
            
            botonBebida.addActionListener(e -> {
                String tipo = bebida.getTipo(); 
                switch (tipo) {
                    case "Refresco": new InterfazElegirRefresco(bebida).setVisible(true); break;
                    case "Agua": new InterfazElegirAgua(bebida).setVisible(true); break;
                    case "Vino": new InterfazElegirVino(bebida).setVisible(true); break;
                }
                this.dispose();
            });
            panelB.add(botonBebida);
        }
       
        JScrollPane scrollPane = new JScrollPane(panelB);
        scrollPane.setBorder(null);
        add(scrollPane, BorderLayout.CENTER);

        this.setVisible(true);
    }

    private JButton crearBoton(String texto) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        btn.setBackground(colorBoton);
        btn.setForeground(colorTexto);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(colorBoton.darker(), 1),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        
        return btn;
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Bebidas_interfaz());
    }
}