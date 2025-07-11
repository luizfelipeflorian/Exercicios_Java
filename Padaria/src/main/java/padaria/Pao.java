package padaria;

public class Pao extends Produto {

    private String tipoFarinha;

    public Pao(String nome, double preco, String tipoFarinha) {
        super(nome, preco);
        this.tipoFarinha = tipoFarinha;
    }

    public String getTipoFarinha() {
        return tipoFarinha;
    }

    public void setTipoFarinha(String tipoFarinha) {
        this.tipoFarinha = tipoFarinha;
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("Pão: " + getNome());
        System.out.println("Preço: R$" + getPreco());
        System.out.println("Tipo de Farinha: " + tipoFarinha);
    }
}
