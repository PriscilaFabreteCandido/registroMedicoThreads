package teste;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Cliente {

    private static Socket socket;
    private static ObjectOutputStream out;
    private static ObjectInputStream in;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Registro Médico");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JTextArea sintomasArea = new JTextArea(10, 20);
        JTextField diagnosticoField = new JTextField(20);
        JButton enviarButton = new JButton("Enviar Consulta");
        JButton consultarButton = new JButton("Consultar Casos");

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));
        inputPanel.add(new JLabel("Sintomas (separados por vírgula):"));
        inputPanel.add(sintomasArea);
        inputPanel.add(new JLabel("Diagnóstico:"));
        inputPanel.add(diagnosticoField);
        inputPanel.add(enviarButton);
        inputPanel.add(consultarButton);

        frame.add(inputPanel, BorderLayout.NORTH);

        JTextArea resultadoArea = new JTextArea(10, 20);
        resultadoArea.setEditable(false);
        frame.add(new JScrollPane(resultadoArea), BorderLayout.CENTER);

        enviarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sintomas = sintomasArea.getText();
                String diagnostico = diagnosticoField.getText();
                Consulta consulta = new Consulta(sintomas.split(","), diagnostico);
                enviarConsulta(consulta);
            }
        });

        consultarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String doenca = JOptionPane.showInputDialog("Digite o diagnóstico:");
                if (doenca != null) {
                    List<String> casos = consultarCasos(doenca);
                    resultadoArea.setText("Casos para " + doenca + ":\n" + String.join("\n", casos));
                }
            }
        });

        frame.pack();
        frame.setVisible(true);

        conectarAoServidor();
    }

    private static void conectarAoServidor() {
        try {
            socket = new Socket("localhost", 12345);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void enviarConsulta(Consulta consulta) {
        try {
            out.writeObject(consulta);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<String> consultarCasos(String doenca) {
        try {
            out.writeObject(doenca);
            out.flush();
            Object obj = in.readObject();
            if (obj instanceof List) {
                return (List<String>) obj;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}

class Consulta implements Serializable {
    private String[] sintomas;
    private String diagnostico;

    public Consulta(String[] sintomas, String diagnostico) {
        this.sintomas = sintomas;
        this.diagnostico = diagnostico;
    }

    public String[] getSintomas() {
        return sintomas;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    @Override
    public String toString() {
        return "Sintomas: " + String.join(", ", sintomas) + " - Diagnóstico: " + diagnostico;
    }
}
