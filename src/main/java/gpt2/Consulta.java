/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gpt2;

import java.io.Serializable;

public class Consulta implements Serializable {
    private String[] sintomas;
    private String diagnostico;

    public Consulta(String[] sintomas, String diagnostico) {
        this.sintomas = sintomas;
        this.diagnostico = diagnostico;
    }

    public String[] getSintomas() {
        
        return sintomas;
    }

    public String getDiagnostico() {
        return diagnostico;
    }
}
