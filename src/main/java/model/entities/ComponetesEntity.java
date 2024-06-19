package model.entities;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "componentes")
public class ComponetesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private Boolean pia;

    @Column
    private Boolean churrasqueira;

    @Column
    private Boolean mesaMadeira;

    @Column
    private Boolean mesaPlastico;

    @Column
    private Boolean bancos;

    @Column
    private Boolean cobertura;

    @Column
    private Boolean lixeira;

    @ManyToMany
    @JoinTable(
        name = "componentes_has_qiosques",
        joinColumns = @JoinColumn(name = "componente_id"),
        inverseJoinColumns = @JoinColumn(name = "quiosque_id")
    )
    private Set<QuiosqueEntity> quiosques;

    public ComponetesEntity() {
    }

    public ComponetesEntity(long id, Boolean pia, Boolean churrasqueira, Boolean mesaMadeira,
                             Boolean mesaPlastico, Boolean bancos, Boolean cobertura, Boolean lixeira) {
        this.id = id;
        this.pia = pia;
        this.churrasqueira = churrasqueira;
        this.mesaMadeira = mesaMadeira;
        this.mesaPlastico = mesaPlastico;
        this.bancos = bancos;
        this.cobertura = cobertura;
        this.lixeira = lixeira;
    }

    // Getters e setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Boolean getPia() {
        return pia;
    }

    public void setPia(Boolean pia) {
        this.pia = pia;
    }

    public Boolean getChurrasqueira() {
        return churrasqueira;
    }

    public void setChurrasqueira(Boolean churrasqueira) {
        this.churrasqueira = churrasqueira;
    }

    public Boolean getMesaMadeira() {
        return mesaMadeira;
    }

    public void setMesaMadeira(Boolean mesaMadeira) {
        this.mesaMadeira = mesaMadeira;
    }

    public Boolean getMesaPlastico() {
        return mesaPlastico;
    }

    public void setMesaPlastico(Boolean mesaPlastico) {
        this.mesaPlastico = mesaPlastico;
    }

    public Boolean getBancos() {
        return bancos;
    }

    public void setBancos(Boolean bancos) {
        this.bancos = bancos;
    }

    public Boolean getCobertura() {
        return cobertura;
    }

    public void setCobertura(Boolean cobertura) {
        this.cobertura = cobertura;
    }

    public Boolean getLixeira() {
        return lixeira;
    }

    public void setLixeira(Boolean lixeira) {
        this.lixeira = lixeira;
    }

    public Set<QuiosqueEntity> getQuiosques() {
        return quiosques;
    }

    public void setQuiosques(Set<QuiosqueEntity> quiosques) {
        this.quiosques = quiosques;
    }
}
