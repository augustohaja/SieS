package models;

public class Material {
	private Long id;
	private String nome;
	private Double preco;
	private Double qtdEmbalagem;
	
	public Material(){
		
	}
	
	public Material(Long id, String nome, Double preco, Double qtdEmbalagem) {
		super();
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.qtdEmbalagem = qtdEmbalagem;
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
	@Override
	public String toString() {
		return "Material [id=" + id + ", nome=" + nome + ", preco=" + preco + ", qtdEmbalagem=" + qtdEmbalagem + "]";
	}
}
