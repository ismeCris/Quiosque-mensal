package model.Entity;

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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}
}
