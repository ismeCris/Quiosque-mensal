package controller;

import model.entities.ClientesEntity;
import model.entities.QuiosqueEntity;
import model.service.QuiosqueService;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class QuiosqueController {
    private QuiosqueService quiosqueService;

    private EntityManager em;

    public QuiosqueController(EntityManager em) {
        this.em = em;
        this.quiosqueService = new QuiosqueService();
    }

    public QuiosqueEntity findQuiosqueById(Long id) {
        return quiosqueService.findQuiosqueById(id);
    }

    public void updateQuiosque(QuiosqueEntity quiosque) {
        quiosqueService.updateQuiosque(quiosque);
    }

    public void deleteQuiosque(Long id) {
        quiosqueService.deleteQuiosque(id);
    }

    public QuiosqueEntity createQuiosque(QuiosqueEntity quiosque) {
        return quiosqueService.createQuiosque(quiosque);
    }

    public List<QuiosqueEntity> findAll() {
        return quiosqueService.findAll();
    }
    


    public QuiosqueEntity encontrarQuiosquePorNumero(Integer numero) {
        try {
            TypedQuery<QuiosqueEntity> query = em.createQuery(
                    "SELECT q FROM QuiosqueEntity q WHERE q.numero = :numero", QuiosqueEntity.class);
            query.setParameter("numero", numero);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null; // Retorna null se nenhum quiosque com o número especificado for encontrado
        }
    }


    public List<QuiosqueEntity> findQuiosquesByDisponibilidadeStatus(boolean disponibilidadeStatus) {
        TypedQuery<QuiosqueEntity> query = em.createQuery(
                "SELECT q FROM QuiosqueEntity q WHERE q.disponibilidadeStatus = :disponibilidadeStatus", QuiosqueEntity.class);
        query.setParameter("disponibilidadeStatus", disponibilidadeStatus);
        return query.getResultList();
    }


   
    
}
