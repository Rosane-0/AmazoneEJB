package fr.treeptik.amazoneejb.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.treeptik.amazoneejb.dao.GenericDAO;

public class GenericDAOImpl<T, Pk> implements GenericDAO<T, Pk> {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public Class<T> type;

	public GenericDAOImpl(Class<T> type) {
		this.type = type;
	}

	@Override
	public T add(T entite) {
		entityManager.persist(entite);
		return entite;
	}

	@Override
	public T update(T entite) {
		entityManager.merge(entite);
		return entite;
	}

	@Override
	public T findById(Pk id) {
		return entityManager.find(type, id);
	}

	@Override
	public void remove(T entite) {
		entityManager.remove(entite);
	}

	@Override
	public List<T> findAll() {
		return entityManager.createQuery("SELECT e FROM " + type.getSimpleName() + " e", type).getResultList();
	}





}
