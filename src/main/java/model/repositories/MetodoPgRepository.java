package model.repositories;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class MetodoPgRepository {
    EntityManager em = Persistence.createEntityManagerFactory("bancoQuiosque").createEntityManager();

    public void salvarPag(Object objeto) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(objeto);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    public void atualizarPag(Object objeto) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(objeto);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void excluirPag(Object objeto) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.remove(objeto);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }



}
