package view;

import controller.UsuarioController;
import model.Entity.Usuario;

import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Login();
    }

    public static void Login(){
        System.out.println("Realize o login");

        System.out.println("Nome de usuario:");
        String nome = sc.nextLine();

        System.out.println("Senha:");
        String senha = sc.nextLine();

        Usuario AtenticarUser = UsuarioController.login(nome, senha);
        if(AtenticarUser != null){
            System.out.println("Bem-Vind@, " + AtenticarUser.getNome()+ "!");
            menuprinciapal();
        }else {
            System.out.println("Nome de usuário ou senha incorretos. Por favor, tente novamente.");
            Login();
        }

    }
    public static void menuprinciapal() {
        Scanner sc = new Scanner(System.in);

        boolean sair = false;

        while(!sair) {

            System.out.println("===== Menu Principal =====");
            System.out.println("-> 1. Gerenciar usuarios");
            System.out.println("-> 2. Gerenciar vendas");
            System.out.println("-> 3. Gerenciar vendas");
            System.out.println("-> 4. Relatorios");
            System.out.println("-> 0. sair \n");
            System.out.print("Escolha uma opção: ");

            int opcao = sc.nextInt();
            sc.nextLine();

            switch(opcao) {
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 0:
                    sair = true;
                    System.out.println("Volte sempre..........");
                    break;

                default:
                    System.out.println("Opcao invalida. Tente novamente");

            }

        }

    }
}
