package fr.treeptik.amazoneejb.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.transaction.Transactional;

import fr.treeptik.amazoneejb.dao.ArticleDAO;
import fr.treeptik.amazoneejb.dao.impl.ArticleDAOImpl;
import fr.treeptik.amazoneejb.exceptions.DAOException;
import fr.treeptik.amazoneejb.exceptions.ServiceException;
import fr.treeptik.amazoneejb.pojo.Article;
import fr.treeptik.amazoneejb.service.ArticleService;

@Stateless
public class ArticleServiceImpl implements ArticleService {

	@EJB
	private ArticleDAO articleDAO = new ArticleDAOImpl();

	@Resource(mappedName="java:/jms/amazoneQueue")
	private Queue queue;
	
	@Resource(mappedName="java:/ConnectionFactory")
	private ConnectionFactory connectionFactory;

	@Override
	@Transactional
	public Article add(Article article) throws ServiceException {
		try {
			if (article.getId() == null) {
				article = articleDAO.add(article);
			}
			else {
				article = articleDAO.update(article);
			}
			return article;
		} catch (DAOException e) {
			throw new ServiceException("ERROR in ArticleServiceImpl Add " + e.getMessage(), e);
		}
	}

	@Override
	@Transactional
	public Article update(Article article) throws ServiceException  {
		try {
			return articleDAO.update(article);
		} catch (DAOException e) {
			throw new ServiceException("ERROR in ArticleServiceImpl Update " + e.getMessage(), e);
		}
	}

	@Override
	@Transactional
	public void remove(Article article) throws ServiceException  {
		try {
			articleDAO.remove(article);
		} catch (DAOException e) {
			throw new ServiceException("ERROR in ArticleServiceImpl remove " + e.getMessage(), e);
		}
	}

	@Override
	@Transactional
	public List<Article> findAll() throws ServiceException  {
		try {
			return articleDAO.findAll();
		} catch (DAOException e) {
			throw new ServiceException("ERROR in ArticleServiceImpl findAll " + e.getMessage(), e);
		}
	}

	@Override
	@Transactional
	public Article findById(Long id) throws ServiceException  {
		try {
			return articleDAO.findById(id);
		} catch (DAOException e) {
			throw new ServiceException("ERROR in ArticleServiceImpl findById " + e.getMessage(), e);
		}
	}
	
	public void sendArticle(Article article) throws ServiceException  {
		Connection connection = null;
		try {
			connection = connectionFactory.createConnection();	
			Session session = connection.createSession();
			MessageProducer messageProducer = session.createProducer(queue);
			ObjectMessage objectMessage = session.createObjectMessage();
			objectMessage.setObject(article);
			messageProducer.send(objectMessage);
		} catch (JMSException e) {
			throw new ServiceException("ERROR in ArticleServiceImpl sendArticle " + e.getMessage(), e);
		} finally {
			try {
				connection.close();
			} catch (JMSException e) {
				throw new ServiceException("ERROR in ArticleServiceImpl sendArticle " + e.getMessage(), e);
			}

		}
	}

}
