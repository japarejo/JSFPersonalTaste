package com.japarejo.personaltaste.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.japarejo.personaltaste.model.entities.Artwork;
import com.japarejo.personaltaste.model.entities.Geek;
import com.japarejo.personaltaste.repositories.UserRepository;
import com.japarejo.personaltaste.repositories.ArtworkRepository;
import com.japarejo.personaltaste.repositories.GeekRepository;

@Service
public class UserService {

	@Autowired
	GeekRepository geekRepo;
	@Autowired
	UserRepository userRepo;

	@Autowired	
	ArtworkRepository artworkRepo;
	
	public Geek getCurrentUser() {
		Geek result=null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    String currentUserName = authentication.getName();		    
		    result=geekRepo.findByUsername(currentUserName);
		}
		return result;
	}

	public void addArtworkToCurrentUser(Artwork currentArtwork) {
		Geek geek=getCurrentUser();
		artworkRepo.save(currentArtwork);
		geek.getFavoritos().add(currentArtwork);
		geekRepo.save(geek);		
	}

	public boolean removeFavouriteArworkFromCurrentUser(String name) {
		boolean result=false;
		Geek user=getCurrentUser();
		if(user!=null) {
			user.getFavoritos().removeIf(a -> a.getName().equals(name));
			geekRepo.save(user);
		}
		return result;
	}

	public boolean existsUser(String formUserName) {
		return findUser(formUserName)!=null;
	}

	public Geek findUser(String username) {
		return geekRepo.findByUsername(username);
	}

	public List<String> getUserNames() {
		return geekRepo.findAll().stream().map(u -> u.getUsername()).collect(Collectors.toList());		
	}

	public void saveUser(Geek user) {
		userRepo.save(user.getUser());
		geekRepo.save(user);		
	}


}
