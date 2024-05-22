package view;

import controller.ClientesController;
import controller.FuncionarioController;
import model.entities.ClientesEntity;
import model.entities.FuncionariosEntity;

public class ExibirDados {
    private static FuncionarioController funcionarioController = new FuncionarioController();
    private static ClientesController clientesController = new ClientesController();

    public static void ShowFucionarioById(Long id){
        FuncionariosEntity funcionario = funcionarioController.findFuncionarioById( id);
        if (funcionario != null) {
            System.out.println("ID: " + funcionario.getId());
            System.out.println("Nome: " + funcionario.getNome());
            System.out.println("Cargo: " + funcionario.getCargo());
            System.out.println("Email: " + funcionario.getEmail());
            System.out.println("Telefone: " + funcionario.getTelefone());

        } else {
            System.out.println("Funcionário não encontrado.");
        }
    }

        public static void ShowClientesById(Long id) {
            ClientesEntity cliente = clientesController.findClienteById(id);
            if (cliente != null) {
                System.out.println("Detalhes do Cliente:");
                System.out.println("ID: " + cliente.getId());
                System.out.println("Nome: " + cliente.getNome());
                System.out.println("Email: " + cliente.getEmail());
                System.out.println("Telefone: " + cliente.getTelefone());
                System.out.println("Idade: " + cliente.getIdade());
                System.out.println("CPF: " + cliente.getCpf());
                System.out.println("Status: " + cliente.getUserStatus());
            } else {
                System.out.println("Nenhum cliente encontrado com o ID fornecido.");
            }
        }







}
