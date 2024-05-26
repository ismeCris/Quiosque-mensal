package model.repositories;

import model.entities.ClientesEntity;
import model.entities.FuncionariosEntity;
import model.entities.ReservasEntity;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ResevasRepository implements BasicCrud {
    EntityManager em = Persistence.createEntityManagerFactory("bancoQuiosque").createEntityManager();


    // ---------------------------------
    @Override
    public ReservasEntity create(Object object) {
        ReservasEntity reservas1 = (ReservasEntity) object;
        em.getTransaction().begin();
        em.persist(reservas1);
        em.getTransaction().commit();
        return reservas1; // Retorna a própria entidade inserida
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

        return em.createQuery("SELECT r FROM ReservasEntity r",ReservasEntity.class).getResultList();
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


    public List<ReservasEntity> findByDate(LocalDate date) {
        try {
            return em.createQuery("SELECT r FROM ReservasEntity r WHERE :date BETWEEN r.dataInicio AND r.dataFim", ReservasEntity.class)
                    .setParameter("date", date)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }




}




