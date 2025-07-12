package biblioteca;

import javax.swing.*;
import java.awt.event.*;

class TelaCadastros extends JFrame {

    public TelaCadastros(ListaEncadeada<Usuario> usuarios, ListaEncadeada<Funcionario> funcionarios, ListaEncadeada<Obra> obras) {
        super("Cadastros");
        setSize(350, 250);
        setLocationRelativeTo(null);
        setLayout(null);

        JButton btnFuncionario = new JButton("Cadastrar Funcionário");
        btnFuncionario.setBounds(50, 30, 220, 30);
        btnFuncionario.addActionListener(e -> new TelaCadastroFuncionario(funcionarios));
        add(btnFuncionario);

        JButton btnUsuario = new JButton("Cadastrar Usuário");
        btnUsuario.setBounds(50, 80, 220, 30);
        btnUsuario.addActionListener(e -> new TelaCadastroUsuario(usuarios));
        add(btnUsuario);

        JButton btnObra = new JButton("Cadastrar Obra");
        btnObra.setBounds(50, 130, 220, 30);
        btnObra.addActionListener(e -> new TelaCadastroObra(obras));
        add(btnObra);

        //teste //
        funcionarios.adicionar(new Funcionario("1", "José"));
        funcionarios.adicionar(new Funcionario("2", "Cláudia"));
        usuarios.adicionar(new Aluno("12", "Ziul"));
        usuarios.adicionar(new Aluno("5", "Ana"));
        usuarios.adicionar(new Professor("2", "Luis Enrrique"));
        usuarios.adicionar(new Professor("3", "Adriano"));
        obras.adicionar(new Obra("1", "Titulo massa", "Fulano", 5, "Livro"));
        obras.adicionar(new Obra("4", "Titulo massa vol-2", "Fulano", 5, "Livro"));
        obras.adicionar(new Obra("2", "Titulo show", "Ciclano", 6, "Revista"));
        obras.adicionar(new Obra("5", "Titulo show vol-2", "Ciclano", 6, "Revista"));
        obras.adicionar(new Obra("3", "Titulo top", "Beutrano", 7, "DVD"));
        obras.adicionar(new Obra("6", "Titulo top vol-2", "Beutrano", 7, "DVD"));

        setVisible(true);
    }
}

class TelaCadastroFuncionario extends JFrame {

    public TelaCadastroFuncionario(ListaEncadeada<Funcionario> funcionarios) {
        super("Cadastro de Funcionário");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(20, 20, 60, 25);
        add(lblId);
        JTextField txtId = new JTextField();
        txtId.setBounds(80, 20, 180, 25);
        add(txtId);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(20, 60, 60, 25);
        add(lblNome);
        JTextField txtNome = new JTextField();
        txtNome.setBounds(80, 60, 180, 25);
        add(txtNome);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(100, 110, 80, 30);
        btnSalvar.addActionListener(e -> {
            if (txtId.getText().isEmpty() || txtNome.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                funcionarios.adicionar(new Funcionario(txtId.getText(), txtNome.getText()));
                JOptionPane.showMessageDialog(this, "Funcionário cadastrado!");
                dispose();
            }
        });
        add(btnSalvar);

        setVisible(true);
    }
}

class TelaCadastroUsuario extends JFrame {

    public TelaCadastroUsuario(ListaEncadeada<Usuario> usuarios) {
        super("Cadastro de Usuário");
        setSize(300, 220);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(20, 20, 60, 25);
        add(lblId);
        JTextField txtId = new JTextField();
        txtId.setBounds(80, 20, 180, 25);
        add(txtId);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(20, 60, 60, 25);
        add(lblNome);
        JTextField txtNome = new JTextField();
        txtNome.setBounds(80, 60, 180, 25);
        add(txtNome);

        JLabel lblTipo = new JLabel("Tipo:");

        lblTipo.setBounds(
                20, 100, 60, 25);
        add(lblTipo);
        JComboBox<String> cbTipo = new JComboBox<>(new String[]{"Aluno", "Professor"});

        cbTipo.setBounds(
                80, 100, 180, 25);
        add(cbTipo);

        JButton btnSalvar = new JButton("Salvar");

        btnSalvar.setBounds(
                100, 150, 80, 30);
        btnSalvar.addActionListener(e
                -> {
            if (txtId.getText().isEmpty() || txtNome.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                if (cbTipo.getSelectedIndex() == 0) { // aqui posso adicionar um switch case para mais tipos de usuario
                    usuarios.adicionar(new Aluno(txtId.getText(), txtNome.getText()));
                } else {
                    usuarios.adicionar(new Professor(txtId.getText(), txtNome.getText()));
                }
                JOptionPane.showMessageDialog(this, "Usuário cadastrado!");
                dispose();
            }
        }
        );
        add(btnSalvar);

        setVisible(true);
    }
}

class TelaCadastroObra extends JFrame {

    public TelaCadastroObra(ListaEncadeada<Obra> obras) {
        super("Cadastro de Obra");
        setSize(320, 320);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(20, 20, 60, 25);
        add(lblId);
        JTextField txtId = new JTextField();
        txtId.setBounds(80, 20, 200, 25);
        add(txtId);

        JLabel lblTitulo = new JLabel("Título:");
        lblTitulo.setBounds(20, 60, 60, 25);
        add(lblTitulo);
        JTextField txtTitulo = new JTextField();
        txtTitulo.setBounds(80, 60, 200, 25);
        add(txtTitulo);

        JLabel lblAutor = new JLabel("Autor:");
        lblAutor.setBounds(20, 100, 60, 25);
        add(lblAutor);
        JTextField txtAutor = new JTextField();
        txtAutor.setBounds(80, 100, 200, 25);
        add(txtAutor);

        JLabel lblQtd = new JLabel("Exemplares:");
        lblQtd.setBounds(20, 140, 80, 25);
        add(lblQtd);
        JTextField txtQtd = new JTextField();
        txtQtd.setBounds(100, 140, 60, 25);
        add(txtQtd);

        JLabel lblTipo = new JLabel("Tipo:");
        lblTipo.setBounds(20, 180, 60, 25);
        add(lblTipo);
        JComboBox<String> cbTipoO = new JComboBox<>(new String[]{"Livro", "Revista", "DVD"});
        cbTipoO.setBounds(80, 180, 180, 25);
        add(cbTipoO);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(200, 140, 80, 30);
        btnSalvar.addActionListener(e -> {
            if (txtId.getText().isEmpty() || txtTitulo.getText().isEmpty() || txtAutor.getText().isEmpty() || txtQtd.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    int qtd = Integer.parseInt(txtQtd.getText());
                    obras.adicionar(new Obra(txtId.getText(), txtTitulo.getText(), txtAutor.getText(), qtd, cbTipoO.getName()));
                    JOptionPane.showMessageDialog(this, "Obra cadastrada!");
                    dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Informe um número válido para exemplares.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        );
        add(btnSalvar);

        setVisible(true);
    }
}
