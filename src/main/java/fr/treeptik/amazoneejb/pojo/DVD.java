package fr.treeptik.amazoneejb.pojo;

//import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
//@DiscriminatorValue("dvd")
public class DVD extends Article {
	
	private static final long serialVersionUID = 1L;
	
	private String realisateur;
	private String description;
	private Double dureeMinutes;
	
	@Override
	public String toString() {
		return "DVD [realisateur=" + realisateur + ", description=" + description + ", dureeMinutes=" + dureeMinutes
				+ "]";
	}
	public String getRealisateur() {
		return realisateur;
	}
	public void setRealisateur(String realisateur) {
		this.realisateur = realisateur;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getDureeMinutes() {
		return dureeMinutes;
	}
	public void setDureeMinutes(Double dureeMinutes) {
		this.dureeMinutes = dureeMinutes;
	}
	
	

}
