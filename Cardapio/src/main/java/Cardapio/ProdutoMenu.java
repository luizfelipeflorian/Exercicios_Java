package Cardapio;

public abstract class ProdutoMenu {
    protected String nome;
    protected double preco;

    public ProdutoMenu(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public abstract void mostrarDetalhes();
}

