/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gpt2;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author William
 */
public class teste {
    public static void main(String[] args) throws UnknownHostException, IOException {
        String server_name = InetAddress.getLocalHost().getHostName();
        String server_address = InetAddress.getByName(server_name).getHostAddress();

        Socket socket = new Socket(InetAddress.getByName(server_name), 2444);

    }
}
