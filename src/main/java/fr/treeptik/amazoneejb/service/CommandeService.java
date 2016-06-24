package fr.treeptik.amazoneejb.service;

import java.util.List;

import fr.treeptik.amazoneejb.pojo.Commande;

public interface CommandeService {
	
	Commande add(Commande commande);
	Commande update(Commande commande);
	Commande findById(Long id);
	void remove(Commande commande);
	List<Commande> findAll();

}
