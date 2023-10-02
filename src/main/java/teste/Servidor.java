package teste;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Servidor {

    private static Map<String, List<String>> registros = new HashMap<>();

    public static void main(String[] args) {
        int porta = 12345;

        try (ServerSocket servidorSocket = new ServerSocket(porta)) {
            System.out.println("Servidor está esperando por conexões na porta " + porta);

            while (true) {
                Socket clienteSocket = servidorSocket.accept();
                new ClienteHandler(clienteSocket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClienteHandler extends Thread {
        private Socket clienteSocket;

        public ClienteHandler(Socket socket) {
            this.clienteSocket = socket;
        }

        @Override
        public void run() {
            try (
                ObjectOutputStream out = new ObjectOutputStream(clienteSocket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(clienteSocket.getInputStream())
            ) {
                while (true) {
                    Object obj = in.readObject();
                    if (obj instanceof Consulta) {
                        Consulta consulta = (Consulta) obj;
                        salvarConsulta(consulta);
                    } else if (obj instanceof String) {
                        String doenca = (String) obj;
                        List<String> casos = obterCasosPorDoenca(doenca);
                        out.writeObject(casos);
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

   private void salvarConsulta(Consulta consulta) {
    String diagnostico = consulta.getDiagnostico();
    List<String> sintomas = registros.get(diagnostico);
    if (sintomas == null) {
        sintomas = new ArrayList<>();
        registros.put(diagnostico, sintomas);
    }
    for (String sintoma : consulta.getSintomas()) {
        sintomas.add(sintoma);
    }
    System.out.println("Consulta registrada: " + consulta.toString());
}



        private List<String> obterCasosPorDoenca(String doenca) {
            return registros.getOrDefault(doenca, new ArrayList<>());
        }
    }
}
