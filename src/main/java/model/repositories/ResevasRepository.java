package model.repositories;



import model.entities.QuiosqueEntity;
import model.entities.ReservasEntity;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

public class ResevasRepository {
    private EntityManager em;

    public ResevasRepository(EntityManager em) {
        this.em = Persistence.createEntityManagerFactory("bancoQuiosque").createEntityManager();
    }

    public ReservasEntity create(ReservasEntity reserva) {
        em.getTransaction().begin();
        em.persist(reserva);
        em.getTransaction().commit();
        return reserva;
    }

    public ReservasEntity update(ReservasEntity reserva) {
        em.getTransaction().begin();
        ReservasEntity updatedReserva = em.merge(reserva);
        em.getTransaction().commit();
        return updatedReserva;
    }

    public void delete(Long id) {
        em.getTransaction().begin();
        ReservasEntity reserva = em.find(ReservasEntity.class, id);
        if (reserva != null) {
            em.remove(reserva);
        }
        em.getTransaction().commit();
    }

    public List<ReservasEntity> findAll() {
        TypedQuery<ReservasEntity> query = em.createQuery("SELECT r FROM ReservasEntity r", ReservasEntity.class);
        return query.getResultList();
    }

    public ReservasEntity findById(Long id) {
        return em.find(ReservasEntity.class, id);
    }
   
    public List<ReservasEntity> findByDateRange(LocalDate inicio, LocalDate fim) {
        TypedQuery<ReservasEntity> query = em.createQuery(
            "SELECT r FROM ReservasEntity r WHERE r.dataInicio >= :inicio AND r.dataFim <= :fim", 
            ReservasEntity.class
        );
        query.setParameter("inicio", inicio);
        query.setParameter("fim", fim);
        return query.getResultList();
    }
    
    public List<ReservasEntity> obterReservasPorQuiosque(QuiosqueEntity quiosque) {
        TypedQuery<ReservasEntity> query = em.createQuery("SELECT r FROM ReservasEntity r WHERE r.quiosque = :quiosque", ReservasEntity.class);
        query.setParameter("quiosque", quiosque);
        return query.getResultList();
    }
    
    public List<ReservasEntity> obterReservasPorQuiosque(QuiosqueEntity quiosque, LocalDate inicio, LocalDate fim, Long reservaIdAtual) {
        TypedQuery<ReservasEntity> query = em.createQuery(
            "SELECT r FROM ReservasEntity r WHERE r.quiosque = :quiosque " +
            "AND NOT (:inicio > r.dataFim OR :fim < r.dataInicio) " +
            "AND (r.id != :reservaIdAtual OR :reservaIdAtual IS NULL)", 
            ReservasEntity.class
        );
        query.setParameter("quiosque", quiosque);
        query.setParameter("inicio", inicio);
        query.setParameter("fim", fim);
        query.setParameter("reservaIdAtual", reservaIdAtual);
        return query.getResultList();
    }
    public List<ReservasEntity> obterReservasPorPeriodo(LocalDate inicio, LocalDate fim) {
        TypedQuery<ReservasEntity> query = em.createQuery("SELECT r FROM ReservasEntity r WHERE r.dataInicio >= :inicio AND r.dataFim <= :fim", ReservasEntity.class);
        query.setParameter("inicio", inicio);
        query.setParameter("fim", fim);
        return query.getResultList();
    }



    
}
