package model.service;

import model.entities.ContratosEntity;
import model.entities.ReservasEntity;
import model.repositories.ContratosRepository;
import model.repositories.MetodoPgRepository;
import model.repositories.ResevasRepository;

public class AluguelService {
    private ResevasRepository reservasRepository = new ResevasRepository();
    private ContratosRepository contratosRepository = new ContratosRepository();
    private MetodoPgRepository pagamentosRepository = new MetodoPgRepository();

    public AluguelService(ResevasRepository reservasRepository, ContratosRepository contratosRepository, MetodoPgRepository pagamentosRepository) {
        this.reservasRepository = reservasRepository;
        this.contratosRepository = contratosRepository;
        this.pagamentosRepository = pagamentosRepository;
    }

    public ReservasEntity criarNovaReserva(ReservasEntity reserva) {
        return (ReservasEntity) reservasRepository.create(reserva);
    }

    public ContratosEntity criarNovoContrato(ContratosEntity contrato) {
        return contratosRepository.create(contrato);
    }

}
