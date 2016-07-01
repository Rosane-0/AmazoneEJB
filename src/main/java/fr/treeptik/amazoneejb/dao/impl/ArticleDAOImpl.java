package fr.treeptik.amazoneejb.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import org.jboss.logging.Logger;

import fr.treeptik.amazoneejb.dao.ArticleDAO;
import fr.treeptik.amazoneejb.exceptions.DAOException;
import fr.treeptik.amazoneejb.managedbean.UtilisateurManagedBean;
import fr.treeptik.amazoneejb.pojo.Article;

@Stateless
public class ArticleDAOImpl implements ArticleDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private Logger logger = Logger.getLogger(ArticleDAOImpl.class);
	
	@Override
	public Article add(Article entite) throws DAOException {
		try {
			if (entite.getId() == null) {
				entityManager.persist(entite);
				logger.info("PERSIST");
			}
			else {
				entityManager.merge(entite);
			}
			return entite;
		} catch (PersistenceException e) {
			throw new DAOException("ERROR in ArticleDAOImpl add " + e.getMessage(), e);
		}
	}

	@Override
	public Article update(Article entite) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Article findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(Article entite) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Article> findAll() throws DAOException  {
		try {
			return entityManager.createQuery("SELECT a FROM Article a", Article.class).getResultList();
		} catch (PersistenceException e) {
			throw new DAOException("ERROR in ArticleDAOImpl findAll " + e.getMessage(), e);
		}
	}

}
