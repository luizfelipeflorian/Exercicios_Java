package pagamento;

public class PagamentoCartao extends Pagamento{

    public PagamentoCartao(double valor) {
        super(valor);
    }

    @Override
    public void processar() {
        System.out.println("Método: Cartão");
        System.out.println("Valor: " + getValor());
        System.out.println("Processando pagamento...");
        System.out.println("-------------------------------");
        System.out.println("Pagamento efetuado com sucesso!");
        System.out.println("-------------------------------");
    }
}
