package pequeno_cesar.MarcoPizzas;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import pequeno_cesar.MainMenu; // Importación necesaria
import java.awt.*;

public class PizzasInterfaz extends JFrame {

    private Pizza pzPepperoni = new Pizza("Pepperoni", 89.00, 5);
    private Pizza pzQueso = new Pizza("Queso", 89.00, 3);
    private Pizza pzHawaiana = new Pizza("Hawaiana", 109.00, 2);

    private JLabel lblStockPepp;
    private JLabel lblStockQueso;
    private JLabel lblStockHaw;
    private JTextArea areaPedido;
    
    private double cantidadApagar = 0.0;

    public void guardarEnArchivo() {
        String nombreArchivo = "pago.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo, true))) {
            writer.write("Venta Pizzas - Cantidad a pagar: $" + cantidadApagar);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    public PizzasInterfaz() {
        setTitle("Módulo de Pizzas - Hot-N-Ready");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // --- PANEL NORTE ---
        JPanel panelNorte = new JPanel(new BorderLayout());
        JButton btnReg = new JButton("← Regresar y Confirmar");
        
     // Dentro de PizzasInterfaz.java
        btnReg.addActionListener(e -> {
            if (cantidadApagar > 0) {
                // 1. Guardar en el archivo de texto
                guardarEnArchivo();
                
                // 2. ENVIAR AL MAIN MENU (Llamamos al método estático)
                // Esto actualizará la variable 'totalCuenta' que es estática
                MainMenu.agregarAlPedido("Pedido de Pizzas", cantidadApagar);
            }
            
            // 3. Regresar al menú
            // Si quieres evitar que se dupliquen ventanas, podrías usar un buscador de ventanas
            // o simplemente crear la nueva sabiendo que 'totalCuenta' es static y se mantendrá.
            new MainMenu().setVisible(true);
            this.dispose(); 
        });
        
        JPanel panelBotonReg = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBotonReg.add(btnReg);

        JLabel lblTitulo = new JLabel("SELECCIÓN DE PIZZAS", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(Color.RED);

        panelNorte.add(panelBotonReg, BorderLayout.WEST);
        panelNorte.add(lblTitulo, BorderLayout.CENTER);
        panelNorte.add(Box.createHorizontalStrut(100), BorderLayout.EAST);
        add(panelNorte, BorderLayout.NORTH);

        // --- PANEL CENTRAL ---
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

        // --- PANEL SUR ---
        areaPedido = new JTextArea(10, 30);
        areaPedido.setEditable(false);
        areaPedido.setFont(new Font("Monospaced", Font.PLAIN, 14));
        areaPedido.setText("--- Registro Temporal de Pizzas ---\n");
        JScrollPane scrollPane = new JScrollPane(areaPedido);
        add(scrollPane, BorderLayout.SOUTH);

        // --- ACCIONES ---
        btnPepp.addActionListener(e -> despacharPizza(pzPepperoni, lblStockPepp));
        btnQueso.addActionListener(e -> despacharPizza(pzQueso, lblStockQueso));
        btnHaw.addActionListener(e -> despacharPizza(pzHawaiana, lblStockHaw));

        btnHornearPepp.addActionListener(e -> hornearPizzas(pzPepperoni, lblStockPepp));
        btnHornearQueso.addActionListener(e -> hornearPizzas(pzQueso, lblStockQueso));
        btnHornearHaw.addActionListener(e -> hornearPizzas(pzHawaiana, lblStockHaw));

        this.setVisible(true);
    }

    private void despacharPizza(Pizza pizza, JLabel etiquetaStock) {
        if (pizza.despachar()) {
            this.cantidadApagar += pizza.getPrecio();
            areaPedido.append("✔️ " + pizza.getEspecialidad() + " [$" + pizza.getPrecio() + "]\n");
            etiquetaStock.setText("Disponibles: " + pizza.getCantidadEnAlmacen());
        } else {
            JOptionPane.showMessageDialog(this, "Sin stock de " + pizza.getEspecialidad());
        }
    }

    private void hornearPizzas(Pizza pizza, JLabel etiquetaStock) {
        pizza.hornearNuevas(5);
        areaPedido.append("🔥 Hornado: 5x " + pizza.getEspecialidad() + "\n");
        etiquetaStock.setText("Disponibles: " + pizza.getCantidadEnAlmacen());
    }
}