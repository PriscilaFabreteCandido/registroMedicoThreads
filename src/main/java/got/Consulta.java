package got;

import java.io.Serializable;
import java.util.ArrayList;

public class Consulta implements Serializable {
    private ArrayList<String> itens;

    public Consulta() {
        itens = new ArrayList<>();
    }

    public void adicionarItem(String item) {
        itens.add(item);
    }

    public ArrayList<String> getItens() {
        return itens;
    }
}
