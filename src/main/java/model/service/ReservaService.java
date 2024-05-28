package model.service;

import model.entities.ReservasEntity;
import model.repositories.ResevasRepository;
import java.time.LocalDate;
import java.util.List;

public class ReservaService {
    private final ResevasRepository reservaRepository;

    public ReservaService(ResevasRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public ReservaService() {
        this.reservaRepository = new ResevasRepository();
    }

    public ReservasEntity atualizarReserva(ReservasEntity reserva) {
        return (ReservasEntity) reservaRepository.update(reserva);
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

    public ReservasEntity criarReserva(ReservasEntity reserva) {

        if (isQuiosqueDisponivel(reserva)) {

            return reservaRepository.create(reserva);
        } else {
            return null;
        }
    }

    private boolean isQuiosqueDisponivel(ReservasEntity novaReserva) {
        List<ReservasEntity> reservasExistente = reservaRepository.findAll();

        for (ReservasEntity reservaExistente : reservasExistente) {

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

    public ReservasEntity encontrarReservaPorId(Long id) {
        return (ReservasEntity) reservaRepository.findById(id);
    }


}
