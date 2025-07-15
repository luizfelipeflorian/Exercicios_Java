package biblioteca;

import javax.swing.*;
import java.util.LinkedList;

public class TelaCadastro extends JFrame {

    LinkedList<Aluno> alunos;
    LinkedList<Professor> professores;
    LinkedList<Funcionario> funcionarios;
    LinkedList<Obra> obras;

    public TelaCadastro(LinkedList<Aluno> alunos, LinkedList<Professor> professores,
                       LinkedList<Funcionario> funcionarios, LinkedList<Obra> obras) {
        super("Cadastros");
        this.alunos = alunos;
        this.professores = professores;
        this.funcionarios = funcionarios;
        this.obras = obras;

        setSize(350, 250);
        setLocationRelativeTo(null);
        setLayout(null);

        JButton btnFuncionario = new JButton("Cadastrar Funcionário");
        btnFuncionario.setBounds(50, 30, 220, 30);
        btnFuncionario.addActionListener(e -> new TelaCadastroFuncionario(funcionarios));
        add(btnFuncionario);

        JButton btnUsuario = new JButton("Cadastrar Usuário");
        btnUsuario.setBounds(50, 80, 220, 30);
        btnUsuario.addActionListener(e -> new TelaCadastroUsuario(alunos, professores));
        add(btnUsuario);

        JButton btnObra = new JButton("Cadastrar Obra");
        btnObra.setBounds(50, 130, 220, 30);
        btnObra.addActionListener(e -> new TelaCadastroObra(obras));
        add(btnObra);

        // Dados de teste
        if (funcionarios.isEmpty() && alunos.isEmpty() && professores.isEmpty() && obras.isEmpty()) {
            funcionarios.add(new Funcionario("1", "José"));
            funcionarios.add(new Funcionario("2", "Cláudia"));
            alunos.add(new Aluno("12", "Ziul"));
            alunos.add(new Aluno("5", "Ana"));
            professores.add(new Professor("2", "Luis Enrrique"));
            professores.add(new Professor("3", "Adriano"));
            obras.add(new Obra("1", "Titulo massa", "Fulano", 5, "Livro"));
            obras.add(new Obra("4", "Titulo massa vol-2", "Fulano", 5, "Livro"));
            obras.add(new Obra("2", "Titulo show", "Ciclano", 6, "Revista"));
            obras.add(new Obra("5", "Titulo show vol-2", "Ciclano", 6, "Revista"));
            obras.add(new Obra("3", "Titulo top", "Beutrano", 7, "DVD"));
            obras.add(new Obra("6", "Titulo top vol-2", "Beutrano", 7, "DVD"));
        }

        setVisible(true);
    }
}

class TelaCadastroFuncionario extends JFrame {

    private LinkedList<Funcionario> funcionarios;

    public TelaCadastroFuncionario(LinkedList<Funcionario> funcionarios) {
        super("Cadastro de Funcionário");
        this.funcionarios = funcionarios;

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
            String id = txtId.getText().trim();
            String nome = txtNome.getText().trim();

            if (id.isEmpty() || nome.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
                if (id.isEmpty()) txtId.requestFocus();
                else txtNome.requestFocus();
                return;
            }

            if (existeFuncionario(id)) {
                JOptionPane.showMessageDialog(this, "ID já cadastrado para outro funcionário!", "Erro", JOptionPane.ERROR_MESSAGE);
                txtId.requestFocus();
                return;
            }

            funcionarios.add(new Funcionario(id, nome));
            JOptionPane.showMessageDialog(this, "Funcionário cadastrado!");
            dispose();
        });
        add(btnSalvar);

        setVisible(true);
    }

    private boolean existeFuncionario(String id) {
        return funcionarios.stream().anyMatch(f -> f.getId().equals(id));
    }
}

class TelaCadastroUsuario extends JFrame {

    private LinkedList<Aluno> alunos;
    private LinkedList<Professor> professores;

    public TelaCadastroUsuario(LinkedList<Aluno> alunos, LinkedList<Professor> professores) {
        super("Cadastro de Usuário");
        this.alunos = alunos;
        this.professores = professores;

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
        lblTipo.setBounds(20, 100, 60, 25);
        add(lblTipo);
        JComboBox<String> cbTipo = new JComboBox<>(new String[]{"Aluno", "Professor"});
        cbTipo.setBounds(80, 100, 180, 25);
        add(cbTipo);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(100, 150, 80, 30);
        btnSalvar.addActionListener(e -> {
            String id = txtId.getText().trim();
            String nome = txtNome.getText().trim();

            if (id.isEmpty() || nome.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
                if (id.isEmpty()) txtId.requestFocus();
                else txtNome.requestFocus();
                return;
            }

            if (cbTipo.getSelectedIndex() == 0) { // Aluno
                if (existeAluno(id)) {
                    JOptionPane.showMessageDialog(this, "ID já cadastrado para outro aluno!", "Erro", JOptionPane.ERROR_MESSAGE);
                    txtId.requestFocus();
                    return;
                }
                alunos.add(new Aluno(id, nome));
            } else { // Professor
                if (existeProfessor(id)) {
                    JOptionPane.showMessageDialog(this, "ID já cadastrado para outro professor!", "Erro", JOptionPane.ERROR_MESSAGE);
                    txtId.requestFocus();
                    return;
                }
                professores.add(new Professor(id, nome));
            }

            JOptionPane.showMessageDialog(this, "Usuário cadastrado!");
            dispose();
        });
        add(btnSalvar);

        setVisible(true);
    }

    private boolean existeAluno(String id) {
        return alunos.stream().anyMatch(a -> a.getId().equals(id));
    }

    private boolean existeProfessor(String id) {
        return professores.stream().anyMatch(p -> p.getId().equals(id));
    }
}

class TelaCadastroObra extends JFrame {

    private LinkedList<Obra> obras;

    public TelaCadastroObra(LinkedList<Obra> obras) {
        super("Cadastro de Obra");
        this.obras = obras;

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
            String id = txtId.getText().trim();
            String titulo = txtTitulo.getText().trim();
            String autor = txtAutor.getText().trim();
            String qtdStr = txtQtd.getText().trim();

            if (id.isEmpty() || titulo.isEmpty() || autor.isEmpty() || qtdStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
                if (id.isEmpty()) txtId.requestFocus();
                else if (titulo.isEmpty()) txtTitulo.requestFocus();
                else if (autor.isEmpty()) txtAutor.requestFocus();
                else txtQtd.requestFocus();
                return;
            }

            if (existeObra(id)) {
                JOptionPane.showMessageDialog(this, "ID já cadastrado para outra obra!", "Erro", JOptionPane.ERROR_MESSAGE);
                txtId.requestFocus();
                return;
            }

            try {
                int qtd = Integer.parseInt(qtdStr);
                if (qtd <= 0) {
                    JOptionPane.showMessageDialog(this, "Informe um número válido para exemplares.", "Erro", JOptionPane.ERROR_MESSAGE);
                    txtQtd.requestFocus();
                    return;
                }
                obras.add(new Obra(id, titulo, autor, qtd, (String) cbTipoO.getSelectedItem()));
                JOptionPane.showMessageDialog(this, "Obra cadastrada!");
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Informe um número válido para exemplares.", "Erro", JOptionPane.ERROR_MESSAGE);
                txtQtd.requestFocus();
            }
        });
        add(btnSalvar);

        setVisible(true);
    }

    private boolean existeObra(String id) {
        return obras.stream().anyMatch(o -> o.getId().equals(id));
    }
}