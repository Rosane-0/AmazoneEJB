package fr.treeptik.amazoneejb.service;

import java.util.List;

import fr.treeptik.amazoneejb.exceptions.ServiceException;
import fr.treeptik.amazoneejb.pojo.Utilisateur;

public interface UtilisateurService {
	
	Utilisateur add(Utilisateur utilisateur);
	Utilisateur update(Utilisateur utilisateur);
	Utilisateur findById(Long id);
	void remove(Utilisateur utilisateur);
	List<Utilisateur> findAll();
	Utilisateur addRoleUserDefault(Utilisateur utilisateur) throws ServiceException;

}
