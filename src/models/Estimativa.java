package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Estimativa {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String data;
	private String nome;
	
	@OneToMany (mappedBy="estimativa")
	private List<ItemEstimativa> lista;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<ItemEstimativa> getLista() {
		return lista;
	}

	public void setLista(List<ItemEstimativa> lista) {
		this.lista = lista;
	}

	@Override
	public String toString() {
		return "Estimativa [id=" + id + ", data=" + data + ", nome=" + nome + "]";
	}
}
