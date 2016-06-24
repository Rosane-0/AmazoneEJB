package fr.treeptik.amazoneejb.dao;

import fr.treeptik.amazoneejb.pojo.Utilisateur;

public interface UtilisateurDAO extends GenericDAO<Utilisateur, Long> {
	
	Utilisateur addRoleUser(Utilisateur utilisateur);
	Utilisateur addRoleAdmin(Utilisateur utilisateur);
	

}
