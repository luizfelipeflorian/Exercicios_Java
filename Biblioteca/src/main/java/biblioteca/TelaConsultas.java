package biblioteca;

import javax.swing.*;
import java.util.LinkedList;

class TelaConsultas extends JFrame {

    public TelaConsultas(LinkedList<Aluno> alunos, LinkedList<Professor> professores, LinkedList<Obra> obras) {
        super("Consultas");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(null);

        JButton btnUsuarios = new JButton("Consultar Usuários");
        btnUsuarios.setBounds(50, 30, 280, 40);
        btnUsuarios.addActionListener(e -> {
            StringBuilder sb = new StringBuilder();
            sb.append("ID | Nome | Situação dos emprestimo").append("\n");
            LinkedList<Usuario> usuarios = new LinkedList<>();
            usuarios.addAll(alunos);
            usuarios.addAll(professores);
            for (Usuario usuario : usuarios) {
                sb.append(usuario).append("\n");
            }
            JOptionPane.showMessageDialog(this, usuarios.size() > 0 ? sb.toString() : "Nenhum Usuario cadastrado.");
        });
        add(btnUsuarios);

        JButton btnObras = new JButton("Consultar Obras");
        btnObras.setBounds(50, 100, 280, 40);
        btnObras.addActionListener(e -> {
            StringBuilder sb = new StringBuilder();
            sb.append("ID | Título | Autor | Tipo | Situação dos Exemplares").append("\n");
            for (Obra obra : obras) {
                sb.append(obra).append("\n");
            }
            JOptionPane.showMessageDialog(this, obras.size() > 0 ? sb.toString() : "Nenhuma obra cadastrada.");
        });
        add(btnObras);

        setVisible(true);
    }
}
