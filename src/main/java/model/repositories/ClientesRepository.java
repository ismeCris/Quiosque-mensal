package model.repositories;


import model.entities.ClientesEntity;
import model.entities.FuncionariosEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;


public class ClientesRepository implements  BasicCrud{

    EntityManager em = Persistence.createEntityManagerFactory("bancoQuiosque").createEntityManager();

    // --------------------------------->
    //  criar um cliente no seu sistema
    @Override
    public Object create(Object object) {
        ClientesEntity clientes1 = (ClientesEntity) object;
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

    //  código para atualizar um cliente no seu sistema
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


    // código para excluir um cliente do seu sistema
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
