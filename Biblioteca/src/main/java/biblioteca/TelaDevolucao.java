package biblioteca;

import javax.swing.*;
import java.util.LinkedList;
import java.util.Date;

import biblioteca.TelaEmprestimo.Emprestimo;

public class TelaDevolucao extends JFrame {

    LinkedList<Emprestimo> emprestimos;
    LinkedList<Penalidade> penalidades; // Nova lista para controle de penalidades

    public TelaDevolucao(LinkedList<Emprestimo> emprestimos, LinkedList<Penalidade> penalidades) {
        this.emprestimos = emprestimos;
        this.penalidades = penalidades;

        setTitle("Tela de Devolução");
        setSize(400, 250);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton btnDevolucoesPendentes = new JButton("Devoluções Pendentes");
        btnDevolucoesPendentes.setBounds(50, 30, 280, 40);
        btnDevolucoesPendentes.addActionListener(e -> {
            StringBuilder sb = new StringBuilder();
            for (Emprestimo emprestimo : emprestimos) {
                if (!emprestimo.isDevolvido()) {  // Lista só os pendentes
                    sb.append(emprestimo).append("\n");
                }
            }
            JOptionPane.showMessageDialog(this, sb.length() > 0 ? sb.toString() : "Nenhuma Devolução Pendente.");
        });
        add(btnDevolucoesPendentes);

        JButton btnRegistrar = new JButton("Registrar Devolução");
        btnRegistrar.setBounds(50, 90, 280, 40);
        btnRegistrar.addActionListener(e -> new TelaRegistrarDevolucao(emprestimos, penalidades));
        add(btnRegistrar);

        setVisible(true);
    }

    public class TelaRegistrarDevolucao extends JFrame {

        public TelaRegistrarDevolucao(LinkedList<Emprestimo> emprestimos, LinkedList<Penalidade> penalidades) {
            setTitle("Registrar Devolução");
            setSize(400, 250);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);
            setLayout(null);

            JLabel lblId = new JLabel("ID do Empréstimo:");
            lblId.setBounds(50, 20, 150, 30);
            add(lblId);

            JTextField txtId = new JTextField();
            txtId.setBounds(200, 20, 150, 30);
            add(txtId);

            JLabel lblQtd = new JLabel("Quantidade Devolvida:");
            lblQtd.setBounds(50, 70, 150, 30);
            add(lblQtd);

            JTextField txtQtd = new JTextField();
            txtQtd.setBounds(200, 70, 150, 30);
            add(txtQtd);

            JButton btnConfirmar = new JButton("Confirmar Devolução");
            btnConfirmar.setBounds(125, 120, 150, 30);
            btnConfirmar.addActionListener(e -> {
                String id = txtId.getText().trim();
                int qtdDevolvida;

                if (id.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Informe o ID do empréstimo.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    qtdDevolvida = Integer.parseInt(txtQtd.getText());
                    if (qtdDevolvida <= 0) {
                        JOptionPane.showMessageDialog(this, "Quantidade deve ser maior que zero.", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Quantidade inválida.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Emprestimo emprestimo = null;
                for (Emprestimo emp : emprestimos) {
                    if (emp.id.equals(id)) {
                        emprestimo = emp;
                        break;
                    }
                }

                if (emprestimo == null) {
                    JOptionPane.showMessageDialog(this, "Empréstimo não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (emprestimo.isDevolvido()) {
                    JOptionPane.showMessageDialog(this, "Este empréstimo já foi devolvido.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                if (qtdDevolvida > emprestimo.quantidade) {
                    JOptionPane.showMessageDialog(this, "Quantidade devolvida maior que a quantidade emprestada.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Verifica atraso e aplica multa se necessário
                Date hoje = new Date();
                if (hoje.after(emprestimo.dataDevolucao)) {
                    long diffMillis = hoje.getTime() - emprestimo.dataDevolucao.getTime();
                    long diasAtraso = diffMillis / (1000 * 60 * 60 * 24);

                    double valorMultaDiaria = 2.0; // valor fixo da multa diária
                    double multaTotal = diasAtraso * valorMultaDiaria * qtdDevolvida;

                    // Cria penalidade de multa
                    String idPenalidade = java.util.UUID.randomUUID().toString();
                    Penalidade multa = new Penalidade(idPenalidade, emprestimo.usuario, Penalidade.Tipo.MULTA,
                            multaTotal, hoje, null);
                    penalidades.add(multa);

                    JOptionPane.showMessageDialog(this,
                            String.format("Devolução atrasada em %d dia(s). Multa de R$ %.2f aplicada.", diasAtraso, multaTotal),
                            "Multa Aplicada", JOptionPane.WARNING_MESSAGE);
                }

                // Registra devolução
                emprestimo.registrarDevolucao(qtdDevolvida);

                JOptionPane.showMessageDialog(this, "Devolução registrada com sucesso!");
                dispose();
            });
            add(btnConfirmar);

            setVisible(true);
        }
    }
}
