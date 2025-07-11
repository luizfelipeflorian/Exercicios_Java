package padaria;

public class Bolo implements Detalhavel {

    private String sabor;
    private double preco;

    public Bolo(String sabor, double preco) {
        this.sabor = sabor;
        this.preco = preco;
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("Bolo: " + sabor + ", Preço: R$" + preco);
    }
}
