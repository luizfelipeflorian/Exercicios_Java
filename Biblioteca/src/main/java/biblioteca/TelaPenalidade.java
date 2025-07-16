package biblioteca;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.*;

//Classe que representa uma penalidade aplicada a um usuário.
class Penalidade {

    enum Tipo {
        MULTA, SUSPENSAO
    }

    private String id; // identificador único da penalidade
    private Usuario usuario;
    private Tipo tipo;
    private double valorMulta; // para multas
    private Date dataInicio;
    private Date dataFim;
    private boolean ativa;

    public Penalidade(String id, Usuario usuario, Tipo tipo, double valorMulta, Date dataInicio, Date dataFim) {
        this.id = id;
        this.usuario = usuario;
        this.tipo = tipo;
        this.valorMulta = valorMulta;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.ativa = true;
    }

    public String getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public double getValorMulta() {
        return valorMulta;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public boolean isAtiva() {
        // Atualiza o status baseado na data atual
        if (ativa && dataFim != null) {
            Date hoje = new Date();
            if (hoje.after(dataFim)) {
                ativa = false;
            }
        }
        return ativa;
    }

    public void encerrar() {
        this.ativa = false;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fim = (dataFim != null) ? sdf.format(dataFim) : "Indefinido";
        String multaStr = (tipo == Tipo.MULTA) ? String.format(" | Multa: R$ %.2f", valorMulta) : "";
        return String.format("ID: %s | Usuário: %s | Tipo: %s%s | De: %s Até: %s | Status: %s",
                id, usuario.getNome(), tipo, multaStr, sdf.format(dataInicio), fim, isAtiva() ? "Ativa" : "Encerrada");
    }
}

// Tela para gerenciamento de penalidades
public class TelaPenalidade extends JFrame {

    private LinkedList<Funcionario> funcionarios;
    private LinkedList<Aluno> alunos;
    private LinkedList<Professor> professores;
    private LinkedList<Obra> obras;
    private LinkedList<TelaEmprestimo.Emprestimo> emprestimos;
    private LinkedList<Penalidade> penalidades;

    private DefaultListModel<Penalidade> modelPenalidades = new DefaultListModel<>();
    private JList<Penalidade> listaPenalidades;

    public TelaPenalidade(LinkedList<Funcionario> funcionarios, LinkedList<Aluno> alunos,
            LinkedList<Professor> professores, LinkedList<Obra> obras,
            LinkedList<TelaEmprestimo.Emprestimo> emprestimos) {
        super("Controle de Penalidades");

        this.funcionarios = funcionarios;
        this.alunos = alunos;
        this.professores = professores;
        this.obras = obras;
        this.emprestimos = emprestimos;
        this.penalidades = new LinkedList<>();

        setSize(600, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Lista de penalidades
        listaPenalidades = new JList<>(modelPenalidades);
        JScrollPane scroll = new JScrollPane(listaPenalidades);
        add(scroll, BorderLayout.CENTER);

        // Painel inferior com botões
        JPanel painelBotoes = new JPanel();
        JButton btnAdicionarMulta = new JButton("Adicionar Multa");
        JButton btnAdicionarSuspensao = new JButton("Adicionar Suspensão");
        JButton btnEncerrarPenalidade = new JButton("Encerrar Penalidade");

        painelBotoes.add(btnAdicionarMulta);
        painelBotoes.add(btnAdicionarSuspensao);
        painelBotoes.add(btnEncerrarPenalidade);

        add(painelBotoes, BorderLayout.SOUTH);

        btnAdicionarMulta.addActionListener(e -> adicionarPenalidade(Penalidade.Tipo.MULTA));
        btnAdicionarSuspensao.addActionListener(e -> adicionarPenalidade(Penalidade.Tipo.SUSPENSAO));
        btnEncerrarPenalidade.addActionListener(e -> encerrarPenalidadeSelecionada());

        atualizarListaPenalidades();

        setVisible(true);
    }

    private void adicionarPenalidade(Penalidade.Tipo tipo) {
        JPanel painel = new JPanel(new GridLayout(0, 2, 5, 5));

        // Seleção de usuário
        painel.add(new JLabel("Tipo de Usuário:"));
        JComboBox<String> cbTipoUsuario = new JComboBox<>(new String[] { "Aluno", "Professor" });
        painel.add(cbTipoUsuario);

        painel.add(new JLabel("Usuário:"));
        JComboBox<Usuario> cbUsuario = new JComboBox<>();
        painel.add(cbUsuario);

        cbTipoUsuario.addActionListener(ev -> {
            cbUsuario.removeAllItems();
            if (cbTipoUsuario.getSelectedItem().equals("Aluno")) {
                for (Aluno a : alunos) {
                    cbUsuario.addItem(a);
                }
            } else {
                for (Professor p : professores) {
                    cbUsuario.addItem(p);
                }
            }
        });
        // Inicializa com alunos
        for (Aluno a : alunos) {
            cbUsuario.addItem(a);
        }

        JTextField txtValorMulta = new JTextField();
        if (tipo == Penalidade.Tipo.MULTA) {
            painel.add(new JLabel("Valor da Multa (R$):"));
            painel.add(txtValorMulta);
        }

        JTextField txtDataInicio = new JTextField(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        painel.add(new JLabel("Data Início (dd/MM/yyyy):"));
        painel.add(txtDataInicio);

        JTextField txtDataFim = new JTextField();
        painel.add(new JLabel("Data Fim (dd/MM/yyyy):"));
        painel.add(txtDataFim);

        int res = JOptionPane.showConfirmDialog(this, painel,
                (tipo == Penalidade.Tipo.MULTA ? "Adicionar Multa" : "Adicionar Suspensão"),
                JOptionPane.OK_CANCEL_OPTION);

        if (res == JOptionPane.OK_OPTION) {
            Usuario usuario = (Usuario) cbUsuario.getSelectedItem();
            if (usuario == null) {
                JOptionPane.showMessageDialog(this, "Selecione um usuário.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double valorMulta = 0;
            if (tipo == Penalidade.Tipo.MULTA) {
                try {
                    valorMulta = Double.parseDouble(txtValorMulta.getText());
                    if (valorMulta < 0)
                        throw new NumberFormatException();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Valor da multa inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            Date dataInicio, dataFim = null;
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                dataInicio = sdf.parse(txtDataInicio.getText());
                if (!txtDataFim.getText().trim().isEmpty()) {
                    dataFim = sdf.parse(txtDataFim.getText());
                    if (dataFim.before(dataInicio)) {
                        JOptionPane.showMessageDialog(this, "Data fim não pode ser anterior à data início.", "Erro",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Formato de data inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String idPenalidade = UUID.randomUUID().toString();
            Penalidade penalidade = new Penalidade(idPenalidade, usuario, tipo, valorMulta, dataInicio, dataFim);
            penalidades.add(penalidade);

            JOptionPane.showMessageDialog(this, "Penalidade adicionada com sucesso!");
            atualizarListaPenalidades();
        }
    }

    private void encerrarPenalidadeSelecionada() {
        Penalidade selecionada = listaPenalidades.getSelectedValue();
        if (selecionada == null) {
            JOptionPane.showMessageDialog(this, "Selecione uma penalidade para encerrar.", "Aviso",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!selecionada.isAtiva()) {
            JOptionPane.showMessageDialog(this, "Esta penalidade já está encerrada.", "Aviso",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        selecionada.encerrar();
        JOptionPane.showMessageDialog(this, "Penalidade encerrada com sucesso.");
        atualizarListaPenalidades();
    }

    private void atualizarListaPenalidades() {
        modelPenalidades.clear();
        for (Penalidade p : penalidades) {
            modelPenalidades.addElement(p);
        }
    }

    // Verifica se um usuário está bloqueado por penalidade ativa.
    public boolean usuarioEstaBloqueado(Usuario usuario) {
        for (Penalidade p : penalidades) {
            if (p.getUsuario().equals(usuario) && p.isAtiva() && p.getTipo() == Penalidade.Tipo.SUSPENSAO) {
                return true;
            }
        }
        return false;
    }

    // Calcula multa total ativa de um usuário.
    public double multaAtivaTotal(Usuario usuario) {
        double total = 0;
        for (Penalidade p : penalidades) {
            if (p.getUsuario().equals(usuario) && p.isAtiva() && p.getTipo() == Penalidade.Tipo.MULTA) {
                total += p.getValorMulta();
            }
        }
        return total;
    }
}
