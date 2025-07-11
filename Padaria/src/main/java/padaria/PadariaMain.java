package padaria;


public class PadariaMain {
    public static void main(String[] args) {
        Produto pao = new Pao("Baguette", 5.00, "Farinha de Trigo");
        Produto bolo = new Bolo("Bolo de Chocolate", 30.00, "Chocolate");
        Produto bebida = new Bebida("Suco de Laranja", 7.00, 500);

        System.out.println("Produtos disponíveis na padaria:\n");

        pao.exibirDetalhes();
        System.out.println();

        bolo.exibirDetalhes();
        System.out.println();
        
        bebida.exibirDetalhes();
    }
}
