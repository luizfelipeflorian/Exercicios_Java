package escolar;

public class Pessoa {

    protected String nome;
    protected String cpf;
    protected String endereco;

    public Pessoa(String nome, String cpf, String endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
    }

    public void exibirDados() {
        System.out.println("Nome: " + nome);
        System.out.println("Cpf: " + cpf);
        System.out.println("Endereco: " + endereco);
    }

    public boolean validarCPF() {
        return cpf != null && cpf.matches("\\d{11}");
    }
}
