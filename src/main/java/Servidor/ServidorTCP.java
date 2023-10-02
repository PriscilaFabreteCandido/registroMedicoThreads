package Servidor;

import Cliente.RespostaServico;
import gpt2.Consulta;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ServidorTCP extends Thread{
    private Socket clientSocket;
    
    public ServidorTCP(Socket socket) {
        this.clientSocket = socket;
    }
    
    @Override
    public void run() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
            System.out.println("Objeto recebido no servidor.");
            
            SolicitacaoServico solitacao = (SolicitacaoServico) objectInputStream.readObject();
            System.out.println("Objeto lido pelo servidor.");
            //String diagnostico = consultaRecebida.getDiagnostico();

            //ArrayList<String> consulta = (ArrayList<String>) objectInputStream.readObject();

            RespostaServico resposta = new RespostaServico();
            resposta.setMsg("Deu certto");
            


            ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            System.out.println("Objeto saindo do servidor.");
            
            objectOutputStream.writeObject(resposta);
            System.out.println( "Objeto escrito e enviado do servidor para o cliente\n");
            
            //objectInputStream.close();
            //objectOutputStream.close();
            //clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } 
         catch (ClassNotFoundException e) {
            e.printStackTrace();
        } 
    }

  
}