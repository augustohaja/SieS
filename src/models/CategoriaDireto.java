package models;

import javax.persistence.Entity;

@Entity
public class CategoriaDireto extends Categoria {
	public CategoriaDireto(){
		super();
	}
	public CategoriaDireto(String name) {
		// TODO Auto-generated constructor stub
		super(name, new Multiplicado());
	}
}
