package model.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
@Entity
@Table(name = "reservas")
public class ReservasEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "data_reserva")
    private LocalDate dataInicio;

    @Column(name = "data_fim")
    private LocalDate dataFim;

    @ManyToOne
    @JoinColumn(name = "quiosque_id_fk")
    private QuiosqueEntity quiosque;

    @ManyToOne
    @JoinColumn(name = "clientes_id_fk")
    private ClientesEntity cliente;

    @Column(name = "preco_diaria")
    private BigDecimal valorDiaria;

    @Column(name = "valor_total")
    private BigDecimal valorTotal;
    

    public ReservasEntity() {

    }
    // Construtor com parâmetros
    public ReservasEntity(long id, LocalDate dataInicio, LocalDate dataFim) {
        this.id = id;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    // Getters e Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public QuiosqueEntity getQuiosque() {
        return quiosque;
    }

    public void setQuiosque(QuiosqueEntity quiosque) {
        this.quiosque = quiosque;
    }

    public ClientesEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClientesEntity cliente) {
        this.cliente = cliente;
    }


    public BigDecimal getPrecoDiaria() {
        return valorDiaria;
    }

    public void setPrecoDiaria(BigDecimal precoDiaria) {
        this.valorDiaria = precoDiaria;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }


}


