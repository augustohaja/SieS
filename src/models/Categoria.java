package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.OneToMany;

@Entity
public abstract class Categoria {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nome;
	@Column
	@Convert (converter = ConsumoConverter.class)
	private Consumo tipoConsumo;
	
	@OneToMany (mappedBy="categoria")
	private List<Material> lista;
	
	public Categoria(){

	}
	
	public Categoria(String nome, Consumo tipoConsumo){
		//this.id = id;
		this.nome = nome;
		this.tipoConsumo = tipoConsumo;
	}
	
	public Double calculaConsumo(Double qtdM2){
		return this.tipoConsumo.consumo(qtdM2);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Consumo getTipoConsumo() {
		return tipoConsumo;
	}

	public void setTipoConsumo(Consumo tipoConsumo) {
		this.tipoConsumo = tipoConsumo;
	}
}
