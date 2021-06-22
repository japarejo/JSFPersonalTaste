package com.japarejo.personaltaste.controllers;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

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
