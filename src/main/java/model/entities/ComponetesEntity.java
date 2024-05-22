package model.entities;

import javax.persistence.*;
import java.util.Set;

@Entity //Indica que esta classe é uma entidade
@Table(name =  "componentes")//Especifica o nome da tabela no banco de dados à qual esta entidade será mapeada
public class ComponetesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//Especifica que o valor do campo id será gerado automaticamente usando a estratégia de identidade do banco de dados
    long id;
    @Column
    private Boolean pia;

    @Column
    private Boolean churrasqueira;

    @Column
    private Boolean mesa_madeira;

    @Column
    private Boolean mesa_plastico;

    @Column
    private Boolean bancos;

    @Column
    private Boolean corbertura;

    @Column
    private Boolean lixeira;


    @ManyToMany(mappedBy = "componentes")
    private Set<QuiosqueEntity> quiosques;


    public ComponetesEntity(){

    }
    public ComponetesEntity(long id, boolean pia, boolean churrasqueira, boolean mesaMa,
                      boolean mesaPla, boolean bancos, boolean cobertura, boolean lixeira) {
        this.id = id;
        this.pia = pia;
        this.churrasqueira = churrasqueira;
        this.mesa_madeira= mesaMa;
        this.mesa_plastico = mesaPla;
        this.bancos = bancos;
        this.corbertura = cobertura;
        this.lixeira = lixeira;
    }

    public Boolean getPia() {
        return pia;
    }

    public void setPia(Boolean pia) {
        this.pia = pia;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Boolean getChurrasqueira() {
        return churrasqueira;
    }

    public void setChurrasqueira(Boolean churrasqueira) {
        this.churrasqueira = churrasqueira;
    }

    public Boolean getMesa_madeira() {
        return mesa_madeira;
    }

    public void setMesa_madeira(Boolean mesa_madeira) {
        this.mesa_madeira = mesa_madeira;
    }

    public Boolean getMesa_plastico() {
        return mesa_plastico;
    }

    public void setMesa_plastico(Boolean mesa_plastico) {
        this.mesa_plastico = mesa_plastico;
    }

    public Boolean getBancos() {
        return bancos;
    }

    public void setBancos(Boolean bancos) {
        this.bancos = bancos;
    }

    public Boolean getCorbertura() {
        return corbertura;
    }

    public void setCorbertura(Boolean corbertura) {
        this.corbertura = corbertura;
    }

    public Boolean getLixeira() {
        return lixeira;
    }

    public void setLixeira(Boolean lixeira) {
        this.lixeira = lixeira;
    }
}
