package pequeno_cesar;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class VentanaHistorial extends JFrame {

	private static final long serialVersionUID = 1L;

	public VentanaHistorial() {
        setTitle("Historial de Ventas");
        setSize(400, 300); 
        setLayout(null);   
        setLocationRelativeTo(null);

        JTextArea areaTexto = new JTextArea();
        areaTexto.setEditable(false); 

        JScrollPane scroll = new JScrollPane(areaTexto);
        scroll.setBounds(20, 20, 340, 180); 
        add(scroll);

        JButton btnRegresar = new JButton("Regresar");
        btnRegresar.setBounds(140, 215, 100, 30);
        add(btnRegresar);

        btnRegresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); 
            }
        });

        GestorHistorial gestor = new GestorHistorial();
        List<String> ventas = gestor.obtenerVentas();

        if (ventas.size() == 0) {
            areaTexto.setText("Aún no hay ventas.");
        } else {
            for (int i = 0; i < ventas.size(); i++) {
                areaTexto.append(ventas.get(i) + "\n"); 
            }
        }
    }
}