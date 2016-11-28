package models;

import javax.persistence.Entity;

@Entity
public class CategoriaIndireto extends Categoria {
	public CategoriaIndireto(){
		super();
	}
	public CategoriaIndireto(String name) {
		// TODO Auto-generated constructor stub
		super(name, new Unico());
	}
}
