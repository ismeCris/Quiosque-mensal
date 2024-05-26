package view;

import controller.ClientesController;
import controller.FuncionarioController;
import controller.QuiosqueController;
import controller.ReservasController;
import model.entities.*;
import model.repositories.ClientesRepository;
import model.repositories.FuncionariosRepository;
import model.repositories.QuiosqueRepository;
import model.repositories.ResevasRepository;
import model.service.ClientesService;
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

    public static void main(String[] args) {
        exibirMenuPrincipal();
    }

    private static void exibirMenuPrincipal() {
        // Solicitar nome de usuário e senha
        System.out.print("Nome de usuário: ");
        String nome = sc.nextLine();

        System.out.print("Senha: ");
        String senha = sc.nextLine();

        // Autenticar
        FuncionariosEntity funcionarioAutenticado = funcionarioController.login(nome, senha);

        if (funcionarioAutenticado != null) {
            System.out.println("Bem-vindo, " + funcionarioAutenticado.getNome() + "!");

            exibirMenu();
        } else {
            System.out.println("Nome de usuário ou senha incorretos. Por favor, tente novamente.");
            exibirMenuPrincipal(); // Chama recursivamente para tentar novamente
        }
    }

    private static void exibirMenu() {
        System.out.println("1 - Gestão de funcionarios");
        System.out.println("2 - Gestão de Clientes");
        System.out.println("3 - Gestão de Quiosques");
        System.out.println("4 - Gestão de Contratos");
        System.out.println("5 - Gestão de Reservas");
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
            case 5:
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
        sc.nextLine(); // Limpa o buffer do scanner

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

    // ->editar dados do funcionario
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

        // Check if the password is already in use
        FuncionariosEntity funcionarioExistente = funcionariosRepository.findBySenha(senha);
        if (funcionarioExistente != null) {
            System.out.println("Já existe um funcionário com a mesma senha. Por favor, escolha outra senha.");
            return; // Return to the main menu
        }

        novoFuncionario.setSenha(senha);

        System.out.println("Digite o email do novo funcionário:");
        String email = sc.nextLine();
        if (email.length() > 255) {
            System.out.println("O email excede o limite de 255 caracteres. Por favor, insira um email mais curto.");
            return; // Return to the main menu
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

        ClientesEntity clientes = ClientesController.findClienteById(id);
        if (clientes == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        System.out.println("Digite o novo nome (ou pressione Enter para manter o atual: " + clientes.getNome() + "):");
        String novoNome = sc.nextLine();
        if (!novoNome.isEmpty()) {
            clientes.setNome(novoNome);
        }

        System.out.println("Digite o novo email (ou pressione Enter para manter o atual: " + clientes.getEmail() + "):");
        String novoEmail = sc.nextLine();
        if (!novoEmail.isEmpty()) {
            clientes.setEmail(novoEmail);
        }

        System.out.println("Digite o novo telefone (ou pressione Enter para manter o atual: " + clientes.getTelefone() + "):");
        String novoTelefone = sc.nextLine();
        if (!novoTelefone.isEmpty()) {
            clientes.setTelefone(novoTelefone);
        }
        System.out.println("Digite o novo telefone (ou pressione Enter para manter o atual: " + clientes.getIdade() + "):");
        String novaIdade = sc.nextLine();
        if (!novaIdade.isEmpty()) {
            clientes.setTelefone(novaIdade);
        }

        System.out.println("Digite o novo CPF (ou pressione Enter para manter o atual: " + clientes.getCpf() + "):");
        String novoCpf = sc.nextLine();
        if (!novoCpf.isEmpty()) {
            clientes.setCpf(novoCpf);
        }

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

        // Verifica se o CPF já existe no banco de dados
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
            // Cria o novo cliente
            ClientesEntity criadoCliente = clientesController.createCliente(novocliente);
            if (criadoCliente != null) {
                // Exibe as informações do cliente criado com sucesso
                System.out.println("Novo cliente criado com sucesso.");
                System.out.println("ID: " + criadoCliente.getId());
                System.out.println("Nome: " + criadoCliente.getNome());
                System.out.println("CPF: " + criadoCliente.getCpf());
                // Exibe outras informações do cliente (não incluídas aqui por simplicidade)
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

                QuiosqueRepository quiosqueRepository = new QuiosqueRepository();
                QuiosqueController quiosqueController = new QuiosqueController(quiosqueRepository);

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
                exibirOpcoesQuiosque();// Volta para o submenu
                break;
        }

    }

    // ->editar dados do Quiosque
    private static void editarQuiosque() {
        System.out.println("Digite o ID do Quiosque a ser editado:");
        long id = sc.nextLong();
        sc.nextLine(); // Limpa o buffer do scanner
        QuiosqueRepository quiosqueRepository = new QuiosqueRepository(); // Criar uma instância de QuiosqueRepository
        QuiosqueController quiosqueController = new QuiosqueController(quiosqueRepository);

        QuiosqueEntity quiosque = quiosqueController.findQuiosqueById(id);
        
        if (quiosque == null) {
            System.out.println("Quiosque não encontrado.");
            return;
        }

        System.out.println("Digite o novo numero (ou pressione Enter para manter o atual: " + quiosque.getNumero() + "):");
        String novoNumInput = sc.nextLine(); // Ler entrada como String

        if (!novoNumInput.isEmpty()) {
            int novoNum = Integer.parseInt(novoNumInput); // Converter String para int
            quiosque.setNumero(novoNum);
        }

        System.out.println("Digite a nova localidade (ou pressione Enter para manter a atual:"+ quiosque.getLocalidade() +"):");
        String novalocalidade = sc.nextLine();
        if (!novalocalidade.isEmpty()) {
            quiosque.setLocalidade(novalocalidade);
        }


        System.out.println("Digite o novo telefone (ou pressione Enter para manter o atual: " + quiosque.getCapacidade() + "):");
        String novaCapaciInput = sc.nextLine(); // Ler entrada como String

        if (!novaCapaciInput.isEmpty()) {
            int novaCapaci = Integer.parseInt(novaCapaciInput); // Converter String para int
            quiosque.setCapacidade(novaCapaci);
        }


        System.out.println("Digite o novo Status (ou pressione Enter para manter o atual: " + quiosque.getDispoStatus() + "):");
        String novoStatusInput = sc.nextLine(); // Ler entrada como String

        if (!novoStatusInput.isEmpty()) {
            boolean novoStatus = Boolean.parseBoolean(novoStatusInput); // Converter String para boolean
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
        sc.nextLine(); // Consumir a quebra de linha após a leitura do número

        // Verificar se já existe um quiosque com o mesmo número
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


        QuiosqueController quiosqueController = new QuiosqueController(quiosqueRepository);

        // Create the new quiosque using the controller
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
                //editarQuiosque();
                aguardarEnter();
                exibirOpcoesReservas();
                break;
            case 3:
                criarnovaReserva();
                aguardarEnter();
                exibirOpcoesReservas();
                break;
            case 4:
                System.out.println("Digite o ID do quiosque a ser excluído:");
                long deleteId = sc.nextLong();

                QuiosqueRepository quiosqueRepository = new QuiosqueRepository();
                QuiosqueController quiosqueController = new QuiosqueController(quiosqueRepository);

                quiosqueController.deleteQuiosque(deleteId);
                System.out.println("quiosque excluído.");
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

    private static void  criarnovaReserva() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite a data de início da reserva (no formato YYYY-MM-DD):");
        String dataInicioStr = sc.nextLine();
        LocalDate dataInicio = LocalDate.parse(dataInicioStr);

        System.out.println("Digite a data de fim da reserva (no formato YYYY-MM-DD):");
        String dataFimStr = sc.nextLine();
        LocalDate dataFim = LocalDate.parse(dataFimStr);

        QuiosqueRepository quiosqueRepository = new QuiosqueRepository();
        List<QuiosqueEntity> quiosquesDisponiveis = quiosqueRepository.buscarQuiosquesDisponiveis();

        System.out.println("Quiosques disponíveis:");
        for (int i = 0; i < quiosquesDisponiveis.size(); i++) {
            QuiosqueEntity quiosque = quiosquesDisponiveis.get(i);
            System.out.println((i + 1) + ". ID: " + quiosque.getId() + ", Número: " + quiosque.getNumero() + ", Localidade: " + quiosque.getLocalidade());
        }

        System.out.println("Selecione o quiosque desejado:");
        int escolha = sc.nextInt();
        sc.nextLine();

        if (escolha > 0 && escolha <= quiosquesDisponiveis.size()) {
            QuiosqueEntity quiosqueSelecionado = quiosquesDisponiveis.get(escolha - 1);

            ClientesRepository clienteRepository = new ClientesRepository();
            List<ClientesEntity> clientesCadastrados = clienteRepository.findAll();

            System.out.println("Lista de Clientes Cadastrados:");
            for (int i = 0; i < clientesCadastrados.size(); i++) {
                ClientesEntity cliente = clientesCadastrados.get(i);
                System.out.println((i + 1) + ". ID: " + cliente.getId() + ", Nome: " + cliente.getNome());
            }

            System.out.println("Digite o número do cliente desejado:");
            int escolhaCliente = sc.nextInt();
            sc.nextLine();

            if (escolhaCliente > 0 && escolhaCliente <= clientesCadastrados.size()) {
                ClientesEntity clienteSelecionado = clientesCadastrados.get(escolhaCliente - 1);

                BigDecimal valorDiaria = quiosqueSelecionado.getValorDiaria();
                if (valorDiaria == null) {
                    System.out.println("Erro: O quiosque selecionado não possui um valor de diária definido.");
                    return;
                }

                long numeroDias = ChronoUnit.DAYS.between(dataInicio, dataFim) + 1;
                double valorDiariaDouble = valorDiaria.doubleValue();
                double valorTotal = numeroDias * valorDiariaDouble;

                ReservasEntity novaReserva = new ReservasEntity();
                novaReserva.setDataInicio(dataInicio);
                novaReserva.setDataFim(dataFim);
                novaReserva.setQuiosque(quiosqueSelecionado);
                novaReserva.setCliente(clienteSelecionado);
                novaReserva.setPrecoDiaria(valorDiaria);
                novaReserva.setValorTotal(BigDecimal.valueOf(valorTotal));

                // Criação do contrato
                ContratosEntity contrato = new ContratosEntity(novaReserva);

                // Salvando reserva e contrato no banco de dados
                ReservasController reservasController = new ReservasController(new ReservaService());
                ReservasEntity criadaReserva = reservasController.criarReserva(novaReserva);

                if (criadaReserva != null) {
                    System.out.println("Nova reserva criada com sucesso.");
                    System.out.println("ID: " + criadaReserva.getId());
                    System.out.println("Data de Início: " + criadaReserva.getDataInicio());
                    System.out.println("Data de Fim: " + criadaReserva.getDataFim());
                    System.out.println("Quiosque: " + criadaReserva.getQuiosque().getNumero() + ", Localidade: " + criadaReserva.getQuiosque().getLocalidade());
                    System.out.println("Cliente: " + criadaReserva.getCliente().getNome());
                    System.out.println("Valor Total: R$" + criadaReserva.getValorTotal());

                    contrato.imprimirContrato();

                    criadaReserva.getContrato().imprimirContrato();
                } else {
                    System.out.println("Falha ao criar nova reserva.");
                }
            } else {
                System.out.println("Escolha inválida. Tente novamente.");
            }
        } else {
            System.out.println("Escolha inválida. Tente novamente.");
        }
    }




    // ===========================================================================
    private static void aguardarEnter() {
        System.out.println("Pressione Enter para continuar...");
        sc.nextLine();
    }
}
