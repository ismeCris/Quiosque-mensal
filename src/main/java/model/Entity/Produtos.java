package intregrador.entidades;

public class Produtos {
	Long id;
	String nome;
	String descricao;
	float preco;
	
	public Produtos() {
		
	}
	
	public Produtos(Long id, String nome, String desc, float preco) {
		this.id = id;
		this.nome = nome;
		this.descricao = desc;
		this.preco = preco;
	}

}
