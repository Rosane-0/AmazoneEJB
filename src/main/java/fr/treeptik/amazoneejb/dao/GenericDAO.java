package fr.treeptik.amazoneejb.dao;

import java.util.List;

public interface GenericDAO<T, Pk> {

	T add(T entite);
	T update(T entite);
	T findById(Pk id);
	void remove(T entite);
	List<T> findAll();

}
