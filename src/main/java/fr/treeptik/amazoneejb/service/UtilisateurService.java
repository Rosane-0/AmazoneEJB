package fr.treeptik.amazoneejb.service;

import java.util.List;

import fr.treeptik.amazoneejb.exceptions.ServiceException;
import fr.treeptik.amazoneejb.pojo.Utilisateur;

public interface UtilisateurService {
	
	Utilisateur add(Utilisateur utilisateur) throws ServiceException;
	Utilisateur update(Utilisateur utilisateur) throws ServiceException;
	Utilisateur findById(Long id) throws ServiceException;
	void remove(Utilisateur utilisateur) throws ServiceException;
	List<Utilisateur> findAll() throws ServiceException;
	Utilisateur addRoleUserDefault(Utilisateur utilisateur) throws ServiceException;

}
