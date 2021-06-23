package com.japarejo.personaltaste.controllers;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;

import com.japarejo.personaltaste.model.entities.Geek;
import com.japarejo.personaltaste.model.repositories.UserRepository;

@Controller("LoginController")
@SessionScope
public class LoginBackingBean {
	Geek currentUser;
	
	String formUserName;
	String formPassword;
	
	
	@Autowired
	UserRepository userRepository;
	
	public String getFormUserName() {
		return formUserName;
	}

	public void setFormUserName(String formUserName) {
		this.formUserName = formUserName;
	}

	public String getFormPassword() {
		return formPassword;
	}

	public void setFormPassword(String formPassword) {
		this.formPassword = formPassword;
	}

	public Geek getCurrentUser() {
		return currentUser;
	}
	
	public void setCurrentUser(Geek currentUser) {
		this.currentUser = currentUser;
	}
	
	public UserRepository getUserRepository() {
		return userRepository;
	}
	
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	
	
	
	public String doLogin() {
		String result="favourites";
		if(userRepository.existsUser(formUserName))
		{
			Geek user=userRepository.findUser(formUserName);
			if(user!=null && user.getPassword().contentEquals(formPassword)) {
				currentUser=user;
				clear();
			}else {				
				FacesMessage msg=new FacesMessage("Password Invalid");
				FacesContext.getCurrentInstance().addMessage(null, msg);
				result="login";
			}			
		}else {
			FacesMessage msg=new FacesMessage("User not found!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			result="login";
		}
		return result;	
	}
	
	public String doLogout() {
		this.currentUser=null;
		return "index";
	}
	
	private void clear() {
		formPassword="";
		formUserName="";
	}	
	
}
