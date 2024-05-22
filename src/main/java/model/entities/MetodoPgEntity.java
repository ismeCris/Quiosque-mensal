package model.entities;

import javax.persistence.*;

@Entity //Indica que esta classe é uma entidade
@Table(name =  "metodoPag")//Especifica o nome da tabela no banco de dados à qual esta entidade será mapeada

public class MetodoPgEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//Especifica que o valor do campo id será gerado automaticamente usando a estratégia de identidade do banco de dados

    @Column
    private Long id;

    @Column
    private String tipo;

    public MetodoPgEntity(){

    }
    public MetodoPgEntity(Long id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
