/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tela;

import javax.swing.*;
import java.awt.*;

public class Tela2 extends JFrame{
    public Tela2(String nome){
        setTitle("Tela 2 - Saudação");
        setSize(300,150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JLabel label = new JLabel("Olá, " + nome + "!",JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        
        add(label, BorderLayout.CENTER);
    }
}