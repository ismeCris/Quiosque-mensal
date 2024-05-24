package model.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity //Indica que esta classe é uma entidade
@Table(name =  "reservas")//Especifica o nome da tabela no banco de dados à qual esta entidade será mapeada


public class ReservasEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//Especifica que o valor do campo id será gerado automaticamente usando a estratégia de identidade do banco de dados
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

    @ManyToOne
    @JoinColumn(name = "contratos_id_fk")
    private ContratosEntity contrato;

    public ReservasEntity(){

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
}
