package biblioteca;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class TelaConsultas extends JFrame {

    private LinkedList<Aluno> alunos;
    private LinkedList<Professor> professores;
    private LinkedList<Obra> obras;

    public TelaConsultas(LinkedList<Aluno> alunos, LinkedList<Professor> professores, LinkedList<Obra> obras) {
        super("Consultas");
        this.alunos = alunos;
        this.professores = professores;
        this.obras = obras;

        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();

        // Painel de usuários
        JPanel panelUsuarios = new JPanel(new BorderLayout());
        JPanel panelFiltroUsuarios = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelFiltroUsuarios.add(new JLabel("Buscar por nome:"));
        JTextField txtFiltroUsuario = new JTextField(20);
        panelFiltroUsuarios.add(txtFiltroUsuario);
        JButton btnBuscarUsuario = new JButton("Buscar");
        panelFiltroUsuarios.add(btnBuscarUsuario);
        panelUsuarios.add(panelFiltroUsuarios, BorderLayout.NORTH);

        String[] colunasUsuarios = {"ID", "Nome", "Tipo", "Empréstimos Atuais"};
        DefaultTableModel modelUsuarios = new DefaultTableModel(colunasUsuarios, 0);
        JTable tabelaUsuarios = new JTable(modelUsuarios);
        JScrollPane scrollUsuarios = new JScrollPane(tabelaUsuarios);
        panelUsuarios.add(scrollUsuarios, BorderLayout.CENTER);

        btnBuscarUsuario.addActionListener(e -> {
            String filtro = txtFiltroUsuario.getText().trim().toLowerCase();
            modelUsuarios.setRowCount(0);
            List<Usuario> listaUsuarios = new LinkedList<>();
            listaUsuarios.addAll(alunos);
            listaUsuarios.addAll(professores);

            List<Usuario> filtrados = listaUsuarios.stream()
                    .filter(u -> u.getNome().toLowerCase().contains(filtro))
                    .collect(Collectors.toList());

            for (Usuario u : filtrados) {
                modelUsuarios.addRow(new Object[]{u.getId(), u.getNome(), u.getTipo(), u.getEmprestimosAtuais()});
            }
            if (filtrados.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nenhum usuário encontrado com o filtro informado.");
            }
        });

        // Inicializa tabela com todos os usuários
        btnBuscarUsuario.doClick();

        // Painel de obras
        JPanel panelObras = new JPanel(new BorderLayout());
        JPanel panelFiltroObras = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelFiltroObras.add(new JLabel("Buscar por título ou autor:"));
        JTextField txtFiltroObras = new JTextField(20);
        panelFiltroObras.add(txtFiltroObras);
        JButton btnBuscarObras = new JButton("Buscar");
        panelFiltroObras.add(btnBuscarObras);
        panelObras.add(panelFiltroObras, BorderLayout.NORTH);

        String[] colunasObras = {"ID", "Título", "Autor", "Tipo", "Disponíveis/Total"};
        DefaultTableModel modelObras = new DefaultTableModel(colunasObras, 0);
        JTable tabelaObras = new JTable(modelObras);
        JScrollPane scrollObras = new JScrollPane(tabelaObras);
        panelObras.add(scrollObras, BorderLayout.CENTER);

        btnBuscarObras.addActionListener(e -> {
            String filtro = txtFiltroObras.getText().trim().toLowerCase();
            modelObras.setRowCount(0);

            List<Obra> filtradas = obras.stream()
                    .filter(o -> o.getTitulo().toLowerCase().contains(filtro) || o.getAutor().toLowerCase().contains(filtro))
                    .collect(Collectors.toList());

            for (Obra o : filtradas) {
                modelObras.addRow(new Object[]{
                        o.getId(), o.getTitulo(), o.getAutor(), o.getTipo(),
                        o.getExemplaresDisponiveis() + "/" + o.getTotalExemplares()
                });
            }
            if (filtradas.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nenhuma obra encontrada com o filtro informado.");
            }
        });

        // Inicializa tabela com todas as obras
        btnBuscarObras.doClick();

        tabbedPane.addTab("Usuários", panelUsuarios);
        tabbedPane.addTab("Obras", panelObras);

        add(tabbedPane, BorderLayout.CENTER);

        setVisible(true);
    }
}