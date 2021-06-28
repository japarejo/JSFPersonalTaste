package com.japarejo.personaltaste.model.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "artworks")
public class Artwork extends BaseEntity implements Serializable{
	String name;
	Integer year;	
	String sponsor;
	@ManyToOne
	ArtworkType type;
	
	public Artwork() {}
	public Artwork(Artwork a) {
		this.name=a.name;
		this.year=new Integer(a.year.intValue());
		this.sponsor=a.sponsor;
		this.type=a.type;
	}
	
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;		
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((sponsor == null) ? 0 : sponsor.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((year == null) ? 0 : year.hashCode());
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
		Artwork other = (Artwork) obj;		
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (sponsor == null) {
			if (other.sponsor != null)
				return false;
		} else if (!sponsor.equals(other.sponsor))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}
	
	
					
}
