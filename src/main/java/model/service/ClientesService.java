package model.service;

import java.time.LocalDate;
import java.util.List;

import model.entities.ClientesEntity;
import model.repositories.ClientesRepository;

public class ClientesService {

    private ClientesRepository clientesRepository;

    public ClientesService() {
        this.clientesRepository = new ClientesRepository();
    }

    public ClientesEntity findClienteById(Long id) {
        return (ClientesEntity) clientesRepository.findById(id);
    }

    public ClientesEntity createCliente(ClientesEntity cliente) {
        return (ClientesEntity) clientesRepository.create(cliente);
    }

    public void updateCliente(ClientesEntity cliente) {
        clientesRepository.update(cliente);
    }

    public void deleteCliente(Long id) {
        clientesRepository.delete(id);
    }

    public List<ClientesEntity> findAll() {
        return clientesRepository.findAll();
    }

    public boolean VerficaIdade(LocalDate dataNasc) {
        LocalDate dataAtual = LocalDate.now();
        LocalDate dezoitoAnosAtras = dataAtual.minusYears(18);
        return dataNasc.isBefore(dezoitoAnosAtras);
    }

    public boolean verificaStatusCliente(boolean isClienteAtivo) {
        return isClienteAtivo;
    }
}
