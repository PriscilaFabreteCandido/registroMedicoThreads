package teste;

import java.util.ArrayList;
import java.util.List;

public class Teste2 {
    public static void main(String[] args) {
        ArrayList<ArrayList<String>> grupos = new ArrayList<>();
        ArrayList<String> grupo1 = new ArrayList<>();
        grupo1.add("Leite");
        grupo1.add("Pão");
        grupo1.add("Bolacha");
        grupo1.add("Suco");

        ArrayList<String> grupo2 = new ArrayList<>();
        grupo2.add("Leite");
        grupo2.add("Suco");

        ArrayList<String> grupo3 = new ArrayList<>();
        grupo3.add("Leite");
        grupo3.add("Ovos");

        ArrayList<String> grupo4 = new ArrayList<>();
        grupo4.add("Pão");
        grupo4.add("Bolacha");
        grupo4.add("Café");

        grupos.add(grupo1);
        grupos.add(grupo2);
        grupos.add(grupo3);
        grupos.add(grupo4);

        // Mínimo de suporte (support threshold) para considerar um item como frequente
        int minSuporte = 2;

        // Encontre os itens frequentes
        List<String> itensFrequentes = encontrarItensFrequentes(grupos, minSuporte);

        // Exiba os itens frequentes
        System.out.println("Itens frequentes:");
        for (String item : itensFrequentes) {
            System.out.println(item);
        }

        // Gere e mostre as duplas candidatas
        System.out.println("\nDuplas candidatas:");
        List<String> duplasCandidatas = gerarDuplasCandidatas(itensFrequentes);
        for (String dupla : duplasCandidatas) {
            System.out.println(dupla);
        }

        // Gere novamente os itens frequentes a partir das duplas candidatas
        System.out.println("\nItens frequentes a partir das duplas candidatas:");
        List<String> itensFrequentesDasDuplas = encontrarItensFrequentesDasDuplas(grupos, duplasCandidatas, minSuporte);
        for (String item : itensFrequentesDasDuplas) {
            System.out.println(item);
        }
    }

    private static List<String> encontrarItensFrequentes(ArrayList<ArrayList<String>> grupos, int minSuporte) {
        List<String> itensFrequentes = new ArrayList<>();
        List<String> todosItens = new ArrayList<>();

        // Recolher todos os itens
        for (ArrayList<String> grupo : grupos) {
            todosItens.addAll(grupo);
        }

        // Identificar itens únicos
        List<String> itensUnicos = new ArrayList<>();
        for (String item : todosItens) {
            if (!itensUnicos.contains(item)) {
                itensUnicos.add(item);
            }
        }

        // Contar a frequência de cada item
        for (String item : itensUnicos) {
            int frequencia = contarFrequencia(item, todosItens);
            if (frequencia >= minSuporte) {
                itensFrequentes.add(item);
            }
        }

        return itensFrequentes;
    }

    private static List<String> gerarDuplasCandidatas(List<String> itensFrequentes) {
        List<String> duplasCandidatas = new ArrayList<>();

        for (int i = 0; i < itensFrequentes.size(); i++) {
            for (int j = i + 1; j < itensFrequentes.size(); j++) {
                String item1 = itensFrequentes.get(i);
                String item2 = itensFrequentes.get(j);
                duplasCandidatas.add(item1 + ", " + item2);
            }
        }

        return duplasCandidatas;
    }
    
      private static int contarFrequencia(String item, List<String> todosItens) {
        int frequencia = 0;
        for (String i : todosItens) {
            if (item.equals(i)) {
                frequencia++;
            }
        }
        return frequencia;
    }
    private static List<String> encontrarItensFrequentesDasDuplas(ArrayList<ArrayList<String>> grupos, List<String> duplasCandidatas, int minSuporte) {
        List<String> itensFrequentesDasDuplas = new ArrayList<>();

        for (String dupla : duplasCandidatas) {
            int frequencia = contarFrequenciaDupla(dupla, grupos);
            if (frequencia >= minSuporte) {
                itensFrequentesDasDuplas.add(dupla);
            }
        }

        return itensFrequentesDasDuplas;
    }

    private static int contarFrequenciaDupla(String dupla, ArrayList<ArrayList<String>> grupos) {
        int frequencia = 0;

        String[] itens = dupla.split(", ");
        for (ArrayList<String> grupo : grupos) {
            boolean todosPresentes = true;
            for (String item : itens) {
                if (!grupo.contains(item)) {
                    todosPresentes = false;
                    break;
                }
            }
            if (todosPresentes) {
                frequencia++;
            }
        }

        return frequencia;
    }
}
