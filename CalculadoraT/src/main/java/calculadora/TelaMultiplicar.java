/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calculadora;

import javax.swing.*;
import java.awt.*;

public class TelaMultiplicar extends JFrame {

    private JTextField campo1;
    private JTextField campo2;
    private JButton btnResultado;

    public TelaMultiplicar() {
        setTitle("Calculadora - Tela Multiplicar");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        campo1 = new JTextField(10);
        campo2 = new JTextField(10);
        btnResultado = new JButton("Resultado");

        btnResultado.addActionListener(e -> calcularMultiplicar());

        JPanel painel = new JPanel();
        painel.add(new JLabel("Número 1:"));
        painel.add(campo1);
        painel.add(new JLabel("Número 2:"));
        painel.add(campo2);
        painel.add(btnResultado);

        add(painel, BorderLayout.CENTER);
    }

    private void calcularMultiplicar() {
        try {
            double num1 = Double.parseDouble(campo1.getText());
            double num2 = Double.parseDouble(campo2.getText());
            double resultado = num1 * num2;

            TelaResultado telaResultado = new TelaResultado(resultado);
            telaResultado.setVisible(true);
            dispose();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Digite valores válidos.");
        }
    }
}
