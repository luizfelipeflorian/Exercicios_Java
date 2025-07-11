package escolar;

public class Professor extends Pessoa {

    private String disciplina;

    public Professor(String nome, String cpf, String endereco, String disciplina) {
        super(nome, cpf, endereco);
        this.disciplina = disciplina;
    }

    @Override
    public void exibirDados() {
        super.exibirDados();
        System.out.println("Disciplina: " + disciplina);
    }
}
