package formageometrica;

class Retangulo extends FormaGeometrica {

    private double largura;
    private double altura;

    public Retangulo(double largura, double altura) {
        this.largura = largura;
        this.altura = altura;
    }
    
    @Override
    public double calcularArea(){
        return largura * altura;
    }
    
    @Override
    public double calcularPerimetro(){
        return (largura + altura) * 2;
    }
}
