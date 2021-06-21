package com.japarejo.personaltaste.model.repositories;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.japarejo.personaltaste.model.entities.User;

public class UserRepository implements Serializable {
	Map<String,User> users;

	public UserRepository(){
		users=new HashMap<String,User>();
		init();
	}
	
	public Collection<User> findAllUsers(){
		return users.values();
	}
	
	private void init() {
		User japarejo=new User();
		japarejo.setUsername("japarejo");
		japarejo.setPassword("super-secret");
		addUser(japarejo);
	}
	
	public boolean addUser(User user) {
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
	
	
	
}
