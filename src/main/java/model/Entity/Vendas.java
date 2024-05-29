package model.Entity;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public LocalDateTime getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(LocalDateTime dataVenda) {
		this.dataVenda = dataVenda;
	}

	public Long getNfe() {
		return nfe;
	}

	public void setNfe(Long nfe) {
		this.nfe = nfe;
	}
}
