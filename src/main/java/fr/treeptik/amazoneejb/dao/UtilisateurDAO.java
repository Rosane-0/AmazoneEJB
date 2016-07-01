package fr.treeptik.amazoneejb.dao;

import fr.treeptik.amazoneejb.exceptions.DAOException;
import fr.treeptik.amazoneejb.pojo.Utilisateur;

public interface UtilisateurDAO extends GenericDAO<Utilisateur, Long> {
	
	Utilisateur addRoleUser(Utilisateur utilisateur) throws DAOException;
	Utilisateur addRoleAdmin(Utilisateur utilisateur) throws DAOException;
	
}
