package Cardapio;

public class Bebida extends ProdutoMenu {
    private int volumeML;

    public Bebida(String nome, double preco, int volumeML) {
        super(nome, preco);
        this.volumeML = volumeML;
    }

    @Override
    public void mostrarDetalhes() {
        System.out.println("Bebida: " + nome + " - R$" + preco + " - " + volumeML + "ml");
    }
}

