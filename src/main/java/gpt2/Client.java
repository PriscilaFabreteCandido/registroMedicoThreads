/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gpt2;

import java.io.*;
import java.net.*;
import javax.swing.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import teste.Cliente;

public class Client extends JFrame {

    private JCheckBox[] sintomas;
    private JComboBox<String> diagnosticoComboBox;
    private JButton enviarButton;

    public Client() {
        setTitle("Sistema de Registro Médico");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(9, 1));

        sintomas = new JCheckBox[7];
        for (int i = 0; i < sintomas.length; i++) {
            sintomas[i] = new JCheckBox("S" + (i + 1));
            panel.add(sintomas[i]);
        }

        diagnosticoComboBox = new JComboBox<>(new String[]{"D1", "D2", "D3"});
        panel.add(new JLabel("Diagnóstico:"));
        panel.add(diagnosticoComboBox);

        enviarButton = new JButton("Enviar Consulta");
        panel.add(enviarButton);

        add(panel);

        enviarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enviarConsulta();
            }
        });
    }

    private void enviarConsulta() {
        String[] selectedSintomas = new String[7];
        for (int i = 0; i < sintomas.length; i++) {
            if (sintomas[i].isSelected()) {
                selectedSintomas[i] = "S" + (i + 1);
            }
        }

        String diagnostico = diagnosticoComboBox.getSelectedItem().toString();

        Consulta consulta = new Consulta(selectedSintomas, diagnostico);

        try {
            Socket socket = new Socket("localhost", 12345);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            out.writeObject(consulta);

            String resposta = (String) in.readObject();
            JOptionPane.showMessageDialog(this, resposta);

            out.close();
            in.close();
            socket.close();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Client cliente = new Client();
                cliente.setVisible(true);
            }
        });
    }
}

