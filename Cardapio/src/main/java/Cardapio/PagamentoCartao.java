package Cardapio;

public class PagamentoCartao implements IPagamento {
    @Override
    public void processarPagamento(double valor) {
        System.out.println("Pagamento no cartão processado: R$" + valor);
    }
}
