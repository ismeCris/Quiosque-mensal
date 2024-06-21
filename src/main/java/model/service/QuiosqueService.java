package model.service;

import model.entities.QuiosqueEntity;
import model.repositories.QuiosqueRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


public class QuiosqueService {

    @PersistenceContext
    private EntityManager entityManager;

    private QuiosqueRepository quiosqueRepository;

    public QuiosqueService() {
        this.quiosqueRepository = new QuiosqueRepository();
    }


    public QuiosqueEntity findQuiosqueById(Long id) {
        return (QuiosqueEntity) quiosqueRepository.findById(id);
    }

    public List<QuiosqueEntity> findAll() {
        return quiosqueRepository.findAll();
    }

    public List<QuiosqueEntity> buscarQuiosquesDisponiveis() {
        return quiosqueRepository.buscarQuiosquesDisponiveis();
    }
    
    public void updateQuiosque(QuiosqueEntity quiosque) {
        quiosqueRepository.update(quiosque);
    }

    public void deleteQuiosque(Long id) {
        quiosqueRepository.delete(id);
    }

  
    public QuiosqueEntity createQuiosque(QuiosqueEntity quiosque) {
        return (QuiosqueEntity) quiosqueRepository.create(quiosque);
    }
    public List<QuiosqueEntity> findQuiosquesAtivos() {
        String hql = "SELECT q FROM QuiosqueEntity q WHERE q.dispoStatus = true";
        return entityManager.createQuery(hql, QuiosqueEntity.class).getResultList();
    }
}
