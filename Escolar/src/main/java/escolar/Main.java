package escolar;

public class Main {

    public static void main(String[] args) {
        Aluno aluno = new Aluno("Luiz Felipe", "06850947150", "Rua Z", "12345678");
        System.out.println("===Aluno===");
        aluno.exibirDados();
        System.out.println("CPF é vaido? " + aluno.validarCPF());
        System.out.println("");

        Professor professor = new Professor("Luiz Felipe", "06850947150", "Rua Z", "Física");
        System.out.println("===Professor===");
        aluno.exibirDados();
        System.out.println("CPF é vaido? " + aluno.validarCPF());
        System.out.println("");
        
        Funcionario funcionario = new Funcionario("Luiz Felipe", "06850947150", "Rua Z", "Administração");
        System.out.println("===Funcionario===");
        aluno.exibirDados();
        System.out.println("CPF é vaido? " + aluno.validarCPF());
    }
}
