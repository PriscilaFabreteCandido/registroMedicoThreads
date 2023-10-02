package teste;


import java.util.ArrayList;
import java.util.List;

public class Apriori {
    public static void main(String[] args) {
        ArrayList<String> consulta1 = new ArrayList<>();
        consulta1.add("S1");
        consulta1.add("S2");
        consulta1.add("S3");
        consulta1.add("D3");
        
        System.out.println(consulta1.get(consulta1.size() - 1));
        
        
        String[] compra1 = new String[]{"Leite", "Pão", "Bolacha", "Suco"};
        String[] compra2 = new String[]{"Leite", "Suco"};
        String[] compra3 = new String[]{"Leite", "Ovos"};
        String[] compra4 = new String[]{"Pão", "Bolacha", "Café"};
       
        double frequenciaMinima = 2;
        
        ArrayList<String> itensFrequentes = new ArrayList<>();
        ArrayList<String[]> grupos = new ArrayList<>();
  
        grupos.add(compra1);
        grupos.add(compra2);
        grupos.add(compra3);
        grupos.add(compra4);
    
        
        
        
        
    }
}
