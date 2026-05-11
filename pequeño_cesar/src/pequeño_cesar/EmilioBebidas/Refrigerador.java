package pequeño_cesar.EmilioBebidas;

import java.util.ArrayList;
import java.util.List;

public class Refrigerador {
    // EL TRUCO: Al ser static, los cambios se mantienen aunque cierres la ventana
    public static List<Bebidas> listaBebidas = new ArrayList<>();

    public Refrigerador() {
        // Solo llenamos la lista si está vacía (la primera vez que abres el programa)
        if (listaBebidas.isEmpty()) {
            listaBebidas.add(new Bebidas("Fanta", "Refresco", 50, 20.0));
            listaBebidas.add(new Bebidas("Limonada", "Agua", 20, 15.5));
            listaBebidas.add(new Bebidas("Tinto", "Vino", 60, 60.5));
        }
    }

    public List<Bebidas> getListaBebidas() {
        return listaBebidas;
    }
}