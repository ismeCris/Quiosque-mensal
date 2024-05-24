package model.repositories;


import model.entities.ClientesEntity;
import model.entities.FuncionariosEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;


public class ClientesRepository implements  BasicCrud{

    EntityManager em = Persistence.createEntityManagerFactory("bancoQuiosque").createEntityManager();

    // --------------------------------->
    //  criar um cliente no seu sistema
    @Override
    public Object create(Object object) {
        ClientesEntity clientes1 = (ClientesEntity) object;

        Query query = em.createQuery("SELECT c FROM ClientesEntity c WHERE c.cpf = :cpf");
        query.setParameter("cpf", clientes1.getCpf());
        List<ClientesEntity> clineteMsmCpf = query.getResultList();

        if (!clineteMsmCpf.isEmpty()) {
            System.out.println("Já existe um funcionário com o mesmo CPF.");

            return null;
        }

        em.getTransaction().begin();
        em.persist(clientes1);
        em.getTransaction().commit();
        return findById(clientes1.getId());
    }


    // código para ler um cliente do seu sistema
    @Override
    public Object update(Object object) {
        ClientesEntity clientesUpdade = (ClientesEntity) object;
        em.getTransaction().begin();
        em.merge(clientesUpdade);
        em.getTransaction().commit();
        return null;
    }

    @Override
    public void delete(Long id) {
        em.getTransaction().begin();
        var cliente = (ClientesEntity) findById(id);
        em.remove(cliente);
        em.getTransaction().commit();
    }

    public List<ClientesEntity> findAll() {
        return em.createQuery("SELECT c FROM ClientesEntity c", ClientesEntity.class).getResultList();
    }

    @Override
    public Object findById(Object id) {
        try {
            ClientesEntity clientesInBD = em.find(ClientesEntity.class, id);
            return clientesInBD;
        } catch (Exception e) {

        }
        return null;
    }



}
