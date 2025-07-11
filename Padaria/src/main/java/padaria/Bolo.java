package padaria;

public class Bolo extends Produto {

    private String sabor;

    public Bolo(String nome, double preco, String sabor) {
        super(nome, preco);
        this.sabor = sabor;
    }

    public String getSabor() {
        return sabor;
    }

    public void setSabor(String sabor) {
        this.sabor = sabor;
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("Bolo: " + getNome());
        System.out.println("Preço: R$" + getPreco());
        System.out.println("Sabor: " + sabor);
    }
}
