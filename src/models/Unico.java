package models;

public class Unico implements Consumo{
	public Double consumo(Double qtdM2){
		return 1.0;
	}
	public String getConsumo(){
		return "Unico";
	}
}
