package biblioteca;

import javax.swing.*;
import java.util.LinkedList;

public class TelaPrincipal extends JFrame {

    LinkedList<Funcionario> funcionarios = new LinkedList<>();
    LinkedList<Aluno> alunos = new LinkedList<>();
    LinkedList<Professor> professores = new LinkedList<>();
    LinkedList<Obra> obras = new LinkedList<>();

    public TelaPrincipal() {
        super("Sistema de Biblioteca");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(null);

        JButton btnCadastros = new JButton("Cadastros");
        btnCadastros.setBounds(50, 30, 130, 30);
        btnCadastros.addActionListener(e -> new TelaCadastro(alunos, professores, funcionarios, obras));
        add(btnCadastros);

        JButton btnConsultas = new JButton("Consultas");
        btnConsultas.setBounds(200, 30, 130, 30);
        btnConsultas.addActionListener(e -> new TelaConsultas(alunos, professores, obras));
        add(btnConsultas);

        JButton btnEmprestimos = new JButton("Empréstimos");
        btnEmprestimos.setBounds(50, 80, 130, 30);
        btnEmprestimos.addActionListener(e -> new TelaEmprestimo(alunos, professores, funcionarios, obras)); // sustituir por new Tela"DeAlgumaCoisa"(parametros) quando criar a nova java class
        add(btnEmprestimos);

        JButton btnDevolucoes = new JButton("Devoluções");
        btnDevolucoes.setBounds(200, 80, 130, 30);
        btnDevolucoes.addActionListener(e -> JOptionPane.showMessageDialog(this, "Tela de Devoluções")); // sustituir por new Tela"De AlgumaCoisa"(parametros) quando criar a nova java class
        add(btnDevolucoes);

        JButton btnRelatorios = new JButton("Relatórios");
        btnRelatorios.setBounds(50, 130, 130, 30);
        btnRelatorios.addActionListener(e -> JOptionPane.showMessageDialog(this, "Tela de Relatórios")); // sustituir
                                                                                                         // por new
                                                                                                         // Tela"DeAlgumaCoisa"(parametros)
                                                                                                         // quando criar
                                                                                                         // a nova java
                                                                                                         // class
        add(btnRelatorios);

        JButton btnPenalidades = new JButton("Penalidades");
        btnPenalidades.setBounds(200, 130, 130, 30);
        btnPenalidades.addActionListener(e -> JOptionPane.showMessageDialog(this, "Tela de Penalidades")); // sustituir
                                                                                                           // por new
                                                                                                           // Tela"DeAlgumaCoisa"(parametros)
                                                                                                           // quando
                                                                                                           // criar a
                                                                                                           // nova java
                                                                                                           // class
        add(btnPenalidades);

        setVisible(true);
    }

    public static void main(String[] args) {
        TelaPrincipal tela = new TelaPrincipal();
        tela.setVisible(true);
    }
}
