package model.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import javax.persistence.JoinColumn;

@Entity //Indica que esta classe é uma entidade
@Table(name =  "quiosques")//Especifica o nome da tabela no banco de dados à qual esta entidade será mapeada

public class QuiosqueEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//Especifica que o valor do campo id será gerado automaticamente usando a estratégia de identidade do banco de dados
    private Long id;

    @Column
    private int numero;

    @Column
    private String localidade;

    @Column
    private int capacidade;

    @Column(name = "dispo_status")
    private Boolean dispoStatus;

    @ManyToMany
    @JoinTable(
            name = "componentes_has_qiosques",
            joinColumns = @JoinColumn(name = "quiosque_id_fk"),
            inverseJoinColumns = @JoinColumn(name = "componentes_fk")
    )
    private List<ComponetesEntity> componentes;

    public QuiosqueEntity(List<ComponetesEntity> componentes){

        this.componentes = componentes;
    }

    public QuiosqueEntity() {

    }

    public QuiosqueEntity(Long id, int numero, String localidade, int capacidade, boolean disponibilidadeStatus, Set<ComponetesEntity> componentes) {
        this.id = id;
        this.numero = numero;
        this.localidade = localidade;
        this.capacidade = capacidade;
        this.dispoStatus = disponibilidadeStatus;
        this.componentes = (List<ComponetesEntity>) componentes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public Boolean getDispoStatus() {
        return dispoStatus;
    }

    public void setDispoStatus(Boolean dispoStatus) {
        this.dispoStatus = dispoStatus;
    }
}
