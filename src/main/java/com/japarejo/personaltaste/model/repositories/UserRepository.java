package com.japarejo.personaltaste.model.repositories;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import com.japarejo.personaltaste.model.entities.Artwork;
import com.japarejo.personaltaste.model.entities.ArtworkType;
import com.japarejo.personaltaste.model.entities.Geek;


@Service
@ApplicationScope
public class UserRepository implements Serializable {
	Map<String,Geek> users; 

	public UserRepository(){
		users=new HashMap<String,Geek>();
		init();
	}
	
	public Collection<Geek> findAllUsers(){
		return users.values();
	}
	
	private void init() {
		Geek japarejo=new Geek();
		japarejo.setUsername("japarejo");
		japarejo.setPassword("super-secret");
		addUser(japarejo);
		
		ArtworkType VGtype=new ArtworkType();
		VGtype.setName("Video Game");
		Artwork botw=new Artwork();
		botw.setName("Zelda Breath of the Wild");
		botw.setType(VGtype);
		botw.setYear(2017);
		botw.setSponsor("Nintendo");
		
		ArtworkType filmType=new ArtworkType();
		filmType.setName("Film");
		Artwork br=new Artwork();
		br.setName("Blade Runner");
		br.setType(filmType);
		br.setYear(1982);
		br.setSponsor("Warner Bros");
		
		japarejo.getFavoritos().add(botw);
		japarejo.getFavoritos().add(br);
		
	}
	
	public boolean addUser(Geek user) {
		boolean result=true;
		if(!existsUser(user.getUsername()))
			users.put(user.getUsername(),user);
		else
			result=false;
		return result;
	}

	public boolean existsUser(String username) {
		return users.containsKey(username);
	}

	public Geek findUser(String formUserName) {
		return users.get(formUserName);
	}
	
	
	
}
