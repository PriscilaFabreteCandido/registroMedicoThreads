package Servidor;

import java.util.ArrayList;

public class Database {
    private ArrayList<ArrayList<String>> listaConsultas = new ArrayList<>();

    public Database() {
    }
    
    
    public void armazenarConsulta(ArrayList<String> consulta){
        listaConsultas.add(consulta);
    }
    
    public void diagnosticoAutomatico(){
        String teste = AlgoritmoApriori.recomendacaoDiagnostico();
    }
    
    public void retornarCasos(){
        
    }
    
    
}
