/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import Servidor.SolicitacaoServico;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author 2021122760232
 */
public class Cliente {

    public Cliente() {
    }
    
    public RespostaServico enviarRequisicao(SolicitacaoServico solicitacao){
        try {
            Socket socket = new Socket("localhost", 12345);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            out.writeObject(solicitacao);
            
            RespostaServico resposta = (RespostaServico) in.readObject();
            
            out.close();
            in.close();
           // socket.close();
            return (RespostaServico) resposta;
        } catch (IOException | ClassNotFoundException ex) {
            return null;
        }
        
        
    }
}
