package model.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name =  "reservas")
public class ReservasEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "data_reserva")
    private LocalDate dataInicio;

    @Column(name = "data_fim")
    private LocalDate dataFim;

    @Column(name = "detalhes")
    private String detalhes;

    @ManyToOne
    @JoinColumn(name = "quiosque_id_fk")
    private QuiosqueEntity quiosque;

    @ManyToOne
    @JoinColumn(name = "clientes_id_fk")
    private ClientesEntity cliente;

    @OneToOne
    @JoinColumn(name = "contratos_id_fk")
    private ContratosEntity contrato;

    @Column(name = "preco_diaria")
    private BigDecimal valorDiaria = BigDecimal.valueOf(80.00);

    @Column(name = "valor_total")
    private BigDecimal valorTotal;

    public ReservasEntity() {

    }
    // Construtor com parâmetros
    public ReservasEntity(long id, LocalDate dataInicio, LocalDate dataFim, String detalhes) {
        this.id = id;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.detalhes = detalhes;
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

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
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

    public ContratosEntity getContrato() {
        return contrato;
    }

    public void setContrato(ContratosEntity contrato) {
        this.contrato = contrato;
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


