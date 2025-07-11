package calculadora;

public class Calculadora {

    public double somar(double a, double b) {
        return a + b;
    }

    public double subtrair(double a, double b) {
        return a - b;
    }

    public double multiplicar(double a, double b) {
        return a * b;
    }

    public double dividir(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Divisão por zero não é permitida");
        }
        return a / b;
    }

    public double potenciacao(double a, double b) {
        return Math.pow(a, b);
    }

    public double raizQ(double a) {
        return Math.sqrt(a);
    }

    public double raizY(double a, double b) {
        return Math.pow(a, 1.0 / b);
    }

    public double resto(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Divisão por zero não é permitida");
        }
        return a % b;
    }

    public int fatorial(int a) {
        int result = 1;
        for (int i = 2; i <= a; i++) {    
            result *= i;
        }
        return result;
    }

    double raiz(double a) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}