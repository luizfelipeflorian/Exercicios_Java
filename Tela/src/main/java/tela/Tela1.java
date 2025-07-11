/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tela;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Tela1 extends JFrame{
    private JTextField campoNome;
    private JButton botaoEnviar;
    
    public Tela1(){
        setTitle ("Tela 1 - Digite seu nome");
        setSize (300,150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        campoNome = new JTextField(20);
        botaoEnviar = new JButton("Enviar para Tela 2");
        
        botaoEnviar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String nome = campoNome.getText();
                if (!nome.isEmpty()) {
                    Tela2 tela2 = new Tela2(nome);
                    tela2.setVisible(true);
                    dispose(); //Fecha Tela 1
                }else{
                    JOptionPane.showMessageDialog(null, "Digite um nome!");
                }
            }
        });
        
        JPanel painel = new JPanel();
        painel.add(new JLabel ("Nome: "));
        painel.add(campoNome);
        painel.add(botaoEnviar);
        
        add(painel, BorderLayout.CENTER);
    }
}