package model.entities;


import javax.persistence.*;

@Entity //Indica que esta classe é uma entidade
@Table(name =  "clientes")//Especifica o nome da tabela no banco de dados à qual esta entidade será mapeada
public class ClientesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//Especifica que o valor do campo id será gerado automaticamente usando a estratégia de identidade do banco de dados
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
