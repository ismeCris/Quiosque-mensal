package view;

import controller.ClientesController;
import controller.FuncionarioController;
import controller.QuiosqueController;
import controller.ReservasController;
import model.entities.ClientesEntity;
import model.entities.FuncionariosEntity;
import model.entities.QuiosqueEntity;
import model.entities.ReservasEntity;
import model.repositories.QuiosqueRepository;
import model.repositories.ResevasRepository;
import model.service.ReservaService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class ExibirDados {
    private static FuncionarioController funcionarioController = new FuncionarioController();
    private static ClientesController clientesController = new ClientesController();
    private static QuiosqueRepository quiosqueRepository = new QuiosqueRepository();
    private static QuiosqueController quiosqueController = new QuiosqueController();
    private static ReservasController reservasController = new ReservasController(new ReservaService(new ResevasRepository()));


    public static void ShowFucionarioById(Long id) {
        FuncionariosEntity funcionario = funcionarioController.findFuncionarioById(id);
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

    public static void ShowQuiosqueById(Long id) {
        Optional<QuiosqueEntity> quiosque = Optional.ofNullable(quiosqueController.findQuiosqueById(id));

        if (quiosque.isPresent()) {
            System.out.println("Detalhes do Quiosque:");
            System.out.println("ID: " + quiosque.get().getId());
            System.out.println("Número: " + quiosque.get().getNumero());
            System.out.println("Localidade: " + quiosque.get().getLocalidade());
            System.out.println("Capacidade: " + quiosque.get().getCapacidade());
            System.out.println("Status de Disponibilidade: " + (quiosque.get().getDispoStatus() ? "Disponível" : "Indisponível"));
        } else {
            System.out.println("Nenhum quiosque encontrado com o ID fornecido.");

        }
    }
    public static void setReservasController(ReservasController controller) {
        reservasController = controller;
    }

    public static void mostrarReservasPorData(LocalDate data) {
        List<ReservasEntity> reservas = reservasController.encontrarReservasPorData(data);

        if (!reservas.isEmpty()) {
            System.out.println("Reservas encontradas para a data " + data + ":");
            for (ReservasEntity reserva : reservas) {
                System.out.println("Detalhes da Reserva:");
                System.out.println("ID: " + reserva.getId());
                System.out.println("Data de Início: " + reserva.getDataInicio());
                System.out.println("Data de Fim: " + reserva.getDataFim());
                System.out.println();
            }
        } else {
            System.out.println("Nenhuma reserva encontrada para a data fornecida.");
        }
    }
}