package escolar;

public class Funcionario extends Pessoa {

    private String setor;

    public Funcionario(String nome, String cpf, String endereco, String setor) {
        super(nome, cpf, endereco);
        this.setor = setor;
    }

    @Override
    public void exibirDados() {
        super.exibirDados();
        System.out.println("Setor: " + setor);
    }
}
