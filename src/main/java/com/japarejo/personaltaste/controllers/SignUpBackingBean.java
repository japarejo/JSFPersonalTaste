package com.japarejo.personaltaste.controllers;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;

import com.japarejo.personaltaste.model.entities.Geek;
import com.japarejo.personaltaste.model.repositories.UserRepository;

@Controller("SignUpController")
@SessionScope
public class SignUpBackingBean {
	String formUsername;
	String formEmail;
	String formPassword;
	String formPasswordRepeat;

	@Autowired
	UserRepository userRepository;

	@Autowired
	LoginBackingBean loginBackingBean;

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

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
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

			if (!userRepository.existsUser(formUsername)) {
				Geek user = new Geek();
				user.setUsername(formUsername);
				user.setPassword(formPassword);
				user.setEmail(formEmail);
				userRepository.addUser(user);
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
}
