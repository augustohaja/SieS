package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import db.JPAUtil;
import models.Categoria;
import models.Material;

public class MaterialDAO {
	private EntityManager manager;
	
	public MaterialDAO (){
		this.manager = JPAUtil.getEntityManager();
	}
	
	public void finalize(){
		manager.close();
	}
	
	public void insert(Material material){
		this.manager.getTransaction().begin();
    	this.manager.persist(material);
    	this.manager.getTransaction().commit();
	}
	
	public void update(Material material){
		this.manager.getTransaction().begin();
    		this.manager.merge(material);
    	this.manager.getTransaction().commit();
	}
	
	public void delete(Material material){
		this.manager.getTransaction().begin();
       	material = this.manager.find(Material.class, material.getId());
    	this.manager.remove(material);
    	this.manager.getTransaction().commit();
	}
	
	public List<Material> all() {
    	Query query = manager.createQuery("select s from Material s"); 
        List<Material> list = query.getResultList();
    	return list;
    }
	
	public List<Material>  searchByName(String nome){
		Query query = manager.createQuery("SELECT s FROM Material s WHERE nome = '" + nome.toString() + "'");
		List<Material>  itemMaterial = query.getResultList();
		return itemMaterial;
	}

}
