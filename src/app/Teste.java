package app;

import dao.UsuarioDAO;
import models.Usuario;

public class Teste {
	public static void main(String[] args){
		Usuario usuario = new Usuario ("Augusto","augusto@yahoo.com.br","123456");
		UsuarioDAO dao = new UsuarioDAO();

		dao.insert(usuario);
		
		System.out.println(dao.all().size());
	}	
}
