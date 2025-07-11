package padaria;

public class Bebida extends Produto {

    private int volume; // em ml

    public Bebida(String nome, double preco, int volume) {
        super(nome, preco);
        this.volume = volume;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("Bebida: " + getNome());
        System.out.println("Preço: R$" + getPreco());
        System.out.println("Volume: " + volume + " ml");
    }
}
