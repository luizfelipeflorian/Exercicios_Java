package biblioteca;

import javax.swing.*;
import java.util.LinkedList;
import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TelaEmprestimo extends JFrame {

    LinkedList<Emprestimo> emprestimos;
    LinkedList<Aluno> alunos;
    LinkedList<Professor> professores;
    LinkedList<Funcionario> funcionarios;
    LinkedList<Obra> obras;
    LinkedList<Penalidade> penalidades; // nova lista para controle de penalidades

    private TelaPenalidade controlePenalidades;

    public class Emprestimo {
        Usuario usuario;
        Funcionario funcionario;
        Obra obra;
        int quantidade;
        String id, tipo;
        Date dataEmprestimo;
        Date dataDevolucao;
        Boolean confirmacao;

        public Emprestimo(String id, String tipo, Usuario usuario, Funcionario funcionario, Obra obra, int quantidade,
                Date dataEmprestimo, Date dataDevolucao, Boolean confirmacao) {
            this.id = id;
            this.tipo = tipo;
            this.usuario = usuario;
            this.funcionario = funcionario;
            this.obra = obra;
            this.quantidade = quantidade;
            this.dataEmprestimo = dataEmprestimo;
            this.dataDevolucao = dataDevolucao;
            this.confirmacao = confirmacao;
        }

        @Override
        public String toString() {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String dataEmp = sdf.format(dataEmprestimo);
            String dataDev = sdf.format(dataDevolucao);
            return id + " - [" + tipo + "] [" + usuario + "] solicitou " + quantidade + " exemplares de [" + obra + "]"
                    + " em " + dataEmp + ", devolução até " + dataDev
                    + ". Autorizado por: " + funcionario;
        }

        public boolean isDevolvido() {
            return confirmacao != null && confirmacao;
        }

        public void registrarDevolucao(int quantidade) {
            if (quantidade <= 0)
                return;

            this.confirmacao = true;
            obra.devolver(quantidade);
            usuario.devolver(quantidade);

            if (usuario.getEmprestimosAtuais() < 0) {
                usuario.setEmprestimosAtuais(0);
            }

        }
    }

    public TelaEmprestimo(LinkedList<Aluno> alunos, LinkedList<Professor> professores,
            LinkedList<Funcionario> funcionarios, LinkedList<Obra> obras,
            LinkedList<Emprestimo> emprestimos, LinkedList<Penalidade> penalidades) {

        this.alunos = alunos;
        this.professores = professores;
        this.funcionarios = funcionarios;
        this.obras = obras;
        this.emprestimos = emprestimos;
        this.penalidades = penalidades;

        this.controlePenalidades = new TelaPenalidade(funcionarios, alunos, professores, obras, emprestimos);
        // Oculta a janela da tela de penalidades (utiliza apenas para lógica)
        this.controlePenalidades.setVisible(false);
        setTitle("Tela de Empréstimo");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        setVisible(true);

        JButton btnVisualizar = new JButton("Visualizar empréstimos");
        btnVisualizar.setBounds(50, 30, 280, 40);
        btnVisualizar.addActionListener(e -> {
            StringBuilder sb = new StringBuilder();
            for (Emprestimo emprestimo : emprestimos) {
                sb.append(emprestimo).append("\n");
            }
            JOptionPane.showMessageDialog(this, sb.length() > 0 ? sb.toString() : "Nenhum empréstimo cadastrado.");
        });
        add(btnVisualizar);

        JButton btnNovo = new JButton("Novo empréstimo");
        btnNovo.setBounds(50, 100, 280, 40);
        btnNovo.addActionListener(
                e -> new TelaCadastroEmprestimo(funcionarios, alunos, professores, obras, emprestimos, penalidades));
        add(btnNovo);
    }

    class TelaCadastroEmprestimo extends JFrame {
        LinkedList<Emprestimo> emprestimos;
        LinkedList<Penalidade> penalidades;

        public TelaCadastroEmprestimo(LinkedList<Funcionario> funcionarios, LinkedList<Aluno> alunos,
                LinkedList<Professor> Professores, LinkedList<Obra> obras,
                LinkedList<Emprestimo> emprestimos, LinkedList<Penalidade> penalidades) {
            this.emprestimos = emprestimos;
            this.penalidades = penalidades;

            setSize(380, 480);
            setLocationRelativeTo(null);
            setLayout(null);

            // ID
            JLabel lblId = new JLabel("ID:");
            lblId.setBounds(20, 20, 60, 25);
            add(lblId);
            JTextField txtId = new JTextField();
            txtId.setBounds(80, 20, 180, 25);
            add(txtId);

            // Data de Empréstimo e Devolução
            JLabel lblDataEmprestimo = new JLabel("Data Empréstimo (dd/MM/yyyy):");
            lblDataEmprestimo.setBounds(20, 60, 180, 25);
            add(lblDataEmprestimo);
            JTextField txtDataEmprestimo = new JTextField(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
            txtDataEmprestimo.setBounds(200, 60, 100, 25);
            add(txtDataEmprestimo);

            JLabel lblDataDevolucao = new JLabel("Data Devolução (dd/MM/yyyy):");
            lblDataDevolucao.setBounds(20, 100, 180, 25);
            add(lblDataDevolucao);
            JTextField txtDataDevolucao = new JTextField();
            txtDataDevolucao.setBounds(200, 100, 100, 25);
            add(txtDataDevolucao);

            // Funcionario Responsável
            JLabel lblResp = new JLabel("Funcionario Responsável:");
            lblResp.setBounds(20, 140, 150, 25);
            add(lblResp);
            JComboBox<String> cbResp = new JComboBox<>();
            for (Funcionario funcionario : funcionarios) { // Adiciona os funcionários da linkedlist ao JComboBox
                cbResp.addItem(funcionario.toString());
            }
            cbResp.setBounds(170, 140, 180, 25);
            add(cbResp);

            // Tipo
            JLabel lblTipo = new JLabel("Tipo:");
            lblTipo.setBounds(20, 180, 60, 25);
            add(lblTipo);
            JComboBox<String> cbTipo = new JComboBox<>(new String[] { "Aluno", "Professor" });
            cbTipo.setBounds(80, 180, 180, 25);
            add(cbTipo);

            // Usuario
            JLabel lblUser = new JLabel("Usuario:");
            lblUser.setBounds(20, 220, 60, 25);
            add(lblUser);
            JComboBox<String> cbUser = new JComboBox<>();
            cbUser.setBounds(80, 220, 180, 25);
            add(cbUser);

            // Atualiza cbUser conforme cbTipo
            cbTipo.addActionListener(e -> {
                cbUser.removeAllItems();
                if (cbTipo.getSelectedIndex() == 0) { // Aluno
                    for (Aluno usuario : alunos) {
                        cbUser.addItem(usuario.toString());
                    }
                } else { // Professor
                    for (Professor usuario : Professores) {
                        cbUser.addItem(usuario.toString());
                    }
                }
            });

            // Inicializa cbUser com alunos
            for (Aluno usuario : alunos) {
                cbUser.addItem(usuario.toString());
            }

            // obra
            JLabel lblObra = new JLabel("Obra:");
            lblObra.setBounds(20, 260, 60, 25);
            add(lblObra);
            JComboBox<String> cbObra = new JComboBox<>();
            for (Obra obra : obras) { // Adiciona as obras da linkedlist ao JComboBox
                cbObra.addItem(obra.getId() + " - " + obra.getTitulo() + " - " + obra.getAutor());
            }
            cbObra.setBounds(80, 260, 180, 25);
            add(cbObra);

            // Quantidade
            JLabel lblQtd = new JLabel("QTD:");
            lblQtd.setBounds(20, 300, 60, 25);
            add(lblQtd);
            JTextField txtQtd = new JTextField();
            txtQtd.setBounds(80, 300, 180, 25);
            add(txtQtd);

            JButton btnSalvar = new JButton("Salvar");
            btnSalvar.setBounds(100, 340, 80, 30);
            btnSalvar.addActionListener(e -> {
                if (txtId.getText().isEmpty() || txtQtd.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String id = txtId.getText();
                String tipo;
                Usuario usuario;
                if (cbTipo.getSelectedItem().equals("Aluno")) {
                    tipo = "Aluno";
                    usuario = alunos.get(cbUser.getSelectedIndex());
                } else {
                    tipo = "Professor";
                    usuario = Professores.get(cbUser.getSelectedIndex());
                }
                Funcionario funcionario = funcionarios.get(cbResp.getSelectedIndex());
                Obra obra = obras.get(cbObra.getSelectedIndex());
                int quantidade;
                try {
                    quantidade = Integer.parseInt(txtQtd.getText());
                    if (quantidade <= 0) {
                        JOptionPane.showMessageDialog(this, "Quantidade deve ser maior que zero.", "Erro",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Quantidade inválida!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date dataEmprestimo;
                Date dataDevolucao;
                try {
                    dataEmprestimo = sdf.parse(txtDataEmprestimo.getText());
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(this,
                            "Formato de data inválido para data de empréstimo! Use dd/MM/yyyy.", "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Ajusta dataDevolucao conforme prazo padrão se campo vazio
                if (txtDataDevolucao.getText().trim().isEmpty()) {
                    int prazoDias = (usuario.getTipo() == TipoUsuario.ALUNO) ? 7 : 10; // 7 dias para aluno, 10 para
                                                                                       // professor
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(dataEmprestimo);
                    cal.add(Calendar.DAY_OF_MONTH, prazoDias);
                    dataDevolucao = cal.getTime();
                } else {
                    try {
                        dataDevolucao = sdf.parse(txtDataDevolucao.getText());
                    } catch (ParseException ex) {
                        JOptionPane.showMessageDialog(this,
                                "Formato de data inválido para data de devolução! Use dd/MM/yyyy.", "Erro",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                // Valida se dataDevolucao está depois de dataEmprestimo
                if (!dataDevolucao.after(dataEmprestimo)) {
                    JOptionPane.showMessageDialog(this, "Data de devolução deve ser posterior à data de empréstimo.",
                            "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Verifica se há exemplares suficientes na obra
                if (obra.getExemplaresDisponiveis() < quantidade) {
                    JOptionPane.showMessageDialog(this, "Não há exemplares suficientes disponíveis.", "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Verifica se o usuário pode pegar essa quantidade (limite de empréstimos)
                if (!usuario.podeEmprestar(quantidade)) {
                    JOptionPane.showMessageDialog(this, "Usuário atingiu o limite de empréstimos permitidos.", "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Verifica se o usuário está bloqueado por penalidade ativa
                if (controlePenalidades.usuarioEstaBloqueado(usuario)) {
                    JOptionPane.showMessageDialog(this,
                            "Usuário está bloqueado por penalidade ativa e não pode realizar empréstimos.", "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Verifica se o usuário possui multas ativas
                double multaAtiva = controlePenalidades.multaAtivaTotal(usuario);
                if (multaAtiva > 0) {
                    JOptionPane.showMessageDialog(this, String.format(
                            "Usuário possui multas ativas no valor de R$ %.2f. Regularize antes de realizar novos empréstimos.",
                            multaAtiva), "Aviso", JOptionPane.WARNING_MESSAGE);
                }

                // Registra empréstimo
                usuario.emprestar(quantidade);
                obra.emprestar(quantidade);

                emprestimos.add(new Emprestimo(id, tipo, usuario, funcionario, obra, quantidade, dataEmprestimo,
                        dataDevolucao, false));

                JOptionPane.showMessageDialog(this, "Empréstimo cadastrado!");
                dispose();
            });

            add(btnSalvar);

            setVisible(true);
        }
    }
}