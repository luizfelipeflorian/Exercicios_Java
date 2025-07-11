package Cardapio;

public class Cliente extends Usuario implements IAvaliacao {
    @Override
    public boolean fazerLogin(String usuario, String senha) {
        if(usuario.equals("cliente") && senha.equals("1234")) {
            this.usuario = usuario;
            this.senha = senha;
            System.out.println("Cliente logado com sucesso!");
            return true;
        }
        System.out.println("Login do cliente falhou.");
        return false;
    }

    @Override
    public void visualizarMenu() {
        System.out.println("Menu Cliente:");
        System.out.println("1. Ver cardápio");
        System.out.println("2. Fazer pedido");
        System.out.println("3. Enviar feedback");
        System.out.println("4. Sair");
    }

    @Override
    public void enviarFeedback(String mensagem) {
        System.out.println("Feedback recebido: " + mensagem);
    }
}

