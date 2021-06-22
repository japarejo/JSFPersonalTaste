package com.japarejo.personaltaste.controllers;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.japarejo.personaltaste.model.entities.User;
import com.japarejo.personaltaste.model.repositories.UserRepository;

@ManagedBean(name="LoginController")
@SessionScoped
public class LoginBackingBean {
	User currentUser;
	
	String formUserName;
	String formPassword;
	
	
	@ManagedProperty(value="#{userRepository}")
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

	public User getCurrentUser() {
		return currentUser;
	}
	
	public void setCurrentUser(User currentUser) {
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
			User user=userRepository.findUser(formUserName);
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
