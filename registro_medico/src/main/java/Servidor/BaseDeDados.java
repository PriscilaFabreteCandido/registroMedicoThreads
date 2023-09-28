/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import java.awt.List;
import java.util.ArrayList;

/**
 *
 * @author prisc
 */
public class BaseDeDados {
    
    private ArrayList<String> diagnosticos;
    private ArrayList<ArrayList<String>> sintomas;
    
    public BaseDeDados() {
        diagnosticos = new ArrayList<String>();
        sintomas = new ArrayList<ArrayList<String>>();
    }
    
    public void cadastrarRegistroMedico(String diagnostico, ArrayList<String> sintomasList){
        if(this.diagnosticos != null&& this.sintomas != null){
            this.diagnosticos.add(diagnostico);
            this.sintomas.add(sintomasList);
        }
    }
}
