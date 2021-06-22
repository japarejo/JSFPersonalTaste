package com.japarejo.personaltaste.model.repositories;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import com.japarejo.personaltaste.model.entities.Artwork;
import com.japarejo.personaltaste.model.entities.ArtworkType;

@ManagedBean(name="userRepository")
@ApplicationScoped
public class FavouritesRepository {
	
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
		return this.userRepository.findAllUsers().stream()
						.map(u -> u.getFavoritos())
						.flatMap(Collection::stream)
						.map(a -> a.getType())
						.distinct()
						.collect(Collectors.toList());
						
	}
	
}
