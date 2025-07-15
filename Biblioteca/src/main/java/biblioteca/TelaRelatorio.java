package biblioteca;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class TelaRelatorio extends JFrame {

    private LinkedList<TelaEmprestimo.Emprestimo> emprestimos;
    private LinkedList<Aluno> alunos;
    private LinkedList<Professor> professores;
    private LinkedList<Obra> obras;

    public TelaRelatorio(LinkedList<Aluno> alunos, LinkedList<Professor> professores,
                        LinkedList<Obra> obras, LinkedList<TelaEmprestimo.Emprestimo> emprestimos) {
        super("Relatórios Administrativos");
        this.alunos = alunos;
        this.professores = professores;
        this.obras = obras;
        this.emprestimos = emprestimos;

        setSize(400, 350);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JButton btnLivrosMaisEmprestados = new JButton("Livros mais emprestados");
        btnLivrosMaisEmprestados.setBounds(50, 30, 280, 40);
        btnLivrosMaisEmprestados.addActionListener(e -> mostrarLivrosMaisEmprestados());
        add(btnLivrosMaisEmprestados);

        JButton btnUsuariosInadimplentes = new JButton("Usuários inadimplentes");
        btnUsuariosInadimplentes.setBounds(50, 80, 280, 40);
        btnUsuariosInadimplentes.addActionListener(e -> mostrarUsuariosInadimplentes());
        add(btnUsuariosInadimplentes);

        JButton btnEmprestimosPorPeriodo = new JButton("Empréstimos por período");
        btnEmprestimosPorPeriodo.setBounds(50, 130, 280, 40);
        btnEmprestimosPorPeriodo.addActionListener(e -> mostrarEmprestimosPorPeriodo());
        add(btnEmprestimosPorPeriodo);

        JButton btnInventarioAcervo = new JButton("Inventário do acervo");
        btnInventarioAcervo.setBounds(50, 180, 280, 40);
        btnInventarioAcervo.addActionListener(e -> mostrarInventarioAcervo());
        add(btnInventarioAcervo);

        setVisible(true);
    }

    // Livros mais emprestados
    private void mostrarLivrosMaisEmprestados() {
        if (emprestimos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum empréstimo registrado.", "Relatório", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Conta a quantidade total emprestada por obra
        Map<String, Integer> contagem = new HashMap<>();
        for (TelaEmprestimo.Emprestimo e : emprestimos) {
            String obraId = e.obra.getId();
            contagem.put(obraId, contagem.getOrDefault(obraId, 0) + e.quantidade);
        }

        // Ordenar por quantidade decrescente
        List<Map.Entry<String, Integer>> listaOrdenada = contagem.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toList());

        StringBuilder sb = new StringBuilder("Livros mais emprestados:\n");
        for (Map.Entry<String, Integer> entry : listaOrdenada) {
            Obra obra = buscarObraPorId(entry.getKey());
            if (obra != null) {
                sb.append(String.format("%s - %s (%d exemplares emprestados)\n", obra.getId(), obra.getTitulo(), entry.getValue()));
            }
        }

        JOptionPane.showMessageDialog(this, sb.toString(), "Relatório", JOptionPane.INFORMATION_MESSAGE);
    }

    // Usuários inadimplentes (empréstimos atrasados)
    private void mostrarUsuariosInadimplentes() {
        Date hoje = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        // Map de usuário para lista de empréstimos atrasados
        Map<Usuario, List<TelaEmprestimo.Emprestimo>> atrasados = new HashMap<>();

        for (TelaEmprestimo.Emprestimo e : emprestimos) {
            if (!e.isDevolvido() && e.dataDevolucao.before(hoje)) {
                atrasados.computeIfAbsent(e.usuario, k -> new ArrayList<>()).add(e); // Adiciona uma nova lista para o usuário caso ele ainda não tenha uma lista de atrasos
            }
        }

        if (atrasados.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum usuário inadimplente.", "Relatório", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder sb = new StringBuilder("Usuários inadimplentes:\n");
        for (Map.Entry<Usuario, List<TelaEmprestimo.Emprestimo>> entry : atrasados.entrySet()) {
            Usuario usuario = entry.getKey();
            sb.append(usuario.getId()).append(" - ").append(usuario.getNome()).append("\n");
            for (TelaEmprestimo.Emprestimo e : entry.getValue()) {
                sb.append("  Empréstimo ID: ").append(e.id)
                  .append(", Obra: ").append(e.obra.getTitulo())
                  .append(", Data devolução: ").append(sdf.format(e.dataDevolucao))
                  .append("\n");
            }
        }

        JOptionPane.showMessageDialog(this, sb.toString(), "Relatório", JOptionPane.WARNING_MESSAGE);
    }

    // Empréstimos por período
    private void mostrarEmprestimosPorPeriodo() {
        String dataInicioStr = JOptionPane.showInputDialog(this, "Informe data inicial (dd/MM/yyyy):");
        String dataFimStr = JOptionPane.showInputDialog(this, "Informe data final (dd/MM/yyyy):");
        if (dataInicioStr == null || dataFimStr == null || dataInicioStr.isEmpty() || dataFimStr.isEmpty()) {
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date dataInicio, dataFim;
        try {
            dataInicio = sdf.parse(dataInicioStr);
            dataFim = sdf.parse(dataFimStr);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Formato de data inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        StringBuilder sb = new StringBuilder("Empréstimos entre " + dataInicioStr + " e " + dataFimStr + ":\n");
        boolean encontrou = false;
        for (TelaEmprestimo.Emprestimo e : emprestimos) {
            if (!e.dataEmprestimo.before(dataInicio) && !e.dataEmprestimo.after(dataFim)) {
                sb.append(String.format("ID: %s | Usuário: %s | Obra: %s | Quantidade: %d\n",
                        e.id, e.usuario.getNome(), e.obra.getTitulo(), e.quantidade));
                encontrou = true;
            }
        }
        if (!encontrou) {
            sb.append("Nenhum empréstimo neste período.");
        }

        JOptionPane.showMessageDialog(this, sb.toString(), "Relatório", JOptionPane.INFORMATION_MESSAGE);
    }

    // Inventário do acervo
    private void mostrarInventarioAcervo() {
        if (obras.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhuma obra cadastrada.", "Relatório", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder sb = new StringBuilder("Inventário do acervo:\n");
        for (Obra obra : obras) {
            sb.append(String.format("%s | %s | %s | Tipo: %s | Disponíveis: %d/%d\n",
                    obra.getId(), obra.getTitulo(), obra.getAutor(), obra.getTipo(),
                    obra.getExemplaresDisponiveis(), obra.getTotalExemplares()));
        }

        JOptionPane.showMessageDialog(this, sb.toString(), "Relatório", JOptionPane.INFORMATION_MESSAGE);
    }

    private Obra buscarObraPorId(String id) {
        for (Obra obra : obras) {
            if (obra.getId().equals(id)) {
                return obra;
            }
        }
        return null;
    }
}
