package formageometrica;

public abstract class FormaGeometrica {

    public abstract double calcularArea();

    public abstract double calcularPerimetro();

    public void exibirMedidas() {
        System.out.println("Área: " + calcularArea());
        System.out.println("Perímetro: " + calcularPerimetro());
    }

}
