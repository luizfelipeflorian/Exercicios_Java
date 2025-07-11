package padaria;

public class Pao implements Detalhavel {

    private String tipo;
    private double preco;

    public Pao(String tipo, double preco) {
        this.tipo = tipo;
        this.preco = preco;
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("Pão: " + tipo + ", Preço: R$" + preco);
    }
}
