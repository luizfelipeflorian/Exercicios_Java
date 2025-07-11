package Cardapio;

import java.util.ArrayList;
import java.util.Scanner;

public class SistemaCardapioBar {
    private static ArrayList<ProdutoMenu> cardapio = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Produtos iniciais
        cardapio.add(new Bebida("Cerveja", 10.0, 500));
        cardapio.add(new Petisco("Batata Frita", 15.0, true));

        System.out.println("Bem-vindo ao sistema do Bar!");
        Usuario usuario = null;

        // Login
        while(usuario == null) {
            System.out.print("Login (cliente/admin): ");
            String tipo = sc.nextLine();
            System.out.print("Usuário: ");
            String user = sc.nextLine();
            System.out.print("Senha: ");
            String senha = sc.nextLine();

            if(tipo.equalsIgnoreCase("cliente")) {
                Cliente c = new Cliente();
                if(c.fazerLogin(user, senha)) {usuario = c;}
            } else if(tipo.equalsIgnoreCase("admin")) {
                Administrador a = new Administrador();
                if(a.fazerLogin(user, senha)) {usuario = a;}
            } else {
                System.out.println("Tipo de usuário inválido.");
            }
        }

        // Menu principal
        boolean sair = false;
        while(!sair) {
            usuario.visualizarMenu();
            System.out.print("Escolha uma opção: ");
            int opc = sc.nextInt();
            sc.nextLine();

            if(usuario instanceof Cliente) {
                Cliente c = (Cliente) usuario;
                switch(opc) {
                    case 1: // Ver cardápio
                        System.out.println("Cardápio:");
                        for(ProdutoMenu p : cardapio) p.mostrarDetalhes();
                        break;
                    case 2: // Fazer pedido
                        double total = 0;
                        System.out.println("Digite o nome do produto para pedir (fim para encerrar):");
                        while(true) {
                            String pedido = sc.nextLine();
                            if(pedido.equalsIgnoreCase("fim")) break;
                            boolean achou = false;
                            for(ProdutoMenu p : cardapio) {
                                if(p.getNome().equalsIgnoreCase(pedido)) {
                                    System.out.println(pedido + " adicionado ao pedido.");
                                    total += p.getPreco();
                                    achou = true;
                                    break;
                                }
                            }
                            if(!achou) System.out.println("Produto não encontrado.");
                        }
                        System.out.println("Total do pedido: R$" + total);
                        System.out.println("Escolha método de pagamento: 1-Cartão 2-Pix");
                        int metodo = sc.nextInt();
                        sc.nextLine();
                        IPagamento pagamento = (metodo == 1) ? new PagamentoCartao() : new PagamentoPix();
                        pagamento.processarPagamento(total);
                        break;
                    case 3: // Enviar feedback
                        System.out.print("Digite seu feedback: ");
                        String fb = sc.nextLine();
                        c.enviarFeedback(fb);
                        break;
                    case 4:
                        sair = true;
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }
            } else if(usuario instanceof Administrador) {
                Administrador a = (Administrador) usuario;
                switch(opc) {
                    case 1:
                        System.out.println("Cardápio:");
                        for(ProdutoMenu p : cardapio) p.mostrarDetalhes();
                        break;
                    case 2:
                        a.editarCardapio(cardapio, sc);
                        break;
                    case 3:
                        sair = true;
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }
            }
        }

        System.out.println("Sistema encerrado. Obrigado!");
        sc.close();
    }
}
