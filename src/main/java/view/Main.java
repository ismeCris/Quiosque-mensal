package view;

import controller.ClientesController;
import controller.FuncionarioController;
import controller.QuiosqueController;
import controller.ReservasController;
import model.entities.*;
import model.repositories.*;
import model.service.ReservaService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static FuncionarioController funcionarioController = new FuncionarioController();
    private static ClientesController clientesController = new ClientesController();
    QuiosqueController quiosqueController = new QuiosqueController();

    public static void main(String[] args) {
        exibirMenuPrincipal();
    }

    private static void exibirMenuPrincipal() {

        System.out.print("Nome de usuário: ");
        String nome = sc.nextLine();

        System.out.print("Senha: ");
        String senha = sc.nextLine();

        FuncionariosEntity funcionarioAutenticado = funcionarioController.login(nome, senha);

        if (funcionarioAutenticado != null) {
            System.out.println("Bem-vindo, " + funcionarioAutenticado.getNome() + "!");

            exibirMenu();
        } else {
            System.out.println("Nome de usuário ou senha incorretos. Por favor, tente novamente.");
            exibirMenuPrincipal();
        }
    }

    private static void exibirMenu() {
        System.out.println("1 - Gestão de funcionarios");
        System.out.println("2 - Gestão de Clientes");
        System.out.println("3 - Gestão de Quiosques");
        System.out.println("4 - Gestão de Reservas");
        System.out.println("0 - sair");
        System.out.println("Escolha uma opção");

        int opcao = sc.nextInt();
        sc.nextLine();

        switch (opcao) {
            case 1:
                exibirOpcoesFuncionario();
                break;
            case 2:
                exibirOpcoesCliente();
                break;
            case 3:
                exibirOpcoesQuiosque();
                break;
            case 4:
                exibirOpcoesReservas();
                break;
            case 0:
                System.out.println("Saindo...");
                break;
            default:
                System.out.println("Opção inválida");
                exibirMenu();
                break;
        }
    }

    private static void exibirOpcoesFuncionario() {
        System.out.println("=======- Gestao de funcionarios -=======");
        System.out.println("1 - Buscar funcionário pelo ID");
        System.out.println("2 - Editar funcionário");
        System.out.println("3 - Novo funcionário");
        System.out.println("4 - Excluir funcionário");
        System.out.println("5 - Lista de funcionarios");
        System.out.println("0 - Voltar ao menu principal");
        System.out.println("Escolha uma opção:");

        int opcao = sc.nextInt();
        sc.nextLine();

        switch (opcao) {
            case 1:
                System.out.println("Digite o ID do funcionário:");
                long id = sc.nextLong();
                ExibirDados.ShowFucionarioById(id);
                aguardarEnter();
                exibirOpcoesFuncionario();
                break;
            case 2:
                editarFuncionario();
                aguardarEnter();
                exibirOpcoesFuncionario();

                break;
            case 3:
                criarNovoFuncionario();
                aguardarEnter();
                exibirOpcoesFuncionario();
                break;
            case 4:
                System.out.println("Digite o ID do funcionário a ser excluído:");
                long deleteId = sc.nextLong();
               funcionarioController.deleteFuncionarioById(deleteId);
                System.out.println("Funcionário excluído.");
                aguardarEnter();
                exibirOpcoesFuncionario();
                break;
            case 5:
                ListarTodosFuncionarios();
                aguardarEnter();
                exibirOpcoesFuncionario();
                break;
            case 0:
                exibirMenu();
                break;
            default:
                System.out.println("Opção inválida.");
                exibirOpcoesFuncionario();
                break;
        }
    }

    private static void editarFuncionario() {
        System.out.println("Digite o ID do funcionário a ser editado:");
        long id = sc.nextLong();
        sc.nextLine();

        FuncionariosEntity funcionario = funcionarioController.findFuncionarioById(id);
        if (funcionario == null) {
            System.out.println("Funcionário não encontrado.");
            return;
        }

        System.out.println("Digite o novo nome (ou pressione Enter para manter o atual: " + funcionario.getNome() + "):");
        String novoNome = sc.nextLine();
        if (!novoNome.isEmpty()) {
            funcionario.setNome(novoNome);
        }

        System.out.println("Digite a nova senha (ou pressione Enter para manter a atual):");
        String novaSenha = sc.nextLine();
        if (!novaSenha.isEmpty()) {
            funcionario.setSenha(novaSenha);
        }

        System.out.println("Digite o novo email (ou pressione Enter para manter o atual: " + funcionario.getEmail() + "):");
        String novoEmail = sc.nextLine();
        if (!novoEmail.isEmpty()) {
            funcionario.setEmail(novoEmail);
        }

        System.out.println("Digite o novo telefone (ou pressione Enter para manter o atual: " + funcionario.getTelefone() + "):");
        String novoTelefone = sc.nextLine();
        if (!novoTelefone.isEmpty()) {
            funcionario.setTelefone(novoTelefone);
        }

        System.out.println("Digite o novo cargo (ou pressione Enter para manter o atual: " + funcionario.getCargo() + "):");
        String novoCargo = sc.nextLine();
        if (!novoCargo.isEmpty()) {
            funcionario.setCargo(novoCargo);
        }

        funcionarioController.updateFuncionario(funcionario);
        System.out.println("Funcionário atualizado com sucesso.");
    }

    private static void criarNovoFuncionario() {
        FuncionariosRepository funcionariosRepository = new FuncionariosRepository();
        FuncionariosEntity novoFuncionario = new FuncionariosEntity();

        System.out.println("Digite o nome do novo funcionário:");
        String nome = sc.nextLine();
        novoFuncionario.setNome(nome);

        System.out.println("Digite a senha do novo funcionário:");
        String senha = sc.nextLine();

        FuncionariosEntity funcionarioExistente = funcionariosRepository.findBySenha(senha);
        if (funcionarioExistente != null) {
            System.out.println("Já existe um funcionário com a mesma senha. Por favor, escolha outra senha.");
            return;
        }

        novoFuncionario.setSenha(senha);

        System.out.println("Digite o email do novo funcionário:");
        String email = sc.nextLine();
        if (email.length() > 255) {
            System.out.println("O email excede o limite de 255 caracteres. Por favor, insira um email mais curto.");
            return;
        }
        novoFuncionario.setEmail(email);

        System.out.println("Digite o telefone do novo funcionário:");
        String telefone = sc.nextLine();
        novoFuncionario.setTelefone(telefone);

        System.out.println("Digite o cargo do novo funcionário:");
        String cargo = sc.nextLine();
        novoFuncionario.setCargo(cargo);

        FuncionariosEntity criadoFuncionario = funcionarioController.createFuncionario(novoFuncionario);

        if (criadoFuncionario != null) {
            System.out.println("Novo funcionário criado com sucesso.");
            System.out.println("ID: " + criadoFuncionario.getId());
            System.out.println("Nome: " + criadoFuncionario.getNome());
            System.out.println("Senha: " + senha);
            System.out.println("Email: " + criadoFuncionario.getEmail());
            System.out.println("Telefone: " + criadoFuncionario.getTelefone());
            System.out.println("Cargo: " + criadoFuncionario.getCargo());
        } else {
            System.out.println("Falha ao criar novo funcionário.");
        }
    }

    private static void ListarTodosFuncionarios(){
        FuncionariosRepository funcionariosRepository = new FuncionariosRepository();
        List<FuncionariosEntity> funcionarios = funcionariosRepository.findAll();

        if (funcionarios.isEmpty()) {
            System.out.println("Não há funcionários cadastrados no sistema.");
        } else {
            System.out.println("Lista de funcionários cadastrados:");
            for (FuncionariosEntity funcionario : funcionarios) {
                System.out.println("ID: " + funcionario.getId() + ", Nome: " + funcionario.getNome() + ", Cargo: " + funcionario.getCargo());
            }
    }
    }

// =====================================================================================================================
    private static void exibirOpcoesCliente() {
        System.out.println("=======- Gestao de clientes -=======");
        System.out.println("1 - Buscar cliente pelo ID");
        System.out.println("2 - Editar dados do cliente");
        System.out.println("3 - Novo cliente");
        System.out.println("4 - Excluir cliente");
        System.out.println("5 - Lista de funcionarios ");
        System.out.println("0 - Voltar ao menu principal");
        System.out.println("Escolha uma opção:");

        int opcao = sc.nextInt();
        sc.nextLine();
        switch (opcao) {
            case 1:
                System.out.println("Digite o ID do cliente:");
                Long clienteId = sc.nextLong();
                sc.nextLine();
                ExibirDados.ShowClientesById(clienteId);
                break;
            case 2:
                editarCliente();
                aguardarEnter();
                exibirOpcoesCliente();
                break;
            case 3:
                criarNovoCliente();
                aguardarEnter();
                exibirOpcoesCliente();

                break;
            case 4:
                System.out.println("Digite o ID do cliente a ser excluído:");
                long deleteId = sc.nextLong();
                ClientesController clienteController = new ClientesController();
                clienteController.deleteClienteById(deleteId);
                System.out.println("Cliente excluído.");
                aguardarEnter();
                exibirOpcoesCliente();
                break;
            case 5:
                ListarTodosClientes();
                aguardarEnter();
                exibirOpcoesCliente();
                break;
            case 0:
                exibirMenu();
                break;
            default:
                System.out.println("Opção inválida.");
                exibirOpcoesFuncionario();
                break;
        }

    }

    private static void editarCliente() {
        System.out.println("Digite o ID do Cliente a ser editado:");
        long id = sc.nextLong();
        sc.nextLine();

        ClientesEntity cliente = ClientesController.findClienteById(id);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        System.out.println("Digite o novo nome (ou pressione Enter para manter o atual: " + cliente.getNome() + "):");
        String novoNome = sc.nextLine();
        if (!novoNome.isEmpty()) {
            cliente.setNome(novoNome);
        }

        System.out.println("Digite o novo email (ou pressione Enter para manter o atual: " + cliente.getEmail() + "):");
        String novoEmail = sc.nextLine();
        if (!novoEmail.isEmpty()) {
            cliente.setEmail(novoEmail);
        }

        System.out.println("Digite o novo telefone (ou pressione Enter para manter o atual: " + cliente.getTelefone() + "):");
        String novoTelefone = sc.nextLine();
        if (!novoTelefone.isEmpty()) {
            cliente.setTelefone(novoTelefone);
        }

        System.out.println("Digite a nova idade (ou pressione Enter para manter a atual: " + cliente.getIdade() + "):");
        String novaIdade = sc.nextLine();
        if (!novaIdade.isEmpty()) {
            cliente.setIdade(Integer.parseInt(novaIdade));
        }

        System.out.println("Digite o novo CPF (ou pressione Enter para manter o atual: " + cliente.getCpf() + "):");
        String novoCpf = sc.nextLine();
        if (!novoCpf.isEmpty()) {
            cliente.setCpf(novoCpf);
        }

        // Atualizar o cliente no banco de dados
        clientesController.updateCliente(cliente);

        System.out.println("Cliente atualizado com sucesso.");
    }

    private static void criarNovoCliente() {

        ClientesRepository clientesRepository = new ClientesRepository();
        ClientesEntity novocliente =new ClientesEntity();

        System.out.println("Cadastro de Novo Cliente");

        System.out.print("Digite o nome do novo Cliente: ");
        String nome = sc.nextLine();
        novocliente.setNome(nome);

        System.out.print("Digite o CPF do novo Cliente: ");
        String cpf = sc.nextLine();

        ClientesEntity clienteExistente = (ClientesEntity) clientesRepository.findById(cpf);
        if (clienteExistente != null) {
            System.out.println("Já existe um cliente cadastrado com o CPF informado.");
            System.out.println("Pressione Enter para retornar ao menu de clientes.");
            sc.nextLine();
            aguardarEnter();
            return;
        }
        novocliente.setCpf(cpf);

        System.out.print("Digite o email do novo Cliente: ");
        String email = sc.nextLine();
        novocliente.setEmail(email);

        System.out.print("Digite o telefone do novo Cliente: ");
        String telefone = sc.nextLine();
        novocliente.setTelefone(telefone);

        System.out.print("Digite a idade do novo Cliente: ");
        int idade = Integer.parseInt(sc.nextLine());
        novocliente.setIdade(idade);

        System.out.print("Digite o status do novo Cliente (true/false): ");
        boolean status = Boolean.parseBoolean(sc.nextLine());
        novocliente.setUserStatus(status);

        try {
            ClientesEntity criadoCliente = clientesController.createCliente(novocliente);
            if (criadoCliente != null) {
                System.out.println("Novo cliente criado com sucesso.");
                System.out.println("ID: " + criadoCliente.getId());
                System.out.println("Nome: " + criadoCliente.getNome());
                System.out.println("CPF: " + criadoCliente.getCpf());
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }

    private static void ListarTodosClientes(){
        ClientesRepository clientesRepository = new ClientesRepository();
        List<ClientesEntity> clientes = clientesRepository.findAll();

        if (clientes.isEmpty()) {
            System.out.println("Não há clientes cadastrados no sistema.");
        } else {
            System.out.println("Lista de clientes cadastrados:");
            for (ClientesEntity cliente : clientes) {
                System.out.println("ID: " + cliente.getId() + ", Nome: " + cliente.getNome() + ", status: " + cliente.getUserStatus());
            }
        }
    }
//======================================================================================================================
    private static void exibirOpcoesQuiosque() {
        QuiosqueController quiosqueController = new QuiosqueController();

        System.out.println("=======- Gestao de Quiosques -=======");
        System.out.println("1 - Buscar quiosque pelo numero");
        System.out.println("2 - Editar dados da quiosque");
        System.out.println("3 - novo quiosque");
        System.out.println("4 - excluir ou desativar quiosque");
        System.out.println("5 - Lista de quiosques");
        System.out.println("0 - Voltar ao menu principal");
        System.out.println("Escolha uma opção:");

        int opcao = sc.nextInt();
        sc.nextLine();
        switch (opcao) {
            case 1:
                System.out.println("Digite o ID do quiosque:");
                Long quiosqueId = sc.nextLong();
                sc.nextLine();
                ExibirDados.ShowQuiosqueById(quiosqueId);
                aguardarEnter();
                exibirOpcoesQuiosque();
                break;
            case 2:
                editarQuiosque();
                aguardarEnter();
                exibirOpcoesQuiosque();
                break;
            case 3:
                criarNovoQuiosque();
                aguardarEnter();
                exibirOpcoesQuiosque();
                break;
            case 4:
                System.out.println("Digite o ID do quiosque a ser excluído:");
                long deleteId = sc.nextLong();

                quiosqueController.deleteQuiosque(deleteId);
                System.out.println("quiosque excluído.");
                aguardarEnter();
                exibirOpcoesQuiosque();
                break;
            case 5:
                ListarTodosQuiosques();
                aguardarEnter();
                exibirOpcoesQuiosque();
                break;
            case 0:
                exibirMenu();
                break;
            default:
                System.out.println("Opção inválida.");
                exibirOpcoesQuiosque();
                break;
        }

    }


    private static void editarQuiosque() {
        System.out.println("Digite o ID do Quiosque a ser editado:");
        long id = sc.nextLong();
        sc.nextLine();
        QuiosqueController quiosqueController = new QuiosqueController();

        QuiosqueEntity quiosque = quiosqueController.findQuiosqueById(id);
        
        if (quiosque == null) {
            System.out.println("Quiosque não encontrado.");
            return;
        }

        System.out.println("Digite o novo numero (ou pressione Enter para manter o atual: " + quiosque.getNumero() + "):");
        String novoNumInput = sc.nextLine();

        if (!novoNumInput.isEmpty()) {
            int novoNum = Integer.parseInt(novoNumInput);
            quiosque.setNumero(novoNum);
        }

        System.out.println("Digite a nova localidade (ou pressione Enter para manter a atual:"+ quiosque.getLocalidade() +"):");
        String novalocalidade = sc.nextLine();
        if (!novalocalidade.isEmpty()) {
            quiosque.setLocalidade(novalocalidade);
        }


        System.out.println("Digite o novo telefone (ou pressione Enter para manter o atual: " + quiosque.getCapacidade() + "):");
        String novaCapaciInput = sc.nextLine();

        if (!novaCapaciInput.isEmpty()) {
            int novaCapaci = Integer.parseInt(novaCapaciInput);
            quiosque.setCapacidade(novaCapaci);
        }


        System.out.println("Digite o novo Status (ou pressione Enter para manter o atual: " + quiosque.getDispoStatus() + "):");
        String novoStatusInput = sc.nextLine();

        if (!novoStatusInput.isEmpty()) {
            boolean novoStatus = Boolean.parseBoolean(novoStatusInput);
            quiosque.setDispoStatus(novoStatus);
        }


        quiosqueController.updateQuiosque(quiosque);
        System.out.println("Quiosque atualizado com sucesso.");
    }

    private static void criarNovoQuiosque() {
        Scanner sc = new Scanner(System.in);
        QuiosqueRepository quiosqueRepository = new QuiosqueRepository();
        QuiosqueEntity novoQuiosque = new QuiosqueEntity();

        System.out.println("Digite o número do novo Quiosque:");
        int numero = sc.nextInt();
        sc.nextLine();

        QuiosqueEntity quiosqueExistente = quiosqueRepository.findById(numero);
        if (quiosqueExistente != null) {
            System.out.println("Já existe um quiosque com o mesmo número. Por favor, escolha outro.");
            return;
        }

        novoQuiosque.setNumero(numero);

        System.out.println("Digite a localidade do novo Quiosque:");
        String localidade = sc.nextLine();

        System.out.println("Digite a capacidade do novo Quiosque:");
        int capacidade = sc.nextInt();
        sc.nextLine();

        System.out.println("Digite o status de disponibilidade do novo Quiosque (true/false):");
        boolean disponibilidadeStatus = sc.nextBoolean();
        sc.nextLine();

        novoQuiosque.setNumero(numero);
        novoQuiosque.setLocalidade(localidade);
        novoQuiosque.setCapacidade(capacidade);
        novoQuiosque.setDispoStatus(disponibilidadeStatus);


        QuiosqueController quiosqueController = new QuiosqueController();

        QuiosqueEntity criadoQuiosque = quiosqueController.createQuiosque(novoQuiosque);

        if (criadoQuiosque != null) {
            System.out.println("Novo Quiosque criado com sucesso.");
            System.out.println("ID: " + criadoQuiosque.getId());
            System.out.println("Número: " + criadoQuiosque.getNumero());
            System.out.println("Localidade: " + criadoQuiosque.getLocalidade());
            System.out.println("Capacidade: " + criadoQuiosque.getCapacidade());
            System.out.println("Status de Disponibilidade: " + criadoQuiosque.getDispoStatus());
        } else {
            System.out.println("Falha ao criar novo Quiosque.");
        }
    }

    private static void ListarTodosQuiosques(){
        QuiosqueRepository quiosqueRepository = new QuiosqueRepository();
        List<QuiosqueEntity> quiosques = quiosqueRepository.findAll();

        if (quiosques.isEmpty()) {
            System.out.println("Não há quiosques cadastrados no sistema.");
        } else {
            System.out.println("Lista de quiosques cadastrados:");
            for (QuiosqueEntity quiosque : quiosques) {
                System.out.println("ID: " + quiosque.getId() + ", Nome: " + quiosque.getNumero() + ", Cargo: " + quiosque.getCapacidade() + ", Cargo: " + quiosque.getDispoStatus());
            }
        }
    }

//======================================================================================================================
    private static void exibirOpcoesReservas() {

        System.out.println("=======- Gestao de Reservas -=======");
        System.out.println("1 - Buscar reserva por data");
        System.out.println("2 - Editar dados da reserva");
        System.out.println("3 - Nova reserva");
        System.out.println("4 - Excluir reserva");
        System.out.println("5 - Lista de Reservas ");
        System.out.println("0 - Voltar ao menu principal");
        System.out.println("Escolha uma opção:");

        int opcao = sc.nextInt();
        sc.nextLine();
        switch (opcao) {
            case 1:
                System.out.println("Digite a data da reserva (no formato YYYY-MM-DD):");
                String dataReservaStr = sc.nextLine();
                LocalDate dataReserva = LocalDate.parse(dataReservaStr);
                ExibirDados.mostrarReservasPorData(dataReserva);
                aguardarEnter();
                exibirOpcoesReservas();
                break;
            case 2:
                editarReserva();
                aguardarEnter();
                exibirOpcoesReservas();
                break;
            case 3:
                criarNovaReserva();
                aguardarEnter();
                exibirOpcoesReservas();
                break;
            case 4:
                System.out.println("Digite o ID da reserva a ser excluida:");
                long deleteId = sc.nextLong();

                ReservaService reservaService = new ReservaService();
                ReservasController reservasController = new ReservasController(reservaService);



                reservasController.excluirReserva(deleteId);
                System.out.println("reserva excluida.");
                aguardarEnter();
                exibirOpcoesReservas();
                break;
            case 5:
                listarTodasReservas();
                aguardarEnter();
                exibirOpcoesReservas();
                break;
            case 0:
                exibirMenu();
                break;
            default:
                System.out.println("Opção inválida.");
                exibirOpcoesReservas();
                break;
        }

    }
    private static void editarReserva() {
        System.out.println("Digite o ID da reserva a ser editada:");
        long id = sc.nextLong();
        sc.nextLine();


        ResevasRepository reservaRepository = new ResevasRepository();
        ReservaService reservaService = new ReservaService(reservaRepository);
        ReservasController reservaController = new ReservasController(reservaService);
        ReservasEntity reserva = reservaController.encontrarReservaPorId(id);

        if (reserva == null) {
            System.out.println("Reserva não encontrada.");
            return;
        }

        System.out.println("Digite a nova data de início (ou pressione Enter para manter a atual: " + reserva.getDataInicio() + "):");
        String novaDataInicioStr = sc.nextLine();
        if (!novaDataInicioStr.isEmpty()) {
            LocalDate novaDataInicio = LocalDate.parse(novaDataInicioStr);
            reserva.setDataInicio(novaDataInicio);
        }

        System.out.println("Digite a nova data de fim (ou pressione Enter para manter a atual: " + reserva.getDataFim() + "):");
        String novaDataFimStr = sc.nextLine();
        if (!novaDataFimStr.isEmpty()) {
            LocalDate novaDataFim = LocalDate.parse(novaDataFimStr);
            reserva.setDataFim(novaDataFim);
        }
        System.out.println("Digite o novo ID do Quiosque (ou pressione Enter para manter o atual: " + reserva.getQuiosque().getId() + "):");
        String novoIdQuiosqueStr = sc.nextLine();
        if (!novoIdQuiosqueStr.isEmpty()) {
            long novoIdQuiosque = Long.parseLong(novoIdQuiosqueStr);
            QuiosqueEntity novoQuiosque = new QuiosqueEntity();
            novoQuiosque.setId(novoIdQuiosque);
            reserva.setQuiosque(novoQuiosque);
        }

        System.out.println("Digite o novo ID do Cliente (ou pressione Enter para manter o atual: " + reserva.getCliente().getId() + "):");
        String novoIdClienteStr = sc.nextLine();
        if (!novoIdClienteStr.isEmpty()) {
            long novoIdCliente = Long.parseLong(novoIdClienteStr);
            ClientesEntity novoCliente = new ClientesEntity();
            novoCliente.setId(novoIdCliente);
            reserva.setCliente(novoCliente);
        }

        System.out.println("Digite o novo valor da diária (ou pressione Enter para manter o atual: " + reserva.getPrecoDiaria() + "):");
        String novoValorDiariaStr = sc.nextLine();
        if (!novoValorDiariaStr.isEmpty()) {
            BigDecimal novoValorDiaria = new BigDecimal(novoValorDiariaStr);
            reserva.setPrecoDiaria(novoValorDiaria);
        }


        reservaController.atualizarReserva(reserva);
        System.out.println("Reserva atualizada com sucesso.");
    }

    private static void criarNovaReserva() {
        ResevasRepository resevasRepository = new ResevasRepository();
        ReservaService reservaService = new ReservaService();
        ReservasController reservaController = new ReservasController(reservaService);
        ReservasEntity novaReserva = new ReservasEntity();

        System.out.println("Digite a data de início da reserva (no formato YYYY-MM-DD):");
        String dataInicioStr = sc.nextLine();
        LocalDate dataInicio = LocalDate.parse(dataInicioStr);

        System.out.println("Digite a data de fim da reserva (no formato YYYY-MM-DD):");
        String dataFimStr = sc.nextLine();
        LocalDate dataFim = LocalDate.parse(dataFimStr);


        System.out.println("Lista de Quiosques Ativos:");
        List<QuiosqueEntity> quiosquesAtivos = resevasRepository.getQuiosquesAtivos();
        for (QuiosqueEntity quiosque : quiosquesAtivos) {
            System.out.println("ID: " + quiosque.getId() + ", Número: " + quiosque.getNumero() + ", Localidade: " + quiosque.getLocalidade());
        }

        System.out.println("Selecione o quiosque desejado:");
        Long escolhaQui = sc.nextLong();
        sc.nextLine();

        QuiosqueController quiosqueController = new QuiosqueController();
        QuiosqueEntity quiosqueEscolha = quiosqueController.findQuiosqueById(escolhaQui);

        System.out.println("Lista de Clientes Ativos:");
        List<ClientesEntity> clientesAtivos = resevasRepository.getClientesAtivos();
        for (ClientesEntity cliente : clientesAtivos) {
            System.out.println("ID: " + cliente.getId() + ", Nome: " + cliente.getNome());
        }
        System.out.println("Digite o número do cliente desejado:");
        Long escolhaCli = sc.nextLong();
        sc.nextLine();

        ClientesEntity clienteEscolha = clientesController.findClienteById(escolhaCli);

        System.out.println("Entre com o valor da diária:");
        BigDecimal precoDiaria = sc.nextBigDecimal();
        sc.nextLine();

        long numeroDias = ChronoUnit.DAYS.between(dataInicio, dataFim) + 1;


        BigDecimal valorTotal = precoDiaria.multiply(BigDecimal.valueOf(numeroDias));

        novaReserva.setDataInicio(dataInicio);
        novaReserva.setDataFim(dataFim);
        novaReserva.setQuiosque(quiosqueEscolha);
        novaReserva.setCliente(clienteEscolha);
        novaReserva.setPrecoDiaria(precoDiaria);
        novaReserva.setValorTotal(valorTotal);

        ReservasEntity criadoReserva =  reservaController.criarReserva(novaReserva);

        if (criadoReserva != null) {
            System.out.println("Nova reserva criada com sucesso.");
            System.out.println("ID: " + criadoReserva.getId());
            System.out.println("Data de Início: " + criadoReserva.getDataInicio());
            System.out.println("Data de Fim: " + criadoReserva.getDataFim());
            System.out.println("Quiosque: " + criadoReserva.getQuiosque().getNumero() + ", Localidade: " + criadoReserva.getQuiosque().getLocalidade());
            System.out.println("Cliente: " + criadoReserva.getCliente().getNome());
            System.out.println("Valor Total: R$" + criadoReserva.getValorTotal());
        } else {
            System.out.println("Falha ao criar novo funcionário.");
        }
    }


    private static void listarTodasReservas() {
        ResevasRepository reservasRepository = new ResevasRepository();
        List<ReservasEntity> reservas = reservasRepository.findAll();

        if (reservas.isEmpty()) {
            System.out.println("Não há reservas cadastradas no sistema.");
        } else {
            System.out.println("Lista de reservas cadastradas:");
            for (ReservasEntity reserva : reservas) {
                System.out.println("ID: " + reserva.getId() + ", Data de Início: " + reserva.getDataInicio() + ", Data de Fim: " + reserva.getDataFim() + ", Valor Total: " + reserva.getValorTotal());
            }
        }
    }



    // ===========================================================================
    private static void aguardarEnter() {
        System.out.println("Pressione Enter para continuar...");
        sc.nextLine();
    }

}
