package Cardapio;

public class Petisco extends ProdutoMenu {
    private boolean vegetariano;

    public Petisco(String nome, double preco, boolean vegetariano) {
        super(nome, preco);
        this.vegetariano = vegetariano;
    }

    @Override
    public void mostrarDetalhes() {
        System.out.println("Petisco: " + nome + " - R$" + preco + " - " + (vegetariano ? "Vegetariano" : "Não vegetariano"));
    }
}
