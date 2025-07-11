package pagamento;

public class PagamentoBoleto extends Pagamento {

    public PagamentoBoleto(double valor) {
        super(valor);
    }

    @Override
    public void processar() {
        System.out.println("Método: Boleto");
        System.out.println("Valor: " + getValor());
        System.out.println("Processando pagamento...");
        System.out.println("-------------------------------");
        System.out.println("Pagamento efetuado com sucesso!");
        System.out.println("-------------------------------");
    }
}
