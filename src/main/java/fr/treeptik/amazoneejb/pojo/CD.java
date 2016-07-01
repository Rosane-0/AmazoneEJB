package fr.treeptik.amazoneejb.pojo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("cd")
public class CD extends Article  {

	private static final long serialVersionUID = 1L;
	
	private String artiste;
	private String compositeur;
	private Integer nbPistes;
	
	
	@Override
	public String toString() {
		return "CD [artiste=" + artiste + ", compositeur=" + compositeur + ", nbPistes=" + nbPistes + "]";
	}
	public String getArtiste() {
		return artiste;
	}
	public void setArtiste(String artiste) {
		this.artiste = artiste;
	}
	public String getCompositeur() {
		return compositeur;
	}
	public void setCompositeur(String compositeur) {
		this.compositeur = compositeur;
	}
	public Integer getNbPistes() {
		return nbPistes;
	}
	public void setNbPistes(Integer nbPistes) {
		this.nbPistes = nbPistes;
	}
	
	


}
