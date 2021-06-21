package com.japarejo.personaltaste.model.entities;

import java.io.Serializable;
import java.util.List;

public class Artwork implements Serializable{
	String name;
	Integer year;
	List<String> authors;
	String sponsor;
	ArtworkType type;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public List<String> getAuthors() {
		return authors;
	}
	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}
	public String getSponsor() {
		return sponsor;
	}
	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}
	public ArtworkType getType() {
		return type;
	}
	public void setType(ArtworkType type) {
		this.type = type;
	}
					
}
