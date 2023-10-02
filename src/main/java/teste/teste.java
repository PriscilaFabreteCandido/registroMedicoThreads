package teste;

import java.util.ArrayList;

public class teste {
    public static void main(String[] args) {
        ArrayList<String> sintomasPaciente1 = new ArrayList<>();
        sintomasPaciente1.add("teste1");
        sintomasPaciente1.add("teste2");
        sintomasPaciente1.add("teste3");
        
        ArrayList<String> sintomasPaciente2 = new ArrayList<>();
        sintomasPaciente2.add("teste4");
        sintomasPaciente2.add("teste5");
        sintomasPaciente2.add("teste6");
        sintomasPaciente2.add("teste7");

        // Criando um vetor de ArrayLists
        ArrayList<ArrayList<String>> vetorDeSintomas = new ArrayList<>();

        // Adicionando os ArrayLists ao vetor
        vetorDeSintomas.add(sintomasPaciente1);
        vetorDeSintomas.add(sintomasPaciente2);

        // Acessando elementos dos ArrayLists no vetor
        for (ArrayList<String> sintomas : vetorDeSintomas) {
            System.out.println("Sintomas do paciente:");
            for (String sintoma : sintomas) {
                System.out.println(sintoma);
            }
            System.out.println(); // Separador entre os pacientes
        }
    }
}
