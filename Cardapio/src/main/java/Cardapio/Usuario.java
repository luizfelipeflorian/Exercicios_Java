package Cardapio;

public abstract class Usuario {

    protected String usuario;
    protected String senha;

    public abstract boolean fazerLogin(String usuario, String senha);

    public abstract void visualizarMenu();
}
