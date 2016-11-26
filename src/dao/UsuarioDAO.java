package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import db.JPAUtil;
import models.Usuario;

public class UsuarioDAO {
	private EntityManager manager;
	
	public UsuarioDAO (){
		this.manager = JPAUtil.getEntityManager();
	}
	
	public void finalize(){
		manager.close();
	}
	
	public void insert(Usuario usuario){
		this.manager.getTransaction().begin();
    	this.manager.persist(usuario);
    	this.manager.getTransaction().commit();
	}
	
	public void update(Usuario usuario){
		this.manager.getTransaction().begin();
    		this.manager.merge(usuario);
    	this.manager.getTransaction().commit();
	}
	
	public void delete(Usuario usuario){
		this.manager.getTransaction().begin();
       	usuario = this.manager.find(Usuario.class, usuario.getId());
    	this.manager.remove(usuario);
    	this.manager.getTransaction().commit();
	}
	
	public List<Usuario> searchByEmail(String eMail){
		Query query = manager.createQuery("SELECT s FROM Usuario s WHERE email = '" + eMail.toString() + "'");
		List<Usuario> list = query.getResultList();
		return list;
	}
	
	public List<Usuario> all() {
    	Query query = manager.createQuery("select s from Usuario s"); 
        List<Usuario> list = query.getResultList();
    	return list;
    }
}
