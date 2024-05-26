package controller;

import model.entities.ClientesEntity;
import model.service.ClientesService;


public class ClientesController {
    private static ClientesService clientesService = new ClientesService();

    public ClientesController() {
        this.clientesService = new ClientesService(); // ou injete o serviço conforme necessário
    }

    public static ClientesEntity findClienteById(Long id) {
        return clientesService.findClienteById(id); // Corrigido para findClienteById
    }

    public ClientesEntity createCliente(ClientesEntity cliente) {
        return clientesService.createCliente(cliente);
    }

    public void updateCliente(ClientesEntity cliente) {
        clientesService.updateCliente(cliente); // Corrigido para updateCliente
    }

    public void deleteClienteById(Long id) { // Corrigido para deleteClienteById
        clientesService.deleteCliente(id);
    }

}
