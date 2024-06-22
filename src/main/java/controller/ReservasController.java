package controller;

import model.entities.QuiosqueEntity;
import model.entities.ReservasEntity;
import model.service.ReservaService;

import java.time.LocalDate;
import java.util.List;

public class ReservasController {
    private final ReservaService reservaService;

    public ReservasController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    public ReservasEntity criarReserva(ReservasEntity reserva) {
        return reservaService.criarReserva(reserva);
    }

    public ReservasEntity atualizarReserva(ReservasEntity reserva) {
        return reservaService.atualizarReserva(reserva);
    }

    public void excluirReserva(Long id) {
        reservaService.excluirReserva(id);
    }

    public List<ReservasEntity> encontrarTodasReservas() {
        return reservaService.encontrarTodasReservas();
    }

    public List<ReservasEntity> encontrarReservasPorData(LocalDate data) {
        return reservaService.encontrarReservasPorData(data);
    }

    public ReservasEntity encontrarReservaPorId(Long id) {
        return reservaService.encontrarReservaPorId(id);
    }

    public List<ReservasEntity> findReservasExpiradas(LocalDate data) {
        return reservaService.findReservasExpiradas(data);
    }

    public void removerReserva(ReservasEntity reserva) {
        reservaService.removerReserva(reserva);
    }
    
    public List<ReservasEntity> obterReservasPorQuiosque(QuiosqueEntity quiosque) {
        return reservaService.obterReservasPorQuiosque(quiosque);
    }

    public boolean isQuiosqueAlugadoNoPeriodo(QuiosqueEntity quiosque, LocalDate inicio, LocalDate fim) {
        return reservaService.isQuiosqueAlugadoNoPeriodo(quiosque, inicio, fim);
    }

}
