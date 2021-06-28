package com.japarejo.personaltaste.controllers;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;

import com.japarejo.personaltaste.model.entities.Geek;
import com.japarejo.personaltaste.model.repositories.UserRepository;
import com.japarejo.personaltaste.services.UserService;


@Controller("SignUpController")
@SessionScope
public class SignUpBackingBean {
	String formUsername;
	String formEmail;
	String formPassword;
	String formPasswordRepeat;	

	@Autowired
	LoginBackingBean loginBackingBean;
	
	@Autowired
	UserService usersService;

	public String getFormUsername() {
		return formUsername;
	}

	public void setFormUsername(String formUsername) {
		this.formUsername = formUsername;
	}

	public String getFormEmail() {
		return formEmail;
	}

	public void setFormEmail(String formEmail) {
		this.formEmail = formEmail;
	}

	public String getFormPassword() {
		return formPassword;
	}

	public void setFormPassword(String formPassword) {
		this.formPassword = formPassword;
	}


	public void setLoginBackingBean(LoginBackingBean loginBackingBean) {
		this.loginBackingBean = loginBackingBean;
	}

	public LoginBackingBean getLoginBackingBean() {
		return loginBackingBean;
	}

	public String getFormPasswordRepeat() {
		return formPasswordRepeat;
	}

	public void setFormPasswordRepeat(String formPasswordRepeat) {
		this.formPasswordRepeat = formPasswordRepeat;
	}

	public String doSave() {
		String result = "index";
		if (formPassword.equals(formPasswordRepeat)) {

			if (!usersService.existsUser(formUsername)) {
				Geek user = new Geek();
				user.setUsername(formUsername);
				user.setPassword(formPassword);
				user.setEmail(formEmail);
				usersService.saveUser(user);
				loginBackingBean.setCurrentUser(user);
				clear();
				/*
				 * FacesMessage msg=new FacesMessage("User Created!!!");
				 * FacesContext.getCurrentInstance().addMessage(null, msg);
				 */
				return "index";
			} else {
				FacesMessage msg = new FacesMessage("User name already taken.");
				FacesContext.getCurrentInstance().addMessage(null, msg);
				result = "signup";
			}
		} else {
			FacesMessage msg = new FacesMessage("Passwords do not match!.");
			FacesContext.getCurrentInstance().addMessage("formularioSignUp:passwordRepeat", msg);
			result = "signup";
		}

		return result;
	}

	public void clear() {
		formEmail = "";
		formUsername = "";
		formPassword = "";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((formEmail == null) ? 0 : formEmail.hashCode());
		result = prime * result + ((formPassword == null) ? 0 : formPassword.hashCode());
		result = prime * result + ((formPasswordRepeat == null) ? 0 : formPasswordRepeat.hashCode());
		result = prime * result + ((formUsername == null) ? 0 : formUsername.hashCode());
		result = prime * result + ((loginBackingBean == null) ? 0 : loginBackingBean.hashCode());
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
		SignUpBackingBean other = (SignUpBackingBean) obj;
		if (formEmail == null) {
			if (other.formEmail != null)
				return false;
		} else if (!formEmail.equals(other.formEmail))
			return false;
		if (formPassword == null) {
			if (other.formPassword != null)
				return false;
		} else if (!formPassword.equals(other.formPassword))
			return false;
		if (formPasswordRepeat == null) {
			if (other.formPasswordRepeat != null)
				return false;
		} else if (!formPasswordRepeat.equals(other.formPasswordRepeat))
			return false;
		if (formUsername == null) {
			if (other.formUsername != null)
				return false;
		} else if (!formUsername.equals(other.formUsername))
			return false;
		if (loginBackingBean == null) {
			if (other.loginBackingBean != null)
				return false;
		} else if (!loginBackingBean.equals(other.loginBackingBean))
			return false;		
		return true;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -502758519454403593L;
}
