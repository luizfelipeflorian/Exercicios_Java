package biblioteca;

import javax.swing.JComboBox;

abstract class Usuario {

    String id, nome;
    int limiteEmprestimos, emprestimosAtuais = 0;

    Usuario(String id, String nome, int limite) {
        this.id = id;
        this.nome = nome;
        this.limiteEmprestimos = limite;
    }

    @Override
    public String toString() {
        return id + " - " + nome + " | " + emprestimosAtuais + "/" + limiteEmprestimos;
    }
}

class Aluno extends Usuario {

    Aluno(String id, String nome) {
        super(id, nome, 3);
    }
}

class Professor extends Usuario {

    Professor(String id, String nome) {
        super(id, nome, 5);
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

    @Override
    public String toString() {
        return id + " - " + titulo + " - " + autor + " (" + tipo + ")" + " = " + exemplaresDisponiveis + "/"
                + totalExemplares;
    }
}
