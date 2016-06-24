package fr.treeptik.amazoneejb.pojo;

//import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
//@DiscriminatorValue("livre")
public class Livre extends Article {

	private static final long serialVersionUID = 1L;
	
	private String auteur;
	private String isbn;
	private String editeur;
	private Integer nbPages;

	@Override
	public String toString() {
		return "Livre [auteur=" + auteur + ", isbn=" + isbn + ", editeur=" + editeur + ", nbPages=" + nbPages + "]";
	}

	public Integer getNbPages() {
		return nbPages;
	}

	public void setNbPages(Integer nbPages) {
		this.nbPages = nbPages;
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getEditeur() {
		return editeur;
	}

	public void setEditeur(String editeur) {
		this.editeur = editeur;
	}

}
