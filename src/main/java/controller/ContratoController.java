package controller;

import model.entities.ContratosEntity;
import model.service.AluguelService;

public class ContratoController {
    private final AluguelService aluguelService;

    public ContratoController(AluguelService aluguelService) {
        this.aluguelService = aluguelService;
    }

  /*  public ContratosEntity criarContrato(ContratosEntity contrato) {
        return aluguelService.criarNovoContrato(contrato);
    }*/
}
