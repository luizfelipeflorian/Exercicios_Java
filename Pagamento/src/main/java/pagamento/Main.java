package pagamento;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner escolhascan = new Scanner(System.in);
        Scanner scan = new Scanner(System.in);
        System.out.println("Digite o valor a ser pago:");
        double valor = scan.nextDouble();
        System.out.println("Escolha a forma de pagamento:");
        System.out.println("1 - Boleto");
        System.out.println("2 - Cartão");
        int escolha = escolhascan.nextInt();

        Pagamento pag;
        switch (escolha) {
            case 1:
                pag = new PagamentoBoleto(valor);
                pag.processar();
                break;
            case 2:
                pag = new PagamentoCartao(valor);
                pag.processar();
                break;
            default:
                System.out.println("Método inválido!");
        }
    }
}
