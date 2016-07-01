package fr.treeptik.amazoneejb.service;

import java.util.List;

import fr.treeptik.amazoneejb.exceptions.ServiceException;
import fr.treeptik.amazoneejb.pojo.Article;

public interface ArticleService {
	
	Article add(Article article)  throws ServiceException;
	Article update(Article article) throws ServiceException;
	Article findById(Long id) throws ServiceException;
	void remove(Article article) throws ServiceException;
	List<Article> findAll() throws ServiceException;
	void sendArticle(Article article) throws ServiceException;

}
