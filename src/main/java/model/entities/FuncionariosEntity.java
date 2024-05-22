package model.entities;

import javax.persistence.*;

@Entity //Indica que esta classe é uma entidade
@Table(name =  "funcionarios")//Especifica o nome da tabela no banco de dados à qual esta entidade será mapeada
public class FuncionariosEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//Especifica que o valor do campo id será gerado automaticamente usando a estratégia de identidade do banco de dados
    private Long id;

    @Column
    private String nome;

    @Column
    private String senha;

    @Column
    private String email;

    @Column
    private String telefone;

    @Column(nullable = false)
    private String cargo;

    public FuncionariosEntity(){

    }
    public FuncionariosEntity(Long id, String nome, String senha, String email, String telefone, String cargo) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.telefone = telefone;
        this.cargo = cargo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
