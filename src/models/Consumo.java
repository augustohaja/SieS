package models;

import javax.persistence.MappedSuperclass;

@MappedSuperclass 
public interface Consumo {
	public Double consumo(Double qtdM2,Double qtd);
	public String getConsumo();
}
