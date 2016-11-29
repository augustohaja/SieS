package app;

import java.util.List;

import dao.CategoriaDAO;
import dao.MaterialDAO;
import dao.UsuarioDAO;
import models.Categoria;
import models.CategoriaDireto;
import models.CategoriaIndireto;
import models.Material;
import models.Usuario;

public class Teste {
	public static void main(String[] args){
		/*CategoriaDireto catDireto = new CategoriaDireto("Tinta");
		CategoriaIndireto catIndireto = new CategoriaIndireto("Pincel");*/
		CategoriaDAO daoCat = new CategoriaDAO();
		
		/*daoCat.insert(catDireto);
		daoCat.insert(catIndireto);
		
		Material mat = new Material ("Material1",15.0,12.0,"Und",1.0,catDireto);*/
		//MaterialDAO daoMat = new MaterialDAO();
		//daoMat.insert(mat);
		
		
		
		//System.out.println(catDireto.calculaConsumo(10.0,5.0).toString());
		//System.out.println(catIndireto.calculaConsumo(10.0,5.0).toString());
		//System.out.println(mat.getCategoria().calculaConsumo(10.0,5.0));
		//mat.setCategoria(catIndireto);
		//System.out.println(mat.getCategoria().calculaConsumo(10.0,5.0));
		
		//List<Material> lista = daoMat.all();
		
		//for (Material x : lista) {
		//	System.out.println(x.getCategoria().calculaConsumo(100.0, 10.0));
		//}
		
		List<Categoria> listaCat = daoCat.all();
		for (Categoria x : listaCat) {
			System.out.println(x.getId());
			System.out.println(x.getNome());
			System.out.println(x.getTipoConsumo());
		}
		
	}
}
