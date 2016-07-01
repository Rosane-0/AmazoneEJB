package fr.treeptik.amazoneejb.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.transaction.Transactional;

import fr.treeptik.amazoneejb.dao.UtilisateurDAO;
import fr.treeptik.amazoneejb.dao.impl.UtilisateurDAOImpl;
import fr.treeptik.amazoneejb.exceptions.DAOException;
import fr.treeptik.amazoneejb.exceptions.ServiceException;
import fr.treeptik.amazoneejb.pojo.Utilisateur;
import fr.treeptik.amazoneejb.service.UtilisateurService;

@Stateless
public class UtilisateurServiceImpl implements UtilisateurService {
	
	@EJB
	private UtilisateurDAO utilisateurDAO;

	@Override
	@Transactional
	public Utilisateur add(Utilisateur utilisateur) throws ServiceException {
		try {
			if (utilisateur.getId() == null) {
				utilisateur = utilisateurDAO.add(utilisateur);
				System.err.println("ADD DAO");
			}
			else {
				utilisateur = utilisateurDAO.update(utilisateur);
			}
			return utilisateur;
		} catch (DAOException e) {
			throw new ServiceException("Error in UtilisateurServiceImpl add " + e.getMessage(), e);
		}
	}

	@Override 
	@Transactional
	public Utilisateur update(Utilisateur utilisateur) throws ServiceException {
		System.err.println("UPDATE DAO");
		return utilisateurDAO.update(utilisateur);
	}

	@Override
	@Transactional
	public void remove(Utilisateur utilisateur) throws ServiceException {
		utilisateur = utilisateurDAO.findById(utilisateur.getId());
		utilisateurDAO.remove(utilisateur);
	}

	@Override
	@Transactional
	public List<Utilisateur> findAll() throws ServiceException {
		return utilisateurDAO.findAll();
	}

	@Override
	@Transactional
	public Utilisateur findById(Long id) throws ServiceException {
		return utilisateurDAO.findById(id);
	}

	@Override
	public Utilisateur addRoleUserDefault(Utilisateur utilisateur) throws ServiceException {
		try {
			return utilisateurDAO.addRoleUser(utilisateur);
		} catch (DAOException e) {
			throw new ServiceException("Exception dans UtilisateurServiceImpl methode addRoleUserDefault, " + e.getMessage(), e);
		}
	}

}
