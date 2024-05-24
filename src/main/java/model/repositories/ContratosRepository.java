package model.repositories;

import model.entities.ClientesEntity;
import model.entities.ContratosEntity;
import model.entities.FuncionariosEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class ContratosRepository implements BasicCrud {

    EntityManager em = Persistence.createEntityManagerFactory("bancoQuiosque").createEntityManager();


    @Override
    public ContratosEntity create(Object object) {
        ContratosEntity contratos1 = (ContratosEntity) object;
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
}
