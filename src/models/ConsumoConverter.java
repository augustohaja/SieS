package models;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class ConsumoConverter implements AttributeConverter<Consumo,String>{

	@Override
	public String convertToDatabaseColumn(Consumo consumo) {
		// TODO Auto-generated method stub
		return consumo.getConsumo();
	}

	@Override
	public Consumo convertToEntityAttribute(String consumo) {
		// TODO Auto-generated method stub
		if (consumo.equals("Multiplicado")){
			return new Multiplicado();
		} else if (consumo.equals("Unico")){
			return new Unico();
		}
		return null;
	}

}
