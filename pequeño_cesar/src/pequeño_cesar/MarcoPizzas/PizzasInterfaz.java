package pequeño_cesar.MarcoPizzas;

import javax.swing.*;
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

    // Constructor de la ventana
    public PizzasInterfaz() {
        
        // Configuración básica
        setTitle("Módulo de Pizzas - Hot-N-Ready");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Título
        JLabel lblTitulo = new JLabel("SELECCIÓN DE PIZZAS", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(Color.RED);
        add(lblTitulo, BorderLayout.NORTH);

        // Panel Central (Botones y Almacén)
        JPanel panelCentral = new JPanel(new GridLayout(3, 3, 10, 10));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JButton btnPepp = new JButton("Vender Pepperoni ($89)");
        lblStockPepp = new JLabel("Disponibles: " + pzPepperoni.getCantidadEnAlmacen(), SwingConstants.CENTER);
        JButton btnHornearPepp = new JButton("Hornear +5");

        JButton btnQueso = new JButton("Vender Queso ($89)");
        lblStockQueso = new JLabel("Disponibles: " + pzQueso.getCantidadEnAlmacen(), SwingConstants.CENTER);
        JButton btnHornearQueso = new JButton("Hornear +5");

        JButton btnHaw = new JButton("Vender Hawaiana ($109)");
        lblStockHaw = new JLabel("Disponibles: " + pzHawaiana.getCantidadEnAlmacen(), SwingConstants.CENTER);
        JButton btnHornearHaw = new JButton("Hornear +5");

        panelCentral.add(btnPepp); panelCentral.add(lblStockPepp); panelCentral.add(btnHornearPepp);
        panelCentral.add(btnQueso); panelCentral.add(lblStockQueso); panelCentral.add(btnHornearQueso);
        panelCentral.add(btnHaw); panelCentral.add(lblStockHaw); panelCentral.add(btnHornearHaw);

        add(panelCentral, BorderLayout.CENTER);

        // Panel Inferior (Ticket)
        areaPedido = new JTextArea(8, 30);
        areaPedido.setEditable(false);
        areaPedido.setFont(new Font("Monospaced", Font.PLAIN, 14));
        areaPedido.setText("--- Registro de Ventas ---\n");
        JScrollPane scrollPane = new JScrollPane(areaPedido);
        add(scrollPane, BorderLayout.SOUTH);

        // Acciones de los botones
        btnPepp.addActionListener(e -> despacharPizza(pzPepperoni, lblStockPepp));
        btnQueso.addActionListener(e -> despacharPizza(pzQueso, lblStockQueso));
        btnHaw.addActionListener(e -> despacharPizza(pzHawaiana, lblStockHaw));

        btnHornearPepp.addActionListener(e -> hornearPizzas(pzPepperoni, lblStockPepp));
        btnHornearQueso.addActionListener(e -> hornearPizzas(pzQueso, lblStockQueso));
        btnHornearHaw.addActionListener(e -> hornearPizzas(pzHawaiana, lblStockHaw));
    }

    // Métodos de acción
    private void despacharPizza(Pizza pizza, JLabel etiquetaStock) {
        if (pizza.despachar()) {
            areaPedido.append("✔️ 1x Pizza " + pizza.getEspecialidad() + " despachada. [$" + pizza.getPrecio() + "]\n");
            etiquetaStock.setText("Disponibles: " + pizza.getCantidadEnAlmacen());
        } else {
            JOptionPane.showMessageDialog(this, 
                "¡Se acabaron las pizzas de " + pizza.getEspecialidad() + "!\nManda a hornear más.", 
                "Sin inventario", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void hornearPizzas(Pizza pizza, JLabel etiquetaStock) {
        pizza.hornearNuevas(5);
        areaPedido.append("🔥 ¡Ding! Salieron 5 pizzas de " + pizza.getEspecialidad() + " del horno.\n");
        etiquetaStock.setText("Disponibles: " + pizza.getCantidadEnAlmacen());
    }

    // Método Main
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PizzasInterfaz ventana = new PizzasInterfaz();
            ventana.setVisible(true);
        });
    }
}