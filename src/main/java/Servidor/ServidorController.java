package Servidor;

import gpt2.Servidor;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class ServidorController {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        
        try {
            serverSocket = new ServerSocket(12345);
            System.out.println("Servidor iniciado.");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado: " + clientSocket.getInetAddress());

                Thread clientThread = new ServidorTCP(clientSocket);
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
     }
}
