/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servidor;

import Cliente.RespostaServico;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author William
 */
public class Servidor {
     private static final int CADASTRAR = 1;
    private static final int SOLICITAR_DIAGNOSTICO = 2;
    private static final int SOLICITAR_CASOS_ARMAZENADOS = 3;
    private static final int PORTA_SERVICO = 2000;
    
    public static void main(String[] args){
        ServerSocket serverSocket = null;
        BaseDeDados baseDeDados;
        Socket socket = null;
        
        SolicitacaoServico solicitacao = null;
        Object resposta;
        
        ObjectOutputStream objectOutputStream;
        ObjectInputStream objectInputStream;
        
        //Criar base de dados
        baseDeDados = new BaseDeDados();
        
        //Criar o ponto de transporte para conexão
        try{
            serverSocket = new ServerSocket(PORTA_SERVICO);
        } catch (IOException e){
            System.out.println(e.getMessage());
            return;
        }
        
        //Permanecer prestando o serviço
        while(true){
            try{
                socket = serverSocket.accept();
            }catch (IOException e){
                System.out.println(e.getMessage());
                return;
            }
            
           //Criar os Streams para entrada e saída
            try{
                objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                objectInputStream = new ObjectInputStream(socket.getInputStream());
            } catch(IOException e){
                System.out.println(e.getMessage());
                return ;
            }
            
            //Aguardar a recpção da solicitação
            try{
                try {
                    solicitacao = (SolicitacaoServico) objectInputStream.readObject();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch(IOException e){
                System.out.println(e.getMessage());
                return ;
            }
            
            resposta = new RespostaServico();
            
            if(solicitacao.getCodigo() == CADASTRAR){  //cadastrar 
                baseDeDados.cadastrarRegistroMedico(solicitacao.getDiagnostico(), solicitacao.getSintomas());
                ((RespostaServico) resposta).setMsg("Cadastro Realizado com sucesso!!");
            } else if(solicitacao.getCodigo() == SOLICITAR_DIAGNOSTICO){
                ((RespostaServico) resposta).setMsg("Solicitar ");
            } else if(solicitacao.getCodigo() == SOLICITAR_CASOS_ARMAZENADOS){
                
            }
            
            //Enviar o objeto a resposta
            try{
                objectOutputStream.writeObject(resposta);
                
            } catch(IOException e){
                System.out.println(e.getMessage());
                return ;
            }
            
        }
    }
}
