package biblioteca;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class TelaPrincipal extends JFrame {

    LinkedList<Funcionario> funcionarios = new LinkedList<>();
    LinkedList<Aluno> alunos = new LinkedList<>();
    LinkedList<Professor> professores = new LinkedList<>();
    LinkedList<Obra> obras = new LinkedList<>();
    LinkedList<TelaEmprestimo.Emprestimo> emprestimos = new LinkedList<>();
    LinkedList<Penalidade> penalidades = new LinkedList<>();

    private Funcionario usuarioLogado = null; // Para controle simples de acesso

    public TelaPrincipal() {
        super("Sistema de Biblioteca");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(450, 350);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        inicializarDadosTeste();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 15, 10, 15); // Define um espaço (margem ou gap) em volta do componente.
        gbc.fill = GridBagConstraints.HORIZONTAL; // Preenche horizontalmente
        gbc.weightx = 1; 

        // Cadastros
        JButton btnCadastros = new JButton("Cadastros");
        // Define a posição do botão na grade
        gbc.gridx = 0;
        gbc.gridy = 0;
        btnCadastros.addActionListener(e -> {
            if (verificarAcesso()) {
                new TelaCadastro(alunos, professores, funcionarios, obras);
            }
        });
        add(btnCadastros, gbc);

        // Consultas
        JButton btnConsultas = new JButton("Consultas");
        gbc.gridx = 1;
        gbc.gridy = 0;
        btnConsultas.addActionListener(e -> new TelaConsultas(alunos, professores, obras));
        add(btnConsultas, gbc);

        // Empréstimos
        JButton btnEmprestimos = new JButton("Empréstimos");
        gbc.gridx = 0;
        gbc.gridy = 1;
        btnEmprestimos.addActionListener(
                e -> new TelaEmprestimo(alunos, professores, funcionarios, obras, emprestimos, penalidades));
        add(btnEmprestimos, gbc);

        // Devoluções
        JButton btnDevolucoes = new JButton("Devoluções");
        gbc.gridx = 1;
        gbc.gridy = 1;
        btnDevolucoes.addActionListener(e -> new TelaDevolucao(emprestimos, penalidades));
        add(btnDevolucoes, gbc);

        // Relatórios
        JButton btnRelatorios = new JButton("Relatórios");
        gbc.gridx = 0;
        gbc.gridy = 2;
        btnRelatorios.addActionListener(e -> {
            if (verificarAcesso()) {
                new TelaRelatorio(alunos, professores, obras, emprestimos);
            }
        });
        add(btnRelatorios, gbc);

        // Penalidades
        JButton btnPenalidades = new JButton("Penalidades");
        gbc.gridx = 1;
        gbc.gridy = 2;
        btnPenalidades.addActionListener(e -> {
            if (verificarAcesso()) {
                new TelaPenalidade(funcionarios, alunos, professores, obras, emprestimos);
            }
        });
        add(btnPenalidades, gbc);

        // Botão Login/Logout para controle de acesso
        JButton btnLoginLogout = new JButton("Login");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        btnLoginLogout.addActionListener(e -> {
            if (usuarioLogado == null) {
                realizarLogin();
                if (usuarioLogado != null) {
                    btnLoginLogout.setText("Logout (" + usuarioLogado.getNome() + ")");
                }
            } else {
                usuarioLogado = null;
                JOptionPane.showMessageDialog(this, "Logout realizado.");
                btnLoginLogout.setText("Login");
            }
        });
        add(btnLoginLogout, gbc);

        setVisible(true);
    }

    private void inicializarDadosTeste() {
        // Dados de teste
        funcionarios.add(new Funcionario("1", "José"));
        funcionarios.add(new Funcionario("2", "Cláudia"));
        alunos.add(new Aluno("1", "Ana"));
        alunos.add(new Aluno("2", "Bruno"));
        alunos.add(new Aluno("3", "Claudio"));
        alunos.add(new Aluno("4", "Diana"));
        alunos.add(new Aluno("5", "Eduardo"));
        professores.add(new Professor("1", "Fabio"));
        professores.add(new Professor("2", "Gustavo"));
        professores.add(new Professor("3", "Helena"));
        obras.add(new Obra("1", "Titulo massa", "Fulano", 5, "Livro"));
        obras.add(new Obra("4", "Titulo massa vol-2", "Fulano", 5, "Livro"));
        obras.add(new Obra("2", "Titulo show", "Ciclano", 6, "Revista"));
        obras.add(new Obra("5", "Titulo show vol-2", "Ciclano", 6, "Revista"));
        obras.add(new Obra("3", "Titulo top", "Beutrano", 7, "DVD"));
        obras.add(new Obra("6", "Titulo top vol-2", "Beutrano", 7, "DVD"));
    }

    // Login para controle de acesso. Apenas funcionários. (Simulação)
    private void realizarLogin() {
        String id = JOptionPane.showInputDialog(this, "Informe o ID do funcionário para login:");
        if (id == null || id.trim().isEmpty()) {
            return;
        }
        for (Funcionario f : funcionarios) {
            if (f.getId().equals(id.trim())) {
                usuarioLogado = f;
                JOptionPane.showMessageDialog(this, "Login realizado com sucesso. Bem-vindo, " + f.getNome() + "!");
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Funcionário não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
    }

    // Verifica se há usuário logado para acesso às telas administrativas.
    private boolean verificarAcesso() {
        if (usuarioLogado == null) {
            JOptionPane.showMessageDialog(this,
                    "Acesso negado! Faça login como funcionário para acessar esta funcionalidade.", "Acesso Negado",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        new TelaPrincipal();
    }
}