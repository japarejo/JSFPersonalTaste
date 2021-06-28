package com.japarejo.personaltaste.services;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.japarejo.personaltaste.model.entities.Artwork;
import com.japarejo.personaltaste.model.entities.ArtworkType;
import com.japarejo.personaltaste.repositories.ArtworkRepository;

@Service
public class ArtworkService {

	@Autowired
	ArtworkRepository repo;
	@Autowired
	UserService userService;
	
	public Set<ArtworkType> getAllArtworkTypes(){
		
		return repo.findAllArtworkTypes();
	}
	
	public List<Artwork> getAll(){
		return repo.findAll();
	}
	
	public Artwork getArtworkByTitle(String title) {
		return repo.findByName(title);
	}
	
	public ArtworkType findArtworkTypeByName(String value) {
		return repo.findArtworkTypeByName(value);
	}

	public Set<Artwork> getFavoritos(String username) {
		return userService.findUser(username).getFavoritos();
	}
	
}
