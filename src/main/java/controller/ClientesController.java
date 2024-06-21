package controller;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

import model.entities.ClientesEntity;
import model.entities.QuiosqueEntity;
import model.repositories.ClientesRepository;
import model.service.ClientesService;

import java.util.List;

public class ClientesController {

    private ClientesService clientesService;
    private ClientesRepository clientesRepository;
    private EntityManager em;

    public ClientesController(EntityManager em) {
        this.em = em;
        this.clientesService = new ClientesService();
        this.clientesRepository = new ClientesRepository(); // Inicialize conforme sua configuração
    }

    public ClientesEntity createCliente(ClientesEntity cliente) {
        try {
            return clientesService.criarCliente(cliente);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao criar novo Cliente", "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return null;
        }
    }

    public void updateCliente(ClientesEntity cliente) {
        try {
            clientesService.atualizarCliente(cliente);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar Cliente", "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public void deleteCliente(Long id) {
        try {
            clientesService.deletarCliente(id);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir Cliente", "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public List<ClientesEntity> getAllClientes() {
        return clientesService.buscarTodosClientes();
    }

    public ClientesEntity getClienteById(Long id) {
        return clientesService.buscarClientePorId(id);
    }

    public boolean verificarClientePorCPF(String cpf) {
        ClientesEntity cliente = clientesRepository.getClienteByCPF(cpf);
        return cliente != null; // Retorna true se encontrou um cliente com o CPF
    }

    public List<ClientesEntity> findClientesByUserStatus(boolean userStatus) {
        TypedQuery<ClientesEntity> query = em.createQuery(
                "SELECT c FROM ClientesEntity c WHERE c.userStatus = :userStatus", ClientesEntity.class);
        query.setParameter("userStatus", userStatus);
        return query.getResultList();
    }

    public ClientesEntity encontrarClientePorNome(String nomeCliente) {
        TypedQuery<ClientesEntity> query = em.createQuery(
                "SELECT c FROM ClientesEntity c WHERE c.nome = :nome", ClientesEntity.class);
        query.setParameter("nome", nomeCliente);
        return query.getSingleResult();
    }


    
}
