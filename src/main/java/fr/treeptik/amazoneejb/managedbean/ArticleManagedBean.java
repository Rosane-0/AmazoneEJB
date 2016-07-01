package fr.treeptik.amazoneejb.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import org.jboss.logging.Logger;

import fr.treeptik.amazoneejb.builder.DVDbuilder;
import fr.treeptik.amazoneejb.dao.impl.ArticleDAOImpl;
import fr.treeptik.amazoneejb.pojo.Article;
import fr.treeptik.amazoneejb.pojo.CD;
import fr.treeptik.amazoneejb.pojo.DVD;
import fr.treeptik.amazoneejb.pojo.Livre;
import fr.treeptik.amazoneejb.service.ArticleService;
import fr.treeptik.amazoneejb.utils.ClassFinder;

@ManagedBean
@RequestScoped
public class ArticleManagedBean implements Serializable  {

	private static final long serialVersionUID = 1L;

	@EJB
	public ArticleService articleService;
	
	private Article article = new Article();
	private DVD dvd = new DVD();
	private CD cd = new CD();
	private Livre livre = new Livre();
	private List<Article> articles = new ArrayList<>();
	
	private final String POJO_PACKAGE = "fr.treeptik.amazoneejb.pojo";
	private List<String> classesArticle = new ArrayList<>();
	private String type = "";
	
	private Logger logger = Logger.getLogger(ArticleManagedBean.class);
	
	@PostConstruct
	public void init() {
		getArticleTypes();
		logger.info("INIT PAGE ****************************************************************");
		articles = articleService.findAll();
		type = "...";
	}

	private void getArticleTypes() {
		List<Class<?>> classes = ClassFinder.find(POJO_PACKAGE);
		for (Class<?> class1 : classes) {
			if (class1.getSuperclass().getSimpleName().equals("Article")) {
				classesArticle.add(class1.getSimpleName());
			}
		}
	}
	
	public void addArticleToJMSQueue() {
		
		logger.info("TYPE SELECTIONNE : " + type);
		
		switch (type) {
		
			case "DVD":
				logger.info("TITRE : " + article.getTitre());
				logger.info("DESCRIPTION : " + dvd.getDescription());
				DVD dvdToPersist = buildDVD();
				articleService.sendArticle(dvd);
				break;
				
			case "CD":
				
				break;
				
			case "Livre":
				
				break;
				
			case "...":
				logger.info("TITRE : " + article.getTitre());
				articleService.sendArticle(article);
				break;
				
			default:
				logger.info("TYPE = AUCUNE DES CATEGORIES");
				break;
		}
		
		//re init des objets
		dvd = DVDbuilder.createInstance().build();
		livre = new Livre();
		cd = new CD();
		article = new Article();
		articles = articleService.findAll();
	}

	private DVD buildDVD() {
		DVD dvdToPersist = DVDbuilder.createInstance()
		.addTitre(article.getTitre())
		.addPrix(article.getPrix())
		.addGenre(article.getGenre())
		.addDateSortie(article.getDateSortie())
		.addRealisateur(dvd.getRealisateur())
		.addDuree(dvd.getDureeMinutes())
		.addDescription(dvd.getDescription())
		.build();
		return dvdToPersist;
	}
	

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<String> getClassesArticle() {
		return classesArticle;
	}

	public void setClassesArticle(List<String> classesArticle) {
		this.classesArticle = classesArticle;
	}

	public DVD getDvd() {
		return dvd;
	}

	public void setDvd(DVD dvd) {
		this.dvd = dvd;
	}

	public CD getCd() {
		return cd;
	}

	public void setCd(CD cd) {
		this.cd = cd;
	}

	public Livre getLivre() {
		return livre;
	}

	public void setLivre(Livre livre) {
		this.livre = livre;
	}
	
}
