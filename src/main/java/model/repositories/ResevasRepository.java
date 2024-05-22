package model.repositories;

import model.entities.ClientesEntity;
import model.entities.FuncionariosEntity;
import model.entities.ReservasEntity;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class ResevasRepository implements BasicCrud {
    EntityManager em = Persistence.createEntityManagerFactory("bancoQuiosque").createEntityManager();

    // ---------------------------------
    @Override
    public Object create(Object object) {
        ReservasEntity reservas1 = (ReservasEntity) object;
        em.getTransaction().begin();
        em.persist(reservas1);
        em.getTransaction().commit();
        return findById(reservas1.getId());
    }

    @Override
    public Object update(Object object) {
        ReservasEntity reservasUpDate = (ReservasEntity) object;
        em.getTransaction().begin();
        em.merge(reservasUpDate);
        em.getTransaction().commit();
        return null;
    }

    @Override
    public void delete(Long id) {
        em.getTransaction().begin();
        var reserva = (ReservasEntity) findById(id);
        em.remove(reserva);
        em.getTransaction().commit();
    }
    public List<ReservasEntity> findAll(){
        System.out.println("teste");
        return new ArrayList<ReservasEntity>();
        //return em.createQuery("aa",FuncionariosEntity.class).getResultList();
    }


    @Override
    public Object findById(Object id) {
        try {
            ReservasEntity reservasInBd = em.find(ReservasEntity.class, id);
            return reservasInBd;
        } catch (Exception e) {

        }
        return null;
    }
}




