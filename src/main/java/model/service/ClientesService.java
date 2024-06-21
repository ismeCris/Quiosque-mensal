package model.service;

import model.entities.ClientesEntity;
import model.repositories.ClientesRepository;

import java.util.List;

public class ClientesService {

    private ClientesRepository clientesRepository = new ClientesRepository();

    public ClientesEntity criarCliente(ClientesEntity cliente) throws Exception {
        // Validações de negócio podem ser feitas aqui antes de chamar o repositório
        return (ClientesEntity) clientesRepository.create(cliente);
    }

    public void atualizarCliente(ClientesEntity cliente) throws Exception {
        // Validações de negócio podem ser feitas aqui antes de chamar o repositório
        clientesRepository.update(cliente);
    }

    public void deletarCliente(Long id) throws Exception {
        // Validações de negócio podem ser feitas aqui antes de chamar o repositório
        clientesRepository.delete(id);
    }

    public List<ClientesEntity> buscarTodosClientes() {
        return clientesRepository.findAll();
    }

    public ClientesEntity buscarClientePorId(Long id) {
        return (ClientesEntity) clientesRepository.findById(id);
    }

    public ClientesEntity buscarClientePorCPF(String cpf) {
        return clientesRepository.getClienteByCPF(cpf);
    }
}
