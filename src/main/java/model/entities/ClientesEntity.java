package model.entities;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name =  "clientes")
public class ClientesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;

    @Column
    private String telefone;

    @Column
    private String email;

    @Column
    private String cpf;

    @Column
    private int idade;

    @Column(name = "user_status")
    private Boolean UserStatus;

    @OneToMany(mappedBy = "cliente", cascade =  CascadeType.ALL)
    List<ReservasEntity> reservas;

    public ClientesEntity() {

    }
    public ClientesEntity(Long id, String nome, String telefone, String email,Boolean Status, int idade,String cpf) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.cpf = cpf;
        this.idade = idade;
        this.UserStatus = Status;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public Boolean getUserStatus() {
        return UserStatus;
    }

    public void setUserStatus(Boolean userStatus) {
        UserStatus = userStatus;
    }


}
