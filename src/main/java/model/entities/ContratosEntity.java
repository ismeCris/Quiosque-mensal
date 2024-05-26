package model.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name =  "contratos")
public class ContratosEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "inio_date")
    private LocalDateTime dataInicio;

    @Column(name = "fim_date")
    private LocalDateTime dataFim;

    @Column(name = "valor_aluguel")
    private BigDecimal valorAluguel;

    @Column(name = "status_pag")
    private Boolean statusPag;

   /* @OneToOne
    @JoinColumn(name = "cliente_id_fk")
    private ClientesEntity cliente;*/

    @ManyToOne
    @JoinColumn(name = "quiosque_id_fk")
    private QuiosqueEntity quiosque;


    @Column(name = "preco_diaria")
    private BigDecimal valorDiaria = BigDecimal.valueOf(80.00);

    @Column(name = "valor_total")
    private BigDecimal valorTotal;
    public ContratosEntity() {

    }

    public ContratosEntity(long id, LocalDateTime dataInicio, LocalDateTime dataFim, BigDecimal valorAluguel, Boolean statusPag/*, ClientesEntity cliente*/, QuiosqueEntity quiosque) {
        this.id = id;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.valorAluguel = valorAluguel;
        this.statusPag = statusPag;
        this.valorDiaria = BigDecimal.ZERO;
        this.quiosque = quiosque;
    }
    public ContratosEntity(ReservasEntity reservas){
        this.dataInicio = reservas.getDataInicio().atTime(LocalTime.MIDNIGHT);
        this.dataFim = reservas.getDataFim().atTime(LocalTime.MIDNIGHT);
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDateTime getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDateTime dataFim) {
        this.dataFim = dataFim;
    }

    public BigDecimal getValorAluguel() {
        return valorAluguel;
    }

    public void setValorAluguel(BigDecimal valorAluguel) {
        this.valorAluguel = valorAluguel;
    }

    public Boolean getStatusPag() {
        return statusPag;
    }

    public void setStatusPag(Boolean statusPag) {
        this.statusPag = statusPag;
    }

    public void imprimirContrato() {
        System.out.println("Contrato:");
        System.out.println("ID: " + this.getId());
        System.out.println("Data de Início: " + this.getDataInicio());
        System.out.println("Data de Fim: " + this.getDataFim());
        System.out.println("Valor do Aluguel: " + this.getValorAluguel());
        // Imprimir outros detalhes do contrato, se necessário
    }

}
