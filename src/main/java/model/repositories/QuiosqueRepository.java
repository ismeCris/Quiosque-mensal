package model.repositories;

import model.entities.ClientesEntity;
import model.entities.QuiosqueEntity;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class QuiosqueRepository implements  BasicCrud{

    EntityManager em = Persistence.createEntityManagerFactory("bancoQuiosque").createEntityManager();

    @Override
    public Object create(Object object) {
        QuiosqueEntity quiosque1 = (QuiosqueEntity) object;
        em.getTransaction().begin();
        em.persist(quiosque1);
        em.getTransaction().commit();
        return findById(quiosque1.getId());
    }

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
        var quiosque = (ClientesEntity) findById(id);
        em.remove(quiosque);
        em.getTransaction().commit();

    }

    public List<QuiosqueEntity> findAll(){
        System.out.println("teste");
        return new ArrayList<QuiosqueEntity>();
        //return em.createQuery("aa",FuncionariosEntity.class).getResultList();
    }

    @Override
    public Object findById(Object id) {
        try {
            QuiosqueEntity quiosqueInBD = em.find(QuiosqueEntity.class, id);
            return quiosqueInBD;
        } catch (Exception e) {

        }
        return null;
    }
}
