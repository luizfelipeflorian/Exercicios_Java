package Cardapio;

public class PagamentoPix implements IPagamento {
    @Override
    public void processarPagamento(double valor) {
        System.out.println("Pagamento via Pix processado: R$" + valor);
    }
}

