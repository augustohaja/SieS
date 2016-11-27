package app;

import dao.CategoriaDAO;
import dao.MaterialDAO;
import dao.UsuarioDAO;
import models.CategoriaDireto;
import models.CategoriaIndireto;
import models.Material;
import models.Usuario;

public class Teste {
	public static void main(String[] args){
		CategoriaDireto catDireto = new CategoriaDireto("Tinta");
		CategoriaIndireto catIndireto = new CategoriaIndireto("Pincel");
		CategoriaDAO daoCat = new CategoriaDAO();
		
		daoCat.insert(catDireto);
		daoCat.insert(catIndireto);
		
		MaterialDAO daoMat = new MaterialDAO();
		Material mat = new Material ("Material1",15.0,12.0,"Und",1.0,catDireto);
		daoMat.insert(mat);
		
		
		
		System.out.println(catDireto.calculaConsumo(10.0).toString());
		System.out.println(catIndireto.calculaConsumo(10.0).toString());
		System.out.println(mat.getCategoria().calculaConsumo(10.0));
		mat.setCategoria(catIndireto);
		System.out.println(mat.getCategoria().calculaConsumo(10.0));
	}	
}
