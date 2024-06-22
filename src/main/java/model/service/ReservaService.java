package model.service;

import model.entities.QuiosqueEntity;
import model.entities.ReservasEntity;
import model.repositories.ResevasRepository;

import java.time.LocalDate;
import java.util.List;

public class ReservaService {
    private final ResevasRepository reservaRepository;

    public ReservaService(ResevasRepository reservaRepository) {
    	this.reservaRepository = reservaRepository;
    }

    public ReservasEntity criarReserva(ReservasEntity reserva) {
        if (isQuiosqueDisponivel(reserva)) {
            return reservaRepository.create(reserva);
        } else {
            throw new IllegalArgumentException("O quiosque não está disponível para as datas escolhidas.");
        }
    }

    public ReservasEntity atualizarReserva(ReservasEntity reserva) {
        return reservaRepository.update(reserva);
    }

    public void excluirReserva(Long id) {
        reservaRepository.delete(id);
    }

    public List<ReservasEntity> encontrarTodasReservas() {
        return reservaRepository.findAll();
    }

    public List<ReservasEntity> encontrarReservasPorData(LocalDate data) {
        return reservaRepository.findByDate(data);
    }

    public ReservasEntity encontrarReservaPorId(Long id) {
        return reservaRepository.findById(id);
    }

    private boolean isQuiosqueDisponivel(ReservasEntity novaReserva) {
        List<ReservasEntity> reservasExistentes = reservaRepository.findAll();

        for (ReservasEntity reservaExistente : reservasExistentes) {
            if (reservaExistente.getQuiosque().equals(novaReserva.getQuiosque()) &&
                isOverlapping(reservaExistente.getDataInicio(), reservaExistente.getDataFim(),
                               novaReserva.getDataInicio(), novaReserva.getDataFim())) {
                return false;
            }
        }
        return true;
    }

    private boolean isOverlapping(LocalDate start1, LocalDate end1, LocalDate start2, LocalDate end2) {
        return start1.isBefore(end2) && start2.isBefore(end1);
    }

    public List<ReservasEntity> findReservasExpiradas(LocalDate data) {
        List<ReservasEntity> reservas = reservaRepository.findAll();
        reservas.removeIf(r -> !r.getDataFim().isBefore(data));
        return reservas;
    }

    public void removerReserva(ReservasEntity reserva) {
        reservaRepository.delete(reserva.getId());
    }
    
    public List<ReservasEntity> obterReservasPorQuiosque(QuiosqueEntity quiosque) {
        return reservaRepository.obterReservasPorQuiosque(quiosque);
    }

    public boolean isQuiosqueAlugadoNoPeriodo(QuiosqueEntity quiosque, LocalDate inicio, LocalDate fim) {
        return reservaRepository.isQuiosqueAlugadoNoPeriodo(quiosque, inicio, fim);
    }
    
}
