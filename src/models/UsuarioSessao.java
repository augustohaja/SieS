package models;

public class UsuarioSessao {
	private static Usuario user; 
	
	public static void setUser(Usuario user){
		UsuarioSessao.user = user;
	}
	
	public static Usuario getUser() {
		return user;
	}
}

