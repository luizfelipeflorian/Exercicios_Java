package calculadora;

import java.util.Random; //biblioteca de aleatorização "Random rand = new Random();" || "= rand.nextInt(100);"
import java.util.Scanner; //biblioteca de leitura de entrada "Scanner valor = new Scanner(System.in);" || "= valor.nextInt();"

public class Main {

    public static void main(String[] args) {
        Calculadora calc = new Calculadora();
        Scanner sc = new Scanner(System.in);

        int opcao;

        do {
            System.out.println("-Calculadora- (^-^)");
            System.out.println("1 - Somar");
            System.out.println("2 - Subtrair");
            System.out.println("3 - Multiplicar");
            System.out.println("4 - Dividir");
            System.out.println("5 - Potenciação");
            System.out.println("6 - Raiz");
            System.out.println("0 - Sair");
            System.out.println("Escolha uma opção:");
            opcao = sc.nextInt();
            if (opcao >= 1 && opcao <= 5) {
                System.out.print("Digite o primeiro valor: ");
                double a = sc.nextDouble();

                System.out.print("Digite o segundo valor: ");
                double b = sc.nextDouble();

                try {
                    double resultado = switch (opcao) {
                        case 1 ->
                            calc.somar(a, b);
                        case 2 ->
                            calc.subtrair(a, b);
                        case 3 ->
                            calc.multiplicar(a, b);
                        case 4 ->
                            calc.dividir(a, b);
                        case 5 ->
                            calc.potenciacao(a, b);
                        default ->
                            0;
                    };
                    System.out.print("Resultado: " + resultado);
                } catch (ArithmeticException e) {
                    System.out.print("Erro: " + e.getMessage());
                }
            } else if (opcao == 6) {
                System.out.print("Digite o primeiro valor: ");
                double a = sc.nextDouble();
                double resultado = calc.raiz(a);
                System.out.print("Resultado: " + resultado);

            } else if (opcao != 0) {
                System.out.print("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
        System.out.print("Encerrando calculadora...");
        sc.close();
    }
}
