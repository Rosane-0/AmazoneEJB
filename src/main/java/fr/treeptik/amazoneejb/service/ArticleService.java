package fr.treeptik.amazoneejb.service;

import java.util.List;

import fr.treeptik.amazoneejb.pojo.Article;

public interface ArticleService {
	
	Article add(Article article);
	Article update(Article article);
	Article findById(Long id);
	void remove(Article article);
	List<Article> findAll();

}
