package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Material {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private Double preco;
	private Double qtdEmbalagem; // 5, 4 
	private String undMedida; //unidade, kilos
	private Double coefM2; //rendimento
	
	@ManyToOne
	private Categoria categoria;
	
	
	public Material(){
		
	}
	
	public Material(String nome, Double preco, Double qtdEmbalagem, String undMedida, Double coefM2, Categoria categoria) {
		//this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.qtdEmbalagem = qtdEmbalagem;
		this.undMedida = undMedida;
		this.coefM2 = coefM2;
		this.categoria = categoria;
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

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Double getQtdEmbalagem() {
		return qtdEmbalagem;
	}

	public void setQtdEmbalagem(Double qtdEmbalagem) {
		this.qtdEmbalagem = qtdEmbalagem;
	}

	public String getUndMedida() {
		return undMedida;
	}

	public void setUndMedida(String undMedida) {
		this.undMedida = undMedida;
	}

	public Double getCoefM2() {
		return coefM2;
	}

	public void setCoefM2(Double coefM2) {
		this.coefM2 = coefM2;
	}

	public String getCategoria() {
		return this.categoria.getNome();
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "Material [id=" + id + ", nome=" + nome + ", preco=" + preco + ", qtdEmbalagem=" + qtdEmbalagem
				+ ", undMedida=" + undMedida + ", coefM2=" + coefM2 + ", categoria=" + categoria + "]";
	}
	
	
	
}
