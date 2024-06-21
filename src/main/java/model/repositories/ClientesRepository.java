package model.repositories;


import model.entities.ClientesEntity;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;


public class ClientesRepository implements  BasicCrud{

    EntityManager em = Persistence.createEntityManagerFactory("bancoQuiosque").createEntityManager();
  

    @Override
    public Object create(Object object) {
        ClientesEntity cliente = (ClientesEntity) object;
        em.getTransaction().begin();
        em.persist(cliente);
        em.getTransaction().commit();
        return cliente;
    }

    @Override
    public Object update(Object object) {
        ClientesEntity cliente = (ClientesEntity) object;
        em.getTransaction().begin();
        em.merge(cliente);
        em.getTransaction().commit();
        return cliente;
    }

    @Override
    public void delete(Long id) {
        em.getTransaction().begin();
        ClientesEntity cliente = em.find(ClientesEntity.class, id);
        if (cliente != null) {
            em.remove(cliente);
        }
        em.getTransaction().commit();
    }

    @Override
    public Object findById(Object id) {
        try {
            Long clientId = (Long) id;
            ClientesEntity cliente = em.find(ClientesEntity.class, clientId);
            return cliente;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ClientesEntity> findAll() {
        return em.createQuery("SELECT c FROM ClientesEntity c", ClientesEntity.class).getResultList();
    }


    public ClientesEntity getClienteByCPF(String cpf) {
        try {
            Query query = em.createQuery("SELECT c FROM ClientesEntity c WHERE c.cpf = :cpf");
            query.setParameter("cpf", cpf);
            return (ClientesEntity) query.getSingleResult();
        } catch (NoResultException e) {
            return null; // Retorna null se não encontrar nenhum cliente com o CPF
        }
    }


}
