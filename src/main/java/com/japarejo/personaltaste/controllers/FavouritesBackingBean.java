package com.japarejo.personaltaste.controllers;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import com.japarejo.personaltaste.model.entities.Artwork;
import com.japarejo.personaltaste.model.repositories.FavouritesRepository;


@ManagedBean(name="FavouritesController")
@SessionScoped
public class FavouritesBackingBean implements Serializable{
	Artwork currentArtwork;
	
	@ManagedProperty(value="#{favouritesRepository}")
	FavouritesRepository repo;
	
	@ManagedProperty(value="#{LoginController}")
	LoginBackingBean login;
	
	public String create() {
		currentArtwork=new Artwork();
		return "favouritesform";
	}
	
	public String save() {
		if(login.getCurrentUser()!=null) {
			repo.addFavorito(login.getCurrentUser().getUsername(), currentArtwork);
			currentArtwork=null;
			return "favourites";
		}else
			return "login";
	}
	
	public void delete(String name) {		
		boolean result=login.currentUser.getFavoritos().removeIf(a -> a.getName().contentEquals(name));
		if(result) {
			FacesMessage msg = new FacesMessage("Artwork removed", name);
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}else {
			FacesMessage msg = new FacesMessage("Artwork not found", name);
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public void onRowEdit(RowEditEvent<Artwork> event) {
        FacesMessage msg = new FacesMessage("Product Edited", String.valueOf(event.getObject().getName()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent<Artwork> event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", String.valueOf(event.getObject().getName()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public List<String> completeArtworkTypeName(String query) {
    	return repo.findAllArtworkTypes().stream()
    				.map(a -> a.getName())
    				.collect(Collectors.toList());
    }

	
	public Artwork getCurrentArtwork() {
		return currentArtwork;
	}
	
	public void setCurrentArtwork(Artwork currentArtwork) {
		this.currentArtwork = currentArtwork;
	}

	public FavouritesRepository getRepo() {
		return repo;
	}

	public void setRepo(FavouritesRepository repo) {
		this.repo = repo;
	}

	public LoginBackingBean getLogin() {
		return login;
	}

	public void setLogin(LoginBackingBean login) {
		this.login = login;
	}

	
}
