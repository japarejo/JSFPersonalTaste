package com.japarejo.personaltaste.model.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="geeks")
public class Geek extends BaseEntity implements Serializable{
	
	private static final long serialVersionUID = 5088612991452040618L;
	
	@OneToOne
	private User user;
	
	private String email;
	
	@ManyToMany(fetch = FetchType.EAGER)
	Set<Artwork> favoritos;
	
	public Geek() {
		Authorities role=new Authorities();
		role.authority="Geek";
		this.user=new User();		
		this.user.getAuthorities().add(role);
		this.favoritos=new HashSet();
	}	
	
	public User getUser() {
		return user;
	}
	
	public String getUsername() {
		return user.username;
	}
	public void setUsername(String username) {
		this.user.setUsername(username);
	}
	public String getPassword() {
		return this.user.getPassword();
	}
	public void setPassword(String password) {
		this.user.setPassword(password);
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Set<Artwork> getFavoritos() {
		return favoritos;
	}
	
	public void setFavoritos(Set<Artwork> favoritos) {
		this.favoritos = favoritos;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());		
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
		Geek other = (Geek) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;		
		return true;
	}
	
	
}
