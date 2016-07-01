package fr.treeptik.amazoneejb.dao.impl;

import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import fr.treeptik.amazoneejb.dao.UtilisateurDAO;
import fr.treeptik.amazoneejb.exceptions.DAOException;
import fr.treeptik.amazoneejb.pojo.Role;
import fr.treeptik.amazoneejb.pojo.Utilisateur;

@Stateless
public class UtilisateurDAOImpl implements UtilisateurDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private final String ROLE_DESCRIPTION_BY_DEFAULT = "ROLE_USER";

	@Override
	public Utilisateur add(Utilisateur entite) throws DAOException{
		try {
			if (entite.getId() == null) {
				entityManager.persist(entite);
			}
			else {
				entityManager.merge(entite);
			}
			return entite;
		} catch (PersistenceException e) {
			throw new DAOException ("Error in UtilisateurDAOImpl add " + e.getMessage(), e);
		}
	}

	@Override
	public Utilisateur update(Utilisateur entite) throws DAOException{
		entityManager.merge(entite);
		return entite;
	}

	@Override
	public Utilisateur findById(Long id) throws DAOException {
		return entityManager.find(Utilisateur.class, id);
	}

	@Override
	public void remove(Utilisateur entite) throws DAOException {
		entityManager.remove(entite);
	}

	@Override
	public List<Utilisateur> findAll() throws DAOException {
		return entityManager.createQuery("SELECT u FROM Utilisateur u", Utilisateur.class).getResultList();
	}

	@Override
	public Utilisateur addRoleUser(Utilisateur utilisateur) throws DAOException {
		utilisateur = entityManager.find(Utilisateur.class, utilisateur.getId());
		Role roleUser = entityManager.createQuery("SELECT r FROM Role r where r.description = :description", Role.class)
				.setParameter("description", ROLE_DESCRIPTION_BY_DEFAULT)
				.getSingleResult();
		Set<Role> roles = utilisateur.getRoles();
		roles.add(roleUser);
		entityManager.merge(utilisateur);
		return utilisateur;
	}

	@Override
	public Utilisateur addRoleAdmin(Utilisateur utilisateur) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

}

