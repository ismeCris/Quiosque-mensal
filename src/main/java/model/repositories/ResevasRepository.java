package model.repositories;


import model.entities.ClientesEntity;
import model.entities.QuiosqueEntity;
import model.entities.ReservasEntity;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ResevasRepository implements BasicCrud {
    EntityManager em = Persistence.createEntityManagerFactory("bancoQuiosque").createEntityManager();


    @Override
    public ReservasEntity create(Object object) {
        ReservasEntity reservas1 = (ReservasEntity) object;

        Query query = em.createQuery("SELECT r FROM ReservasEntity r WHERE r.dataInicio = :dataInicio AND r.dataFim = :dataFim");
        query.setParameter("dataInicio", reservas1.getDataInicio());
        query.setParameter("dataFim", reservas1.getDataFim());
        List<ReservasEntity> reservasExistente = query.getResultList();

        em.getTransaction().begin();
        em.persist(reservas1);
        em.getTransaction().commit();
        return reservas1; // Retorna
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
            String queryString = "SELECT r FROM ReservasEntity r WHERE :date BETWEEN r.dataInicio AND r.dataFim";
            System.out.println("Query String: " + queryString); // Imprime a consulta SQL

            List<ReservasEntity> result = em.createQuery(queryString, ReservasEntity.class)
                    .setParameter("date", date)
                    .getResultList();

            System.out.println("Parâmetros da consulta:");
            System.out.println("Date: " + date);

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<ClientesEntity> getClientesAtivos() {
        try {
            return em.createQuery("SELECT c FROM ClientesEntity c WHERE c.UserStatus = true", ClientesEntity.class)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    public List<QuiosqueEntity> getQuiosquesAtivos() {
        try {
            return em.createQuery("SELECT q FROM QuiosqueEntity q WHERE q.dispoStatus = true", QuiosqueEntity.class)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }

    }


}



