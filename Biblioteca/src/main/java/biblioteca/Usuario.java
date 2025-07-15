package biblioteca;

// Enum para tipos de usuário no sistema.
enum TipoUsuario {
    ALUNO, PROFESSOR;
}

// Classe abstrata que representa um usuário da biblioteca.
abstract class Usuario {

    private String id;
    private String nome;
    private TipoUsuario tipo; // enum para diferenciar aluno e professor
    private int limiteEmprestimos;
    private int emprestimosAtuais = 0;
    private boolean ativo = true; // Está habilitado para empréstimos?

    public Usuario(String id, String nome, int limite, TipoUsuario tipo) {
        this.id = id;
        this.nome = nome;
        this.limiteEmprestimos = limite;
        this.tipo = tipo;
    }

    /**
     * Verifica se o usuário pode realizar um empréstimo de determinada quantidade.
     * Considera se o usuário está ativo e se não ultrapassa o limite de
     * empréstimos
     */
    public boolean podeEmprestar(int qtd) {
        return ativo && (emprestimosAtuais + qtd) <= limiteEmprestimos;
    }

    // Registra um empréstimo para o usuário.
    public void emprestar(int qtd) {
        emprestimosAtuais += qtd;
    }

    // Registra uma devolução para o usuário.
    public void devolver(int qtd) {
        emprestimosAtuais -= qtd;
        if (emprestimosAtuais < 0) {
            emprestimosAtuais = 0;
        }
    }

    // Getters e setters
    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public TipoUsuario getTipo() {
        return tipo;
    }

    public int getLimiteEmprestimos() {
        return limiteEmprestimos;
    }

    public int getEmprestimosAtuais() {
        return emprestimosAtuais;
    }

    public void setEmprestimosAtuais(int emprestimosAtuais) {
        this.emprestimosAtuais = Math.max(emprestimosAtuais, 0); // Garante que não fique negativo
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void ativar() {
        this.ativo = true;
    }

    public void desativar() {
        this.ativo = false;
    }

    @Override
    public String toString() {
        return id + " - " + nome + " | Empréstimos: " + emprestimosAtuais + "/" + limiteEmprestimos + " | Ativo: "
                + ativo;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Usuario))
            return false;
        Usuario other = (Usuario) obj; // Conversão para poder acessar seus métodos e atributos.
        return id != null && id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}

/**
 * Classe que representa um aluno.
 */
class Aluno extends Usuario {

    public Aluno(String id, String nome) {
        super(id, nome, 3, TipoUsuario.ALUNO);
    }
}

/**
 * Classe que representa um professor.
 */
class Professor extends Usuario {

    public Professor(String id, String nome) {
        super(id, nome, 5, TipoUsuario.PROFESSOR);
    }
}

/**
 * Classe que representa um funcionário da biblioteca.
 */
class Funcionario {

    private String id;
    private String nome;

    public Funcionario(String id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    // Getters

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return id + " - " + nome;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Funcionario))
            return false;
        Funcionario other = (Funcionario) obj;
        return id != null && id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}

/**
 * Classe que representa uma obra no acervo da biblioteca.
 */
class Obra {

    private String id;
    private String titulo;
    private String autor;
    private String tipo;
    private int totalExemplares;
    private int exemplaresDisponiveis;

    public Obra(String id, String titulo, String autor, int qtd, String tipo) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.totalExemplares = qtd;
        this.exemplaresDisponiveis = qtd;
        this.tipo = tipo;
    }

    // Tenta emprestar a quantidade solicitada de exemplares.
    public boolean emprestar(int quantidade) {
        if (this.exemplaresDisponiveis >= quantidade) {
            this.exemplaresDisponiveis -= quantidade;
            return true;
        }
        return false;
    }

    // Registra a devolução de exemplares.
    public void devolver(int quantidade) {
        this.exemplaresDisponiveis += quantidade;
        if (this.exemplaresDisponiveis > totalExemplares) {
            this.exemplaresDisponiveis = totalExemplares;
        }
    }

    // Getters e setters
    public String getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getTipo() {
        return tipo;
    }

    public int getTotalExemplares() {
        return totalExemplares;
    }

    public int getExemplaresDisponiveis() {
        return exemplaresDisponiveis;
    }

    @Override
    public String toString() {
        return id + " - " + titulo + " - " + autor + " (" + tipo + ")" + " = " + exemplaresDisponiveis + "/"
                + totalExemplares;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Obra))
            return false;
        Obra other = (Obra) obj;
        return id != null && id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
