package got;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        Consulta consulta = new Consulta();
        consulta.adicionarItem("Item 1");
        consulta.adicionarItem("Item 2");

        try {
            Socket socket = new Socket("localhost", 12345);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

            out.writeObject(consulta);

            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
