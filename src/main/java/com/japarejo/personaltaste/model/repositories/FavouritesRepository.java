package com.japarejo.personaltaste.model.repositories;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import com.japarejo.personaltaste.model.entities.Artwork;
import com.japarejo.personaltaste.model.entities.ArtworkType;

@ManagedBean(name="favouritesRepository")
@ApplicationScoped
public class FavouritesRepository implements Serializable{
	
	
	@ManagedProperty(value="#{userRepository}")
	UserRepository userRepository;
	
	
	public Collection<Artwork> getFavoritos(String username){
		Collection<Artwork> result=null;
		if(userRepository.existsUser(username)) {
			result=userRepository.findUser(username).getFavoritos();
		}else
			result=Collections.emptyList();
		return result;
	}
	
	public Collection<Artwork> getFavoritos(String username, final String artworkType){
		return getFavoritos(username).stream()
				.filter(a -> 
					a.getType().getName().equalsIgnoreCase(artworkType))
				.collect(Collectors.toList());
	}
	
	public boolean addFavorito(String username, Artwork work) {
		boolean result=true;
		if(userRepository.existsUser(username))
			getFavoritos(username).add(work);
		else
			result=false;
		return result;
	}
	
	
	public UserRepository getUserRepository() {
		return userRepository;
	}
	
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public List<ArtworkType> findAllArtworkTypes(){
		return findAllArtworks().stream()
						.map(a -> a.getType())
						.distinct()
						.collect(Collectors.toList());
						
	}
	
	public List<Artwork> findAllArtworks(){
		return this.userRepository.findAllUsers().stream()
				.map(u -> u.getFavoritos())
				.flatMap(Collection::stream)				
				.distinct()
				.collect(Collectors.toList());
	}
	
	public List<ArtworkType> getArtworkTypes(){
		return findAllArtworkTypes();
	}
	
	public Artwork findArtworkByTitle(String title)
	{
		Artwork result=null;
		List<Artwork> found=findAllArtworks().stream()
								.filter(a -> a.getName().equals(title))
								.collect(Collectors.toList());
		if(!found.isEmpty())
			result=found.get(0);
		return result;
	}
	
	public List<String> getUserNames(){
		return userRepository.getUseNames();
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userRepository == null) ? 0 : userRepository.hashCode());
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
		FavouritesRepository other = (FavouritesRepository) obj;
		if (userRepository == null) {
			if (other.userRepository != null)
				return false;
		} else if (!userRepository.equals(other.userRepository))
			return false;
		return true;
	}
		


	/**
	 * 
	 */
	private static final long serialVersionUID = 4194416846903351018L;

	
	
}
