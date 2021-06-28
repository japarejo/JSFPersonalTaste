package com.japarejo.personaltaste.controllers;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;

import com.japarejo.personaltaste.model.entities.Artwork;
import com.japarejo.personaltaste.services.ArtworkService;
import com.japarejo.personaltaste.services.UserService;

@Controller("FavouritesController")
@SessionScope
public class FavouritesBackingBean implements Serializable{
	Artwork currentArtwork;
	
	@Autowired
	ArtworkService artworskService;
	
	@Autowired
	UserService usersService;
	
	public FavouritesBackingBean(){
		this.currentArtwork=new Artwork();
	}
	
	public String create() {
		currentArtwork=new Artwork();
		return "favouritesform";
	}
	
	public Collection<Artwork> getCurrentUserFavourites(){
		return usersService.getCurrentUser().getFavoritos();
	}
	
	public String save() {
		if(usersService.getCurrentUser()!=null) {
			usersService.addArtworkToCurrentUser(currentArtwork);
			currentArtwork=null;
			return "favourites";
		}else
			return "login";
	}
	
	public void delete(String name) {		
		boolean result=usersService.removeFavouriteArworkFromCurrentUser(name);
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
    	return artworskService.getAllArtworkTypes().stream()
    				.map(a -> a.getName())
    				.collect(Collectors.toList());
    }
    
    public List<String> completeAllArtworksName(String query){
    	return artworskService.getAll().stream()
    			.map(a -> a.getName())
    			.collect(Collectors.toList());
    }
    
    public void autocompleteFromTitle() {
    	Artwork found=artworskService.getArtworkByTitle(currentArtwork.getName());
    	if(found!=null)
    		currentArtwork=new Artwork(found);    	
    }

	
	public Artwork getCurrentArtwork() {
		return currentArtwork;
	}
	
	public void setCurrentArtwork(Artwork currentArtwork) {
		this.currentArtwork = currentArtwork;
	}

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7097642535152384539L;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((currentArtwork == null) ? 0 : currentArtwork.hashCode());
		result = prime * result + ((usersService == null) ? 0 : usersService.hashCode());
		result = prime * result + ((artworskService == null) ? 0 : artworskService.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FavouritesBackingBean other = (FavouritesBackingBean) obj;
		if (currentArtwork == null) {
			if (other.currentArtwork != null)
				return false;
		} else if (!currentArtwork.equals(other.currentArtwork))
			return false;
		if (usersService == null) {
			if (other.usersService != null)
				return false;
		} else if (!usersService.equals(other.usersService))
			return false;
		if (artworskService == null) {
			if (other.artworskService != null)
				return false;
		} else if (!artworskService.equals(other.artworskService))
			return false;
		return true;
	}
}
