package model.repositories;

import model.entities.ComponetesEntity;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class ComponentesRepository implements BasicCrud{
    EntityManager em = Persistence.createEntityManagerFactory("bancoQuiosque").createEntityManager();

    @Override
    public Object create(Object object) {
        ComponetesEntity componetes1 = (ComponetesEntity) object;
        em.getTransaction().begin();
        em.persist(componetes1);
        em.getTransaction().commit();
        return findById(componetes1.getId());
    }

    @Override
    public Object update(Object object) {
        ComponetesEntity componetesUpDate = (ComponetesEntity) object;
        em.getTransaction().begin();
        em.merge(componetesUpDate);
        em.getTransaction().commit();
        return null;
    }

    @Override
    public void delete(Long id) {
        em.getTransaction().begin();
        var componentes = (ComponetesEntity) findById(id);
        em.remove(componentes);
        em.getTransaction().commit();

    }

    public List<ComponetesEntity> findAll(){
        System.out.println("teste");
        return new ArrayList<ComponetesEntity>();
    }

    @Override
    public Object findById(Object id) {
        try {
            ComponetesEntity componeteInBd = em.find(ComponetesEntity.class, id);
            return componeteInBd;
        } catch (Exception e) {

        }
        return null;
    }
}
