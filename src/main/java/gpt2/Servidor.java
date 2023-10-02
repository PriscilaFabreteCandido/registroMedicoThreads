/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gpt2;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Servidor {

    private static Map<String, List<Consulta>> registros = new HashMap<>();

    public static void main(String[] args) {
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(12345);
            System.out.println("Servidor aguardando conexões...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado: " + clientSocket.getInetAddress());

                Thread clientThread = new ClientHandler(clientSocket);
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static synchronized void adicionarConsulta(String diagnostico, Consulta consulta) {
        if (!registros.containsKey(diagnostico)) {
            registros.put(diagnostico, new ArrayList<>());
        }
        registros.get(diagnostico).add(consulta);
    }

    private static synchronized List<Consulta> consultarCasos(String diagnostico) {
        return registros.getOrDefault(diagnostico, new ArrayList<>());
    }

    static class ClientHandler extends Thread {
        private Socket clientSocket;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());

                Consulta consultaRecebida = (Consulta) objectInputStream.readObject();
                String diagnostico = consultaRecebida.getDiagnostico();

                
                String recebido = "Mensagem recebida no servidor";
                adicionarConsulta(diagnostico, consultaRecebida);

                System.out.println(recebido);

                String msgDeVolta = "Essa mensage foi enviado do servidor";
                
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
                objectOutputStream.writeObject(msgDeVolta);

                objectInputStream.close();
                objectOutputStream.close();
                clientSocket.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
