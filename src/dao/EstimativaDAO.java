package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import db.JPAUtil;
import models.Estimativa;
import models.ItemEstimativa;
import models.Usuario;

public class EstimativaDAO {
	private EntityManager manager;

	public EstimativaDAO() {
		this.manager = JPAUtil.getEntityManager();

	}

	public void finalize() {
		manager.close();
	}

	public void insert(Estimativa estimativa) {
		this.manager.getTransaction().begin();
		this.manager.persist(estimativa);
		this.manager.getTransaction().commit();
	}

	public void update(Estimativa estimativa) {
		this.manager.getTransaction().begin();
		this.manager.merge(estimativa);
		this.manager.getTransaction().commit();
	}

	public void delete(Estimativa estimativa) {
		this.manager.getTransaction().begin();
		estimativa = this.manager.find(Estimativa.class, estimativa.getId());
		this.manager.remove(estimativa);
		this.manager.getTransaction().commit();
	}

	public List<Estimativa> all() {
		Query query = manager.createQuery("select s from Estimativa s");
		List<Estimativa> list = query.getResultList();
		return list;
	}

	public List<ItemEstimativa> searchByEstimativa(Long id){
		Query query = manager.createQuery("SELECT s FROM ItemEstimativa s WHERE estimativa_id = '" + id + "'");
		List<ItemEstimativa> list = query.getResultList();
		return list;
	}
}
