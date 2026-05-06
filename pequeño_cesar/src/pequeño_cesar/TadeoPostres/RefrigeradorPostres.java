package pequeño_cesar.TadeoPostres;

import java.util.List;
import java.util.ArrayList; 

public class RefrigeradorPostres {
    private List<Postres> listaPostres;

    public RefrigeradorPostres() {
        this.listaPostres = new ArrayList<>();
       
        listaPostres.add(new Postres("Flan", 25, 120.00));
        
     
        listaPostres.add(new Postres("Pastel", 20, 150.00));
        
        
        listaPostres.add(new Postres("Gelatina",  35, 130.00));
    }

	public List<Postres> getListaPostres() {
		return listaPostres;
	}

	public void setListaBebidas(List<Postres> listaPostres) {
		this.listaPostres = listaPostres;
	}
    
    
}