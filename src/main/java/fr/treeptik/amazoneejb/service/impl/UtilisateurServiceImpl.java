package fr.treeptik.amazoneejb.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.transaction.Transactional;

import fr.treeptik.amazoneejb.dao.UtilisateurDAO;
import fr.treeptik.amazoneejb.dao.impl.UtilisateurDAOImpl;
import fr.treeptik.amazoneejb.exceptions.ServiceException;
import fr.treeptik.amazoneejb.pojo.Utilisateur;
import fr.treeptik.amazoneejb.service.UtilisateurService;

@Stateless
public class UtilisateurServiceImpl implements UtilisateurService {
	
	@EJB
	private UtilisateurDAO utilisateurDAO;

	@Override
	@Transactional
	public Utilisateur add(Utilisateur utilisateur) {
		if (utilisateur.getId() == null) {
			utilisateur = utilisateurDAO.add(utilisateur);			
		}
		else {
			utilisateur = utilisateurDAO.update(utilisateur);
		}
		return utilisateur;
	}

	@Override 
	@Transactional
	public Utilisateur update(Utilisateur utilisateur) {
		return utilisateurDAO.update(utilisateur);
	}

	@Override
	@Transactional
	public void remove(Utilisateur utilisateur) {
		utilisateur = utilisateurDAO.findById(utilisateur.getId());
		utilisateurDAO.remove(utilisateur);
	}

	@Override
	@Transactional
	public List<Utilisateur> findAll() {
		return utilisateurDAO.findAll();
	}

	@Override
	@Transactional
	public Utilisateur findById(Long id) {
		return utilisateurDAO.findById(id);
	}

	@Override
	public Utilisateur addRoleUserDefault(Utilisateur utilisateur) throws ServiceException {
		try {
			return utilisateurDAO.addRoleUser(utilisateur);
		} catch (Exception e) {
			throw new ServiceException("Exception dans UtilisateurServiceImpl methode addRoleUserDefault, " + e.getMessage(), e);
		}
	}

}
