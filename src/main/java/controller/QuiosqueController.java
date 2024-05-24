package controller;

import model.entities.QuiosqueEntity;
import model.repositories.QuiosqueRepository;
import model.service.QuiosqueService;

import java.util.List;


public class QuiosqueController {
    private QuiosqueService quiosqueService;

    public QuiosqueController(QuiosqueRepository quiosqueRepository) {
        this.quiosqueService = new QuiosqueService(quiosqueRepository);
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
}
