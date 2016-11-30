package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import db.JPAUtil;
import models.Categoria;
import models.Usuario;

public class CategoriaDAO {
	private EntityManager manager;
	
	public CategoriaDAO (){
		this.manager = JPAUtil.getEntityManager();
	}
	
	public void finalize(){
		manager.close();
	}
	
	public void insert(Categoria categoria){
		this.manager.getTransaction().begin();
    	this.manager.persist(categoria);
    	this.manager.getTransaction().commit();
	}
	
	public void update(Categoria categoria){
		this.manager.getTransaction().begin();
    		this.manager.merge(categoria);
    	this.manager.getTransaction().commit();
	}
	
	public void delete(Categoria categoria){
		this.manager.getTransaction().begin();
       	categoria = this.manager.find(Categoria.class, categoria.getId());
    	this.manager.remove(categoria);
    	this.manager.getTransaction().commit();
	}
	
	public List<Categoria> all() {
    	Query query = manager.createQuery("select s from Categoria s"); 
        List<Categoria> list = query.getResultList();
    	return list;
    }
	
	public List<Categoria>  searchByName(String nome){
		Query query = manager.createQuery("SELECT s FROM Categoria s WHERE nome = '" + nome.toString() + "'");
		List<Categoria>  itemCategoria = query.getResultList();
		return itemCategoria;
	}
}
