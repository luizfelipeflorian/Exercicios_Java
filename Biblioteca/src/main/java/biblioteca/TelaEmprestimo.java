package biblioteca;

import java.awt.*;
import javax.swing.*;
import java.util.LinkedList;

public class TelaEmprestimo extends JFrame {

    public TelaEmprestimo(LinkedList<Usuario> usuarios, LinkedList<Funcionario> funcionarios, LinkedList<Obra> obras) {
        super("Emprestimo");
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

    public TelaCadastroEmprestimo(LinkedList<Funcionario> funcionarios, LinkedList<Usuario> usuarios, LinkedList<Obra> obras) {
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
        JComboBox<String> cbResp = new JComboBox<>(new String[]{"Livro", "Revista", "DVD"});
        cbResp.setBounds(80, 60, 180, 25);
        add(cbResp);
        
        JLabel lblUser = new JLabel("Usuario:");
        lblUser.setBounds(20, 100, 60, 25);
        add(lblUser);
        JComboBox<String> cbUser = new JComboBox<>(new String[]{"Livro", "Revista", "DVD"});
        cbUser.setBounds(80, 100, 180, 25);
        add(cbUser);

        JLabel lblObra = new JLabel("Obra:");
        lblObra.setBounds(20, 140, 60, 25);
        add(lblObra);
        JComboBox<String> cbObra = new JComboBox<>(new String[]{"Livro", "Revista", "DVD"});
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
                JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                funcionarios.add(new Funcionario(txtId.getText(), txtQtd.getText()));
                JOptionPane.showMessageDialog(this, "Funcionário cadastrado!");
                dispose();
            }
        });
        add(btnSalvar);

        JPanel painel = new JPanel(new GridLayout(2, 2, 10, 10)); //ajustar painel
        painel.add(btnSalvar);

        add(painel);
        
        painel.setVisible(true);
        setVisible(true);
    }
}
