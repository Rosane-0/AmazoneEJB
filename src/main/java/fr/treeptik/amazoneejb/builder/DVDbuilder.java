package fr.treeptik.amazoneejb.builder;

import java.io.Serializable;
import java.util.Date;

import fr.treeptik.amazoneejb.pojo.DVD;

public class DVDbuilder implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private DVD dvd;
	
	public static DVDbuilder createInstance (){
		DVDbuilder builder = new DVDbuilder();
		builder.dvd = new DVD();
		return builder;
	}
	
	public DVDbuilder addTitre(String titre) {
		this.dvd.setTitre(titre);
		return this;
	}
	
	public DVDbuilder addPrix(Double prix) {
		this.dvd.setPrix(prix);
		return this;
	}
	
	public DVDbuilder addGenre(String genre) {
		this.dvd.setGenre(genre);
		return this;
	}
	
	public DVDbuilder addDateSortie(Date dateSortie) {
		this.dvd.setDateSortie(dateSortie);
		return this;
	}
	
	public DVDbuilder addRealisateur(String realisateur) {
		this.dvd.setRealisateur(realisateur);
		return this;
	}
	
	public DVDbuilder addDuree(Double dureeMinutes) {
		this.dvd.setDureeMinutes(dureeMinutes);
		return this;
	}
	
	public DVDbuilder addDescription(String description) {
		this.dvd.setDescription(description);
		return this;
	}
	
	public DVD build(){
		return this.dvd;
	}

}
