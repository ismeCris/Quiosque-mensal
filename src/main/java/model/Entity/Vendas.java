package intregrador.entidades;

import java.time.LocalDateTime;

public class Vendas {
	Long id;
	float total;
	LocalDateTime dataVenda;
	Long nfe;
	
	public Vendas() {
		
	}
	
	public Vendas(Long id, float total, LocalDateTime data, Long nfe) {
		this.id = id;
		this.total = total;
		this.dataVenda = data;
		this.nfe = nfe;
	}
	
}
