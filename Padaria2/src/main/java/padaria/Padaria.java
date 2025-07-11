package padaria;

import java.util.ArrayList;
import java.util.List;

public class Padaria {

    public static void main(String[] args) {
        Detalhavel pao = new Pao("Francês", 0.50);
        Detalhavel bolo = new Bolo("Chocolate", 35.00);
        Detalhavel bebida = new Bebida("Suco de Laranja", 500, 7.00);
        List <Detalhavel> produtos = new ArrayList<>();
        produtos.add(pao);
        produtos.add(bolo);
        produtos.add(bebida);
        System.out.println("Produtos disponíveis na padaria:");
        for (Detalhavel produto : produtos) {
            produto.exibirDetalhes();
        }
    }
}
