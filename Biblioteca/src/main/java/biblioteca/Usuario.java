package biblioteca;

abstract class Usuario {

    String id, nome, tipo;
    int limiteEmprestimos, emprestimosAtuais = 0;

    Usuario(String id, String nome, int limite, String tipo) {
        this.id = id;
        this.nome = nome;
        this.limiteEmprestimos = limite;
        this.tipo = tipo;
    }

    public boolean podeEmprestar(int qtd) {
        return (emprestimosAtuais + qtd) <= limiteEmprestimos;
    }

    public void emprestar(int qtd) {
        emprestimosAtuais += qtd;
    }

    public void devolver(int qtd) {
        emprestimosAtuais -= qtd;
    }

    @Override
    public String toString() {
        return id + " - " + nome + " | " + emprestimosAtuais + "/" + limiteEmprestimos;
    }
}

class Aluno extends Usuario {

    Aluno(String id, String nome) {
        super(id, nome, 3, "Aluno");
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}

class Professor extends Usuario {

    Professor(String id, String nome) {
        super(id, nome, 5, "Professor");
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}

class Funcionario {

    String id, nome;

    Funcionario(String id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    @Override
    public String toString() {
        return id + " - " + nome;
    }
}

class Obra {

    String id, titulo, autor, tipo;
    int totalExemplares, exemplaresDisponiveis;

    Obra(String id, String titulo, String autor, int qtd, String tipo) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.totalExemplares = qtd;
        this.exemplaresDisponiveis = qtd;
        this.tipo = tipo;
    }

    public boolean emprestar(int quantidade) {
        if (this.exemplaresDisponiveis >= quantidade) {
            this.exemplaresDisponiveis -= quantidade;
            return true;
        }
        return false;
    }

    public void devolver(int quantidade) {
        this.exemplaresDisponiveis += quantidade;
    }

    @Override
    public String toString() {
        return id + " - " + titulo + " - " + autor + " (" + tipo + ")" + " = " + exemplaresDisponiveis + "/"
                + totalExemplares;
    }
}
