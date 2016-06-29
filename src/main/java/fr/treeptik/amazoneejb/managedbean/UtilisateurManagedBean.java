package fr.treeptik.amazoneejb.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.jboss.logging.Logger;
import org.primefaces.event.CellEditEvent;

import fr.treeptik.amazoneejb.pojo.Utilisateur;
import fr.treeptik.amazoneejb.service.UtilisateurService;

@ManagedBean
@RequestScoped
public class UtilisateurManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	public UtilisateurService utilisateurService;

	private Utilisateur utilisateur = new Utilisateur();

	private List<Utilisateur> utilisateurs = new ArrayList<>();

	private String username;

	private Logger logger = Logger.getLogger(UtilisateurManagedBean.class);

	@PostConstruct
	public void init() {
		username = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser(); //return session login
	}

	public String addUtilisateur(){
		utilisateur = utilisateurService.add(utilisateur);
		utilisateurService.addRoleUserDefault(utilisateur);
		utilisateur = new Utilisateur();
		return "utilisateur-listDynamic";
	}

	//Avec AJAX, pas de return
	public void remove(){
		utilisateurService.remove(utilisateur);
	}

	public void updatePF(){
		utilisateurService.update(utilisateur);
	}

	public void onCellEdit(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		if(newValue != null && !newValue.equals(oldValue)) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		int index = event.getRowIndex();
		utilisateur = utilisateurs.get(index);
		updatePF();
	}


	public void addRoleAdmin() {

	}

	public void removeRoleAdmin() {

	}

	public void viewCommandes() {

	}

	public void findUser() {

	}


	//GET-SET


	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public List<Utilisateur> getUtilisateurs() {
		utilisateurs = utilisateurService.findAll();
		return utilisateurs;
	}

	public void setUtilisateurs(List<Utilisateur> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}



}
