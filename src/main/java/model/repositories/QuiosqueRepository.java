package model.repositories;

import model.entities.FuncionariosEntity;
import model.entities.QuiosqueEntity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class QuiosqueRepository implements  BasicCrud{

    EntityManager em = Persistence.createEntityManagerFactory("bancoQuiosque").createEntityManager();

    @Override
    public Object create(Object object) {
        QuiosqueEntity quiosque = (QuiosqueEntity) object;

        Query query = em.createQuery("SELECT q FROM QuiosqueEntity q WHERE q.numero = :numero");
        query.setParameter("numero", quiosque.getNumero());
        List<QuiosqueEntity> quiosquesWithSameNumber = query.getResultList();

        if (!quiosquesWithSameNumber.isEmpty()) {
            System.out.println("Já existe um quiosque com o mesmo número. Por favor, escolha outro.");
            return null;
        }

            em.getTransaction().begin();
            em.persist(quiosque);
            em.getTransaction().commit();

            return quiosque;

    }

    @Override
    public Object update(Object object) {
        QuiosqueEntity quiosqueUpdate = (QuiosqueEntity) object;
        em.getTransaction().begin();
        em.merge(quiosqueUpdate);
        em.getTransaction().commit();
        return quiosqueUpdate;
    }

    @Override
    public void delete(Long id) {
    	em.getTransaction().begin();
        var quiosque = (QuiosqueEntity) findById(id);
        em.remove(quiosque);
        em.getTransaction().commit();
    }
  


    public List<QuiosqueEntity> findAll(){
        return em.createQuery("SELECT q FROM QuiosqueEntity q",QuiosqueEntity.class).getResultList();
    }

    @Override
    public QuiosqueEntity findById(Object id) {
        try {
            QuiosqueEntity quiosqueInBD = em.find(QuiosqueEntity.class, id);
            return quiosqueInBD;
        } catch (Exception e) {

        }
        return null;
    }
    
    public List<QuiosqueEntity> buscarQuiosquesDisponiveis() {
        String jpql = "SELECT q FROM QuiosqueEntity q WHERE q.dispoStatus = true";
        TypedQuery<QuiosqueEntity> query = em.createQuery(jpql, QuiosqueEntity.class);
        return query.getResultList();
    }
}
