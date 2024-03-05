package com.japarejo.personaltaste.controllers;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;

import com.japarejo.personaltaste.model.entities.Geek;
import com.japarejo.personaltaste.model.repositories.UserRepository;
import com.japarejo.personaltaste.services.UserService;

@Controller("LoginController")
@SessionScope
public class LoginBackingBean {
	Geek currentUser;
	
	String formUserName;
	String formPassword;
		
	@Autowired
	UserService userService;		
	
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
		formUserName=currentUser.getUsername();
		formPassword=this.currentUser.getPassword();
		doLogin();
	}
		
	
	
	
	public String doLogin() {
		String result="favourites.xhtml";
						
		if(userService.existsUser(formUserName))
		{
			Geek user=userService.findUser(formUserName);
			if(user!=null && user.getPassword().contentEquals(formPassword)) {
				currentUser=user;
				UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(formUserName, formPassword);		
				SecurityContext sc = SecurityContextHolder.getContext();
				sc.setAuthentication(authReq);
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
		return "login";
	}
	
	private void clear() {
		formPassword="";
		formUserName="";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((currentUser == null) ? 0 : currentUser.hashCode());
		result = prime * result + ((formPassword == null) ? 0 : formPassword.hashCode());
		result = prime * result + ((formUserName == null) ? 0 : formUserName.hashCode());
		result = prime * result + ((userService == null) ? 0 : userService.hashCode());
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
		LoginBackingBean other = (LoginBackingBean) obj;
		if (currentUser == null) {
			if (other.currentUser != null)
				return false;
		} else if (!currentUser.equals(other.currentUser))
			return false;
		if (formPassword == null) {
			if (other.formPassword != null)
				return false;
		} else if (!formPassword.equals(other.formPassword))
			return false;
		if (formUserName == null) {
			if (other.formUserName != null)
				return false;
		} else if (!formUserName.equals(other.formUserName))
			return false;
		if (userService == null) {
			if (other.userService != null)
				return false;
		} else if (!userService.equals(other.userService))
			return false;
		return true;
	}	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4806972322119675590L;
	
}
