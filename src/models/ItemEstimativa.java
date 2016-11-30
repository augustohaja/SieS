package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class ItemEstimativa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Double qtd;
	
	@OneToOne
	private Material material;

	@ManyToOne
	private Estimativa estimativa;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getQtd() {
		return qtd;
	}

	public void setQtd(Double qtd) {
		this.qtd = qtd;
	}

	public Double getEstimado() {
		return material.getCategoria().calculaConsumo(material.getCoefM2(), qtd);
	}

	public Double getTotal() {
		return this.getEstimado() * this.material.getPreco();
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public Estimativa getEstimativa() {
		return estimativa;
	}

	public void setEstimativa(Estimativa estimativa) {
		this.estimativa = estimativa;
	}

	@Override
	public String toString() {
		return "ItemEstimativa [id=" + id + ", qtd=" + qtd + ", material=" + material + ", estimativa=" + estimativa
				+ "]";
	}
}
