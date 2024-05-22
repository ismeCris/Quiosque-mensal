package model.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity //Indica que esta classe é uma entidade
@Table(name =  "reservas")//Especifica o nome da tabela no banco de dados à qual esta entidade será mapeada


public class ReservasEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//Especifica que o valor do campo id será gerado automaticamente usando a estratégia de identidade do banco de dados
    private long id;
    @Column(name = "data_reserva")
    private LocalDateTime dataReserva;


    public ReservasEntity(){

    }
    public ReservasEntity(long id, LocalDateTime dataReserva) {
        this.id = id;
        this.dataReserva = dataReserva;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(LocalDateTime dataReserva) {
        this.dataReserva = dataReserva;
    }
}
