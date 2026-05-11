package pequeño_cesar.MarcoPizzas;

import javax.swing.*;

import pequeno_cesar.MainMenu;

import java.awt.*;

public class PizzasInterfaz extends JFrame {

    // Inventario inicial
    private Pizza pzPepperoni = new Pizza("Pepperoni", 89.00, 5);
    private Pizza pzQueso = new Pizza("Queso", 89.00, 3);
    private Pizza pzHawaiana = new Pizza("Hawaiana", 109.00, 2);

    // Elementos de la interfaz
    private JLabel lblStockPepp;
    private JLabel lblStockQueso;
    private JLabel lblStockHaw;
    private JTextArea areaPedido;
    
    private double cantidadApagar = 0.0;

    public PizzasInterfaz() {
        // --- CONFIGURACIÓN BÁSICA ---
        setTitle("Módulo de Pizzas - Hot-N-Ready");
        setSize(700, 600); // Un poco más grande para que quepa todo bien
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // --- PANEL SUPERIOR (Encabezado con Botón Regresar) ---
        JPanel panelNorte = new JPanel(new BorderLayout());
        
        // Botón Regresar
        JButton btnReg = new JButton("← Regresar");
        btnReg.addActionListener(e -> {
            new MainMenu(); // Abre el menú principal
            this.dispose(); // Cierra esta ventana
        });
        
        // Panel pequeño para que el botón no se estire
        JPanel panelBotonReg = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBotonReg.add(btnReg);

        // Título Central
        JLabel lblTitulo = new JLabel("SELECCIÓN DE PIZZAS", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(Color.RED);

        panelNorte.add(panelBotonReg, BorderLayout.WEST);
        panelNorte.add(lblTitulo, BorderLayout.CENTER);
        // Espaciador a la derecha para que el título quede centrado realmente
        panelNorte.add(new Box.Filler(new Dimension(100, 0), new Dimension(100, 0), new Dimension(100, 0)), BorderLayout.EAST);
        
        add(panelNorte, BorderLayout.NORTH);

        // --- PANEL CENTRAL (Ventas y Almacén) ---
        JPanel panelCentral = new JPanel(new GridLayout(3, 3, 10, 10));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Fila Pepperoni
        JButton btnPepp = new JButton("Vender Pepperoni ($89)");
        lblStockPepp = new JLabel("Disponibles: " + pzPepperoni.getCantidadEnAlmacen(), SwingConstants.CENTER);
        JButton btnHornearPepp = new JButton("Hornear +5");

        // Fila Queso
        JButton btnQueso = new JButton("Vender Queso ($89)");
        lblStockQueso = new JLabel("Disponibles: " + pzQueso.getCantidadEnAlmacen(), SwingConstants.CENTER);
        JButton btnHornearQueso = new JButton("Hornear +5");

        // Fila Hawaiana
        JButton btnHaw = new JButton("Vender Hawaiana ($109)");
        lblStockHaw = new JLabel("Disponibles: " + pzHawaiana.getCantidadEnAlmacen(), SwingConstants.CENTER);
        JButton btnHornearHaw = new JButton("Hornear +5");

        panelCentral.add(btnPepp); panelCentral.add(lblStockPepp); panelCentral.add(btnHornearPepp);
        panelCentral.add(btnQueso); panelCentral.add(lblStockQueso); panelCentral.add(btnHornearQueso);
        panelCentral.add(btnHaw); panelCentral.add(lblStockHaw); panelCentral.add(btnHornearHaw);

        add(panelCentral, BorderLayout.CENTER);

        // --- PANEL INFERIOR (Registro de Ventas) ---
        areaPedido = new JTextArea(10, 30);
        areaPedido.setEditable(false);
        areaPedido.setFont(new Font("Monospaced", Font.PLAIN, 14));
        areaPedido.setText("--- Registro de Ventas ---\n");
        JScrollPane scrollPane = new JScrollPane(areaPedido);
        add(scrollPane, BorderLayout.SOUTH);

        // --- ACCIONES DE LOS BOTONES ---
        btnPepp.addActionListener(e -> despacharPizza(pzPepperoni, lblStockPepp));
        btnQueso.addActionListener(e -> despacharPizza(pzQueso, lblStockQueso));
        btnHaw.addActionListener(e -> despacharPizza(pzHawaiana, lblStockHaw));

        btnHornearPepp.addActionListener(e -> hornearPizzas(pzPepperoni, lblStockPepp));
        btnHornearQueso.addActionListener(e -> hornearPizzas(pzQueso, lblStockQueso));
        btnHornearHaw.addActionListener(e -> hornearPizzas(pzHawaiana, lblStockHaw));

        // HACER VISIBLE LA VENTANA
        this.setVisible(true);
    }

    private void despacharPizza(Pizza pizza, JLabel etiquetaStock) {
        if (pizza.despachar()) {
            this.cantidadApagar += pizza.getPrecio();
            areaPedido.append("✔️ 1x " + pizza.getEspecialidad() + " [$" + pizza.getPrecio() + "]\n");
            areaPedido.append("   Total acumulado: $" + cantidadApagar + "\n");
            etiquetaStock.setText("Disponibles: " + pizza.getCantidadEnAlmacen());
        } else {
            JOptionPane.showMessageDialog(this, 
                "¡Se acabaron las pizzas de " + pizza.getEspecialidad() + "!", 
                "Sin stock", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void hornearPizzas(Pizza pizza, JLabel etiquetaStock) {
        pizza.hornearNuevas(5);
        areaPedido.append("🔥 Se hornearon 5 pizzas de " + pizza.getEspecialidad() + "\n");
        etiquetaStock.setText("Disponibles: " + pizza.getCantidadEnAlmacen());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PizzasInterfaz());
    }
}