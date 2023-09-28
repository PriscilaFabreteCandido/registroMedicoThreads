/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import Servidor.SolicitacaoServico;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 *
 * @author prisc
 */
public class Cliente {
    private static final int CADASTRAR = 1;
    private static final int SOLICITAR_DIAGNOSTICO = 2;
    private static final int SOLICITAR_CASOS_ARMAZENADOS = 3;
    private static final int PORTA_SERVICO = 2000;
    
    public Cliente() {
    }
    
    public RespostaServico enviar(SolicitacaoServico solicitacao, int operacaoSolicitada) {
        String nomeServidor = "Usuario-PC";
        Socket socket = null;
        InetAddress enderecoServidor = null;
        Object resposta;
        
        ObjectOutputStream objectOutputStream;
        ObjectInputStream objectInputStream;
        
        if(operacaoSolicitada != CADASTRAR &&  operacaoSolicitada != SOLICITAR_DIAGNOSTICO &&  operacaoSolicitada != SOLICITAR_CASOS_ARMAZENADOS ){
            System.out.println("Servico não implementado");
        }
        
        //Obter o endereço associado ao Servidor
        try{
            enderecoServidor = InetAddress.getByName(nomeServidor);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
          
        //Criar ponto de transporte e conectar-se ao servidor
        try{
            socket = new Socket(enderecoServidor, PORTA_SERVICO);
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
        
        //Criar os Streams para entrada e saída
        try{
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
        } catch(IOException e){
            System.out.println(e.getMessage());
            return null;
        }
        
        //Enviar a solicitação de serviço
        try{
            objectOutputStream.writeObject(solicitacao);
        }catch(IOException e){
            System.out.println(e.getMessage());
            return null;
        }
        
        //Aguardar a resposta do servidor
        try{
            resposta = (RespostaServico) objectInputStream.readObject();
        } catch(IOException e){
            System.out.println(e.getMessage());
            return null;
        } catch(ClassNotFoundException ex){
            System.out.println(ex.getMessage());
            return null;
        }
        
        //Fechar o potno de transporte
        try{
            socket.close();
        } catch(IOException e){
            System.out.println(e.getMessage());
            return null;
        }
        
        //Apresentar a resposta
        if(resposta instanceof RespostaServico){
            return (RespostaServico) resposta;
        }
        
        return null;
    }
}
