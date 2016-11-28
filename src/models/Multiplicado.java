package models;

public class Multiplicado implements Consumo {
	public Double consumo(Double qtdM2, Double qtd){
		return (qtdM2*qtd); // pegar quantidade do item
	}
	public String getConsumo(){
		return "Multiplicado";
	}
}
