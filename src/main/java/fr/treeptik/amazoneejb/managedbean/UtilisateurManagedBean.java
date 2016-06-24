package fr.treeptik.amazoneejb.managedbean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.CellEditEvent;

import fr.treeptik.amazoneejb.pojo.Utilisateur;
import fr.treeptik.amazoneejb.service.UtilisateurService;

@ManagedBean
@RequestScoped
public class UtilisateurManagedBean {
	
	@EJB
	public UtilisateurService utilisateurService;
	
	private Utilisateur utilisateur = new Utilisateur();
	
	private List<Utilisateur> utilisateurs = new ArrayList<>();
	
	public String addUtilisateur(){
		utilisateur = utilisateurService.add(utilisateur);
		utilisateurService.addRoleUserDefault(utilisateur);
		utilisateur = new Utilisateur();
		return "utilisateur-list";
	}
	
	public String ajouterNouvelUtilisateur() {
		utilisateur = new Utilisateur();
		return "utilisateur";
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

	
	//GET-SET


	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public List<Utilisateur> getUtilisateurs() {
		//ENLEVER LE IF POUR LA LIST USERS NON DYNAMIQUE
//		if (utilisateurs.isEmpty()) {
			utilisateurs = utilisateurService.findAll();
//		}
		return utilisateurs;
	}

	public void setUtilisateurs(List<Utilisateur> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}


}
