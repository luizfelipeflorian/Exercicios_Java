package padaria;

public class Bebida implements Detalhavel {

    private String nome;
    private double volume;
    private double preco;

    public Bebida(String nome, double volume, double preco) {
        this.nome = nome;
        this.volume = volume;
        this.preco = preco;
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("Bebida: " + nome + " (" + volume + "ml), Preço: R$" + preco);
    }
}
