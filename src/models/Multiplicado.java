package models;

public class Multiplicado implements Consumo {
	public Double consumo(Double qtdM2){
		return (qtdM2*10.0); // pegar quantidade do item
	}
	public String getConsumo(){
		return "Multiplicado";
	}
}
