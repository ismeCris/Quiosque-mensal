package model.repositories;

import model.entities.ContratosEntity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class ContratosRepository implements BasicCrud {

    EntityManager em = Persistence.createEntityManagerFactory("bancoQuiosque").createEntityManager();


    @Override
    public ContratosEntity create(Object object) {
        ContratosEntity contratos1 = (ContratosEntity) object;
        // Verifica se o cliente associado ao contrato não é nulo
        if (contratos1.getCliente() == null) {
            throw new IllegalArgumentException("Cliente associado ao contrato não pode ser nulo.");
        }
        em.getTransaction().begin();
        em.persist(contratos1);
        em.getTransaction().commit();
        return contratos1;
    }


    @Override
    public Object update(Object object) {
        ContratosEntity contratoUpDate = (ContratosEntity) object;
        em.getTransaction().begin();
        em.merge(contratoUpDate);
        em.getTransaction().commit();
        return null;
    }

    @Override
    public void delete(Long id) {
        em.getTransaction().begin();
        var contrato = (ContratosEntity) findById(id);
        em.remove(contrato);
        em.getTransaction().commit();
    }
    public List<ContratosEntity> findAll(){
        return em.createQuery("SELECT c FROM ContratosEntity c",ContratosEntity.class).getResultList();
    }

    @Override
    public Object findById(Object id) {
        try {
            ContratosEntity contratoInBd = em.find(ContratosEntity.class, id);
            return contratoInBd;
        } catch (Exception e) {

        }
        return null;
    }

    /*public void create(ContratosEntity contrato) {
        // Verifica se o cliente associado ao contrato não é nulo
        if (contrato.getCliente() == null) {
            throw new IllegalArgumentException("Cliente associado ao contrato não pode ser nulo.");
        }

        // Persiste o contrato no banco de dados
        EntityManager em = Persistence.createEntityManagerFactory("bancoQuiosque").createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(contrato);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            ex.printStackTrace(); // Trate a exceção de acordo com sua lógica de aplicação
        }
    }*/
}
