package model.service;

import model.entities.QuiosqueEntity;
import model.repositories.QuiosqueRepository;

import java.util.List;


public class QuiosqueService {

    private QuiosqueRepository quiosqueRepository = new QuiosqueRepository();

    public QuiosqueService(QuiosqueRepository quiosqueRepository) {
    }

    public QuiosqueEntity findQuiosqueById(Long id) {
        return (QuiosqueEntity) quiosqueRepository.findById(id);
    }

    public List<QuiosqueEntity> findAll() {
        return quiosqueRepository.findAll();
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
}
