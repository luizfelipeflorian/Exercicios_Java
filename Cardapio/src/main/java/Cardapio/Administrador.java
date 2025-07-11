package Cardapio;

import java.util.ArrayList;
import java.util.Scanner;

public class Administrador extends Usuario {
    @Override
    public boolean fazerLogin(String usuario, String senha) {
        if(usuario.equals("admin") && senha.equals("admin123")) {
            this.usuario = usuario;
            this.senha = senha;
            System.out.println("Administrador logado com sucesso!");
            return true;
        }
        System.out.println("Login do administrador falhou.");
        return false;
    }

    @Override
    public void visualizarMenu() {
        System.out.println("Menu Administrador:");
        System.out.println("1. Ver cardápio");
        System.out.println("2. Editar cardápio");
        System.out.println("3. Sair");
    }

    public void editarCardapio(ArrayList<ProdutoMenu> cardapio, Scanner sc) {
        System.out.println("Editar cardápio:");
        System.out.println("1. Adicionar produto");
        System.out.println("2. Remover produto");
        int opc = sc.nextInt();
        sc.nextLine(); // consumir enter
        if(opc == 1) {
            System.out.print("Nome do produto: ");
            String nome = sc.nextLine();
            System.out.print("Preço: ");
            double preco = sc.nextDouble();
            sc.nextLine();
            System.out.print("Tipo (1-Bebida, 2-Petisco): ");
            int tipo = sc.nextInt();
            sc.nextLine();
            if(tipo == 1) {
                System.out.print("Volume (ml): ");
                int volume = sc.nextInt();
                sc.nextLine();
                cardapio.add(new Bebida(nome, preco, volume));
            } else {
                System.out.print("É vegetariano? (true/false): ");
                boolean veg = sc.nextBoolean();
                sc.nextLine();
                cardapio.add(new Petisco(nome, preco, veg));
            }
            System.out.println("Produto adicionado.");
        } else if(opc == 2) {
            System.out.print("Nome do produto a remover: ");
            String nome = sc.nextLine();
            cardapio.removeIf(p -> p.getNome().equalsIgnoreCase(nome));
            System.out.println("Produto removido se existia.");
        }
    }
}

