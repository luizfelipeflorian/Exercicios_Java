package escolar;

public class Aluno extends Pessoa {

    private String matricula;

    public Aluno(String nome, String cpf, String endereco, String matricula) {
        super(nome, cpf, endereco);
        this.matricula = matricula;
    }

    @Override
    public void exibirDados() {
        super.exibirDados();
        System.out.println("Matricula: " + matricula);
    }
}
