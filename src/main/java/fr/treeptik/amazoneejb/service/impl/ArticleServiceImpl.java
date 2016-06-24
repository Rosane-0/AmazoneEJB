package fr.treeptik.amazoneejb.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.transaction.Transactional;

import fr.treeptik.amazoneejb.dao.ArticleDAO;
import fr.treeptik.amazoneejb.dao.impl.ArticleDAOImpl;
import fr.treeptik.amazoneejb.pojo.Article;
import fr.treeptik.amazoneejb.service.ArticleService;

@Stateless
public class ArticleServiceImpl implements ArticleService {

	@EJB
	private ArticleDAO articleDAO = new ArticleDAOImpl();

	@Override
	@Transactional
	public Article add(Article article) {
		return article = articleDAO.add(article);
	}

	@Override
	@Transactional
	public Article update(Article article) {
		return articleDAO.update(article);
	}

	@Override
	@Transactional
	public void remove(Article article) {
		articleDAO.remove(article);
	}

	@Override
	@Transactional
	public List<Article> findAll() {
		return articleDAO.findAll();
	}

	@Override
	@Transactional
	public Article findById(Long id) {
		return articleDAO.findById(id);
	}

}
