package pequeno_cesar;

import javax.swing.*;
import java.awt.*;
import pequeno_cesar.EmilioBebidas.Bebidas_interfaz;
import pequeno_cesar.MarcoPizzas.PizzasInterfaz;
import pequeno_cesar.TadeoPostres.Postres_interfaz;

public class MainMenu extends JFrame {
    
    private static final long serialVersionUID = 1L;
    
    // VARIABLES ESTÁTICAS ÚNICAS (Viven fuera de la instancia de la ventana)
    private static JTextArea areaTexto; 
    private static double totalCuenta = 0.0;
    private static String historialTexto = ""; // Para recordar lo que ya se escribió

    private JPanel Mpanel = new JPanel();

    public MainMenu() {
        setTitle("Menú Principal - Pequeño Cesar");
        setSize(700, 500); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        // --- Panel de Botones ---
        Mpanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        Mpanel.setBackground(new Color(245, 245, 245)); 

        JButton btnPizzas = crearBotonConImagen("Pizzas", "/imagenes/pizza-illustration.png");
        JButton btnBebidas = crearBotonConImagen("Bebidas", "/imagenes/bebida_illustration.png");
        JButton btnPostres = crearBotonConImagen("Postres", "/imagenes/postre-illustration.png");
        JButton btnSalir = new JButton("Salir");

        // Eventos
        btnBebidas.addActionListener(e -> { new Bebidas_interfaz().setVisible(true); this.dispose(); });
        btnPizzas.addActionListener(e -> { new PizzasInterfaz().setVisible(true); this.dispose(); });
        btnPostres.addActionListener(e -> { new Postres_interfaz().setVisible(true); this.dispose(); });
        btnSalir.addActionListener(e -> System.exit(0));

        Mpanel.add(btnPizzas); Mpanel.add(btnBebidas); Mpanel.add(btnPostres); Mpanel.add(btnSalir);

        // --- Configuración del JTextArea ---
        areaTexto = new JTextArea();
        areaTexto.setEditable(false); 
        areaTexto.setFont(new Font("Monospaced", Font.PLAIN, 14));
        
        // RECONSTRUCCIÓN DEL TEXTO: Si ya hay historial, lo cargamos. Si no, ponemos bienvenida.
        if (historialTexto.isEmpty()) {
            historialTexto = "===== BIENVENIDO A PEQUEÑO CESAR =====\n" +
                             "Seleccione una categoría para continuar.\n" +
                             "---------------------------------------";
        }
        areaTexto.setText(historialTexto);
        
        // Si ya hay un total previo, lo mostramos al final
        if (totalCuenta > 0) {
            areaTexto.append("\n>>> TOTAL ACTUAL: $" + String.format("%.2f", totalCuenta));
        }

        JScrollPane scrollPane = new JScrollPane(areaTexto);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Detalle del Pedido"));

        this.add(Mpanel, BorderLayout.NORTH);     
        this.add(scrollPane, BorderLayout.CENTER); 

        this.setVisible(true);
    }

    // MÉTODO ESTÁTICO CORREGIDO
    public static void agregarAlPedido(String producto, double precio) {
        totalCuenta += precio;
        // Guardamos el nuevo producto en el historial para que no se pierda al cerrar la ventana
        historialTexto += "\n" + String.format("%-20s $%.2f", producto, precio);
        
        // Si la ventana está abierta en este momento, actualizamos el JTextArea
        if (areaTexto != null) {
            areaTexto.setText(historialTexto);
            areaTexto.append("\n---------------------------------------");
            areaTexto.append("\n>>> TOTAL DE LA CUENTA: $" + String.format("%.2f", totalCuenta));
            areaTexto.setCaretPosition(areaTexto.getDocument().getLength());
        }
    }

    private JButton crearBotonConImagen(String texto, String ruta) {
        java.net.URL url = getClass().getResource(ruta);
        if (url != null) {
            ImageIcon icono = new ImageIcon(url);
            Image img = icono.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            JButton boton = new JButton(texto, new ImageIcon(img));
            boton.setVerticalTextPosition(SwingConstants.BOTTOM);
            boton.setHorizontalTextPosition(SwingConstants.CENTER);
            return boton;
        }
        return new JButton(texto);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainMenu::new);
    }
}