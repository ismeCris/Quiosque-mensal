package view;

import controller.ReservasController;
import model.entities.ReservasEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ReservaScheduler {
    private final ReservasController reservasController;
    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public ReservaScheduler(ReservasController reservasController) {
        this.reservasController = reservasController;
    }

    public void startScheduler() {
        scheduler.scheduleAtFixedRate(this::verificarReservasExpiradas, 0, 1, TimeUnit.HOURS);
    }

    private void verificarReservasExpiradas() {
        LocalDate hoje = LocalDate.now();
        List<ReservasEntity> reservasExpiradas = reservasController.findReservasExpiradas(hoje);

        for (ReservasEntity reserva : reservasExpiradas) {
            liberarQuiosque(reserva.getQuiosque().getNumero());
            reservasController.removerReserva(reserva);
        }
    }

    private void liberarQuiosque(Integer numeroQuiosque) {
        try {
            // Suponha que há um método no controller ou serviço para atualizar o status do quiosque
            // Aqui você deveria chamar esse método para atualizar o status do quiosque
            System.out.println("Liberando quiosque número: " + numeroQuiosque);
            // Exemplo: quiosqueService.atualizarStatusQuiosque(numeroQuiosque, false);
        } catch (Exception e) {
            System.err.println("Erro ao liberar o quiosque número: " + numeroQuiosque);
            e.printStackTrace();
        }
    }
}
