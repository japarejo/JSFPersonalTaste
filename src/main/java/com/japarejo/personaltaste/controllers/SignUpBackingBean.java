package com.japarejo.personaltaste.controllers;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.japarejo.personaltaste.model.entities.User;
import com.japarejo.personaltaste.model.repositories.UserRepository;

@ManagedBean(name = "SignUpController")
@SessionScoped
public class SignUpBackingBean {
	String formUsername;
	String formEmail;
	String formPassword;
	String formPasswordRepeat;

	@ManagedProperty(value = "#{userRepository}")
	UserRepository userRepository;

	@ManagedProperty(value = "#{LoginController}")
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
				User user = new User();
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
