package model;

public class FormaPagamento {
	Long id;
	String tipo;
	
	public FormaPagamento() {
		
	}
	
	public FormaPagamento(Long id, String tipo) {
		this.id = id;
		this.tipo = tipo;
	}
}
