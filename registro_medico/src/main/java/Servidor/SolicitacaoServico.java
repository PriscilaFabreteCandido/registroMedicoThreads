/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author prisc
 */
public class SolicitacaoServico implements Serializable{
     private int codigo;
    private ArrayList<String> sintomas;
    private String diagnostico;
    
    public SolicitacaoServico(ArrayList<String> sintomas, String diagnostico, int codigo) {
        this.sintomas = sintomas;
        this.diagnostico = diagnostico;
        this.codigo = codigo;
    }
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    public ArrayList<String> getSintomas() {
        return sintomas;
    }

    public void setSintomas(ArrayList<String> sintomas) {
        this.sintomas = sintomas;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }
}
