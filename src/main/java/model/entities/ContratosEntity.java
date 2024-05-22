package model.entities;

import javax.persistence.*;//Importa todas as clases de la API de persistencia de Java (JPA),
import java.time.LocalDateTime;

@Entity //Indica que esta classe é uma entidade
@Table(name =  "contratos")//Especifica o nome da tabela no banco de dados à qual esta entidade será mapeada
public class ContratosEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//Especifica que o valor do campo id será gerado automaticamente usando a estratégia de identidade do banco de dados
    private long id;

    @Column(name = "inio_date")
    private LocalDateTime dataInicio;

    @Column(name = "fim_date")
    private LocalDateTime dataFim;

    @Column(name = "valor_aluguel")
    private float valorAluguel;

    @Column(name = "status_pag")
    private Boolean statusPag;

    public ContratosEntity(){

    }

    public ContratosEntity(long id, LocalDateTime dataInicio, LocalDateTime dataFim, float valorAluguel, boolean statusPag) {
        this.id = id;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.valorAluguel = valorAluguel;
        this.statusPag = statusPag;
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

    public float getValorAluguel() {
        return valorAluguel;
    }

    public void setValorAluguel(float valorAluguel) {
        this.valorAluguel = valorAluguel;
    }

    public Boolean getStatusPag() {
        return statusPag;
    }

    public void setStatusPag(Boolean statusPag) {
        this.statusPag = statusPag;
    }
}
