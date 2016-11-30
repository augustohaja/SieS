package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import db.JPAUtil;
import models.ItemEstimativa;

public class ItemEstimativaDAO {
	private EntityManager manager;

	public ItemEstimativaDAO() {
		this.manager = JPAUtil.getEntityManager();

	}

	public void finalize() {
		manager.close();
	}

	public void insert(ItemEstimativa itemEstimativa) {
		this.manager.getTransaction().begin();
		this.manager.persist(itemEstimativa);
		this.manager.getTransaction().commit();
	}

	public void update(ItemEstimativa itemEstimativa) {
		this.manager.getTransaction().begin();
		this.manager.merge(itemEstimativa);
		this.manager.getTransaction().commit();
	}

	public void delete(ItemEstimativa itemEstimativa) {
		this.manager.getTransaction().begin();
		itemEstimativa = this.manager.find(ItemEstimativa.class, itemEstimativa.getId());
		this.manager.remove(itemEstimativa);
		this.manager.getTransaction().commit();
	}

	public List<ItemEstimativa> all() {
		Query query = manager.createQuery("select s from Estimativa s");
		List<ItemEstimativa> list = query.getResultList();
		return list;
	}

}
