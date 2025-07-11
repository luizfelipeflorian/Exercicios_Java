package calculadora;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TelaPrincipal extends JFrame {

    public TelaPrincipal() {
        setTitle("Calculadora - Tela Principal");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton btnSomar = new JButton("Somar");
        JButton btnSubtrair = new JButton("Subtrair");
        JButton btnMultiplicar = new JButton("Multiplicar");
        JButton btnDividir = new JButton("Dividir");

        btnSomar.addActionListener(e -> {
            TelaSomar tela = new TelaSomar();
            tela.setVisible(true);
        });
        btnSubtrair.addActionListener(e -> {
            TelaSubtrair tela = new TelaSubtrair();
            tela.setVisible(true);
        });
        btnMultiplicar.addActionListener(e -> {
            TelaMultiplicar tela = new TelaMultiplicar();
            tela.setVisible(true);
        });
        btnDividir.addActionListener(e -> {
            TelaDividir tela = new TelaDividir();
            tela.setVisible(true);
        });

        JPanel painel = new JPanel(new GridLayout(2, 2, 10, 10));
        painel.add(btnSomar);
        painel.add(btnSubtrair);
        painel.add(btnMultiplicar);
        painel.add(btnDividir);

        add(painel, BorderLayout.CENTER);
    }
}
