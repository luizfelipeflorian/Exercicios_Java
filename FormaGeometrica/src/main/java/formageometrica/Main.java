package formageometrica;

public class Main {

    public static void main(String[] args) {
        FormaGeometrica retangulo = new Retangulo(1, 2);
        FormaGeometrica circulo = new Circulo(3);
        FormaGeometrica triangulo = new Triangulo(3, 4, 5);

        System.out.println("Retangulo");
        retangulo.exibirMedidas();
        
        System.out.println("Circulo");
        circulo.exibirMedidas();
        
        System.out.println("Triangulo");
        triangulo.exibirMedidas();
    }
}
