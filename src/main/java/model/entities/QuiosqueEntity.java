package model.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "quiosques")
public class QuiosqueEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private int numero;

    @Column
    private String localidade;

    @Column
    private int capacidade;

    @Column(name = "dispo_status")
    private Boolean disponibilidadeStatus;

    @Column(name = "preco_diaria", nullable = false, columnDefinition = "DECIMAL(10, 2) DEFAULT 80.00")
    private double precoDiaria;

    @ManyToMany(mappedBy = "quiosques")
    private Set<ComponetesEntity> componentes;

    public QuiosqueEntity() {
    }

    public QuiosqueEntity(int numero, String localidade, int capacidade, Boolean disponibilidadeStatus, double precoDiaria) {
        this.numero = numero;
        this.localidade = localidade;
        this.capacidade = capacidade;
        this.disponibilidadeStatus = disponibilidadeStatus;
        this.precoDiaria = precoDiaria;
    }

    // Getters e setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public Boolean getDisponibilidadeStatus() {
        return disponibilidadeStatus;
    }

    public void setDisponibilidadeStatus(Boolean disponibilidadeStatus) {
        this.disponibilidadeStatus = disponibilidadeStatus;
    }

    public double getPrecoDiaria() {
        return precoDiaria;
    }

    public void setPrecoDiaria(double precoDiaria) {
        this.precoDiaria = precoDiaria;
    }

    public Set<ComponetesEntity> getComponentes() {
        return componentes;
    }

    public void setComponentes(Set<ComponetesEntity> componentes) {
        this.componentes = componentes;
    }

    public void addComponente(ComponetesEntity componente) {
        this.componentes.add(componente);
        componente.getQuiosques().add(this);
    }

    public void removeComponente(ComponetesEntity componente) {
        this.componentes.remove(componente);
        componente.getQuiosques().remove(this);
    }
}
