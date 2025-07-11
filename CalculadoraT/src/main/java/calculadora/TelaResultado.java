package calculadora;

import javax.swing.*;
import java.awt.*;

public class TelaResultado extends JFrame{
    public TelaResultado(double resultado) {
        setTitle("Calculadora - Resultado");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JLabel lblResultado = new JLabel("Resultado: " + resultado, JLabel.CENTER);
        lblResultado.setFont(new Font("Arial", Font.BOLD, 10));
        
        add(lblResultado, BorderLayout.CENTER);
    }
}
