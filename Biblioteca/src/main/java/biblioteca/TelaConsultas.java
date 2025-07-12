package biblioteca;

import javax.swing.*;
import java.awt.event.*;

class TelaConsultas extends JFrame {

    public TelaConsultas(ListaEncadeada<Usuario> usuarios, ListaEncadeada<Obra> obras) {
        super("Consultas");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(null);

        JButton btnUsuarios = new JButton("Consultar Usuários");
        btnUsuarios.setBounds(50, 30, 280, 40);
        btnUsuarios.addActionListener(e -> {
            StringBuilder sb = new StringBuilder();
            Node<Usuario> atual = usuarios.head;
            sb.append("ID | Nome | Situação dos emprestimo").append("\n");
            while (atual != null) {
                sb.append(atual.data).append("\n");
                atual = atual.next;
            }
            JOptionPane.showMessageDialog(this, sb.length() > 0 ? sb.toString() : "Nenhum Usuario cadastrado.");
        });
        add(btnUsuarios);

        JButton btnObras = new JButton("Consultar Obras");
        btnObras.setBounds(50, 100, 280, 40);
        btnObras.addActionListener(e -> {
            StringBuilder sb = new StringBuilder();
            Node<Obra> atual = obras.head;
            sb.append("ID | Título | Autor | Tipo | Situação dos Exemplares").append("\n");
            while (atual != null) {
                sb.append(atual.data).append("\n");
                atual = atual.next;
            }
            JOptionPane.showMessageDialog(this, sb.length() > 0 ? sb.toString() : "Nenhuma obra cadastrada.");
        });
        add(btnObras);

        setVisible(true);
    }
}
