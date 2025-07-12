package biblioteca;

import javax.swing.*;
import java.util.LinkedList;

public class TelaEmprestimo extends JFrame {

    LinkedList<Emprestimo> emprestimos = new LinkedList<>();

    LinkedList<Usuario> usuarios;
    LinkedList<Funcionario> funcionarios;
    LinkedList<Obra> obras;

    public class Emprestimo {
        Usuario usuario;
        Funcionario funcionario;
        Obra obra;
        int quantidade;
        String id, tipo;

        public Emprestimo(String id, String tipo, Usuario usuario, Funcionario funcionario, Obra obra, int quantidade) {
            this.id = id;
            this.tipo = tipo;
            this.usuario = usuario;
            this.funcionario = funcionario;
            this.obra = obra;
            this.quantidade = quantidade;
        }

        @Override
        public String toString() {
            return id + " - " + tipo + usuario + obra + " = " + quantidade + " - " + funcionario;
        }
    }

    public TelaEmprestimo(LinkedList<Usuario> usuarios, LinkedList<Funcionario> funcionarios, LinkedList<Obra> obras) {
        this.usuarios = usuarios;
        this.funcionarios = funcionarios;
        this.obras = obras;

        JButton btnVisualizar = new JButton("Visualizar empréstimos");
        btnVisualizar.setBounds(50, 80, 130, 30);
        btnVisualizar.addActionListener(e -> new VisualizarEmprestimo(usuarios, funcionarios, obras));
        add(btnVisualizar);

        JButton btnNovo = new JButton("Novo empréstimo");
        btnNovo.setBounds(50, 120, 130, 30);
        btnNovo.addActionListener(e -> new TelaCadastroEmprestimo(funcionarios, usuarios, obras));
        add(btnNovo);

        setTitle("Tela de Empréstimo");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        setVisible(true);
    }

    class VisualizarEmprestimo extends JFrame {
        public VisualizarEmprestimo(LinkedList<Usuario> usuarios, LinkedList<Funcionario> funcionarios,
                LinkedList<Obra> obras) {
            super("Emprestimos");
            setSize(400, 300);
            setLocationRelativeTo(null);
            setLayout(null);

            JButton btnUsuarios = new JButton("Consultar Emprestimos"); // Ajustar
            btnUsuarios.setBounds(50, 30, 280, 40);
            btnUsuarios.addActionListener(e -> {
                StringBuilder sb = new StringBuilder();
                for (Usuario usuario : usuarios) {
                    sb.append(usuario).append("\n");
                }
                JOptionPane.showMessageDialog(this, sb.length() > 0 ? sb.toString() : "Nenhuma obra cadastrada.");
            });
            add(btnUsuarios);

            JButton btnNovo = new JButton("Novo Emprestimo");
            btnNovo.setBounds(50, 100, 280, 40);
            btnNovo.addActionListener(e -> new TelaCadastroEmprestimo(funcionarios, usuarios, obras));
            add(btnNovo);

            setVisible(true);
        }
    }

    class TelaCadastroEmprestimo extends JFrame {
        public TelaCadastroEmprestimo(LinkedList<Funcionario> funcionarios, LinkedList<Usuario> usuarios,
                LinkedList<Obra> obras) {
            super("Novo Emprestimo");
            setSize(600, 600);
            setLocationRelativeTo(null);
            setLayout(null);

            JLabel lblId = new JLabel("ID:");
            lblId.setBounds(20, 20, 60, 25);
            add(lblId);
            JTextField txtId = new JTextField();
            txtId.setBounds(80, 20, 180, 25);
            add(txtId);

            JLabel lblResp = new JLabel("Funcionario Responsável:");
            lblResp.setBounds(20, 60, 60, 25);
            add(lblResp);
            JComboBox<String> cbResp = new JComboBox<>();
            for (Funcionario funcionario : funcionarios) { // Adiciona os funcionários da linkedlist ao JComboBox
                cbResp.addItem(funcionario.toString());
            }
            cbResp.setBounds(80, 60, 180, 25);
            add(cbResp);

            JLabel lblUser = new JLabel("Usuario:");
            lblUser.setBounds(20, 100, 60, 25);
            add(lblUser);
            JComboBox<String> cbUser = new JComboBox<>();
            for (Usuario usuario : usuarios) { // Adiciona os usuários da linkedlist ao JComboBox
                cbUser.addItem(usuario.toString());
            }
            cbUser.setBounds(80, 100, 180, 25);
            add(cbUser);

            JLabel lblObra = new JLabel("Obra:");
            lblObra.setBounds(20, 140, 60, 25);
            add(lblObra);
            JComboBox<String> cbObra = new JComboBox<>();
            for (Obra obra : obras) { // Adiciona as obras da linkedlist ao JComboBox
                cbObra.addItem(obra.toString());
            }
            cbObra.setBounds(80, 140, 180, 25);
            add(cbObra);

            JLabel lblQtd = new JLabel("QTD:");
            lblQtd.setBounds(20, 180, 60, 25);
            add(lblQtd);
            JTextField txtQtd = new JTextField();
            txtQtd.setBounds(80, 180, 180, 25);
            add(txtQtd);

            JButton btnSalvar = new JButton("Salvar");
            btnSalvar.setBounds(100, 220, 80, 30);
            btnSalvar.addActionListener(e -> {
                if (txtId.getText().isEmpty() || txtQtd.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Erro",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    String id = txtId.getText();
                    String tipo = usuarios.get(cbUser.getSelectedIndex()).getTipo(); // corrigir colocando o getTipo lá na TelaCadastro ao invés da Usuário
                    Usuario usuario = usuarios.get(cbUser.getSelectedIndex());
                    Funcionario funcionario = funcionarios.get(cbResp.getSelectedIndex());
                    Obra obra = obras.get(cbObra.getSelectedIndex());
                    int quantidade;
                    try {
                        quantidade = Integer.parseInt(txtQtd.getText());
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Quantidade inválida!", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    emprestimos.add(new Emprestimo(id, tipo, usuario, funcionario, obra, quantidade));
                    JOptionPane.showMessageDialog(this, "Empréstimo cadastrado!");
                    dispose();
                }
            });
            add(btnSalvar);

            setVisible(true);
        }
    }
}
