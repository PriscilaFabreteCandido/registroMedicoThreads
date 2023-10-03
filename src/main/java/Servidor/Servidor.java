package Servidor;

import Servicos.Resposta;
import Servicos.Solicitacao;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Servidor extends Thread{
    private Socket socket;

    public Servidor(Socket socket) {
        this.socket = socket;
    }
    
    @Override
    public void run(){
        try{
            ObjectOutputStream objectOutput = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInput = new ObjectInputStream(socket.getInputStream());
            
            Solicitacao solicitacao = (Solicitacao) objectInput.readObject();
            
            Resposta respostaServidor = new Resposta();
            
            respostaServidor.setMensagem("OK, Solicitação recebida e resposta enviada de volta.");
            
            objectOutput.writeObject(respostaServidor);
            
        } catch(IOException | ClassNotFoundException mensagem){
            System.out.println("Mensagem de erro ao enviar ou receber objeto no servidor: " + mensagem);
        }
    }
}
