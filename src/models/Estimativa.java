package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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
	
	@OneToMany (mappedBy="estimativa", cascade = CascadeType.ALL)
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
	
	public void setItemEstimativa(ItemEstimativa itemEstimativa){
			if (this.lista == null){
				System.out.println("� Nulo");
				this.lista = new ArrayList<ItemEstimativa>();
				this.lista.add(itemEstimativa);
			} else {
				System.out.println("N�o � Nulo");
				this.lista.add(itemEstimativa);
			}
	}

	public void removeItemEstimativa(ItemEstimativa itemEstimativa){
		if (this.lista.contains(itemEstimativa)){
			this.lista.remove(itemEstimativa);
		}
	}

	@Override
	public String toString() {
		return "Estimativa [id=" + id + ", data=" + data + ", nome=" + nome + "]";
	}
}
