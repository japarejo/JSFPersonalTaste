package com.japarejo.personaltaste.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


@ManagedBean(name="language")
@SessionScoped
public class LanguageBean implements Serializable{				

	private Locale locale;
	
	String language;
	
	List<String> options;
	
	public LanguageBean() {
		this.locale=new Locale("");
		this.language=locale.getLanguage();
		this.options=new ArrayList<String>();
		this.options.add("en");
		this.options.add("es");
	}

    public Locale getLocale() {
        return locale;
    }
    
    public void setLocale(Locale locale) {
		this.locale = locale;
	}

    public String getLanguage() {
		return language;		
	}
    
    public void setLanguage(String language) {
		this.language = language;
		change();
	}
    
    public List<String> getOptions() {
		return options;
	}
    
    public void setOptions(List<String> options) {
		this.options = options;
	}

    public void change() {
        locale = new Locale(language);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(language));
    }
    
    public void change(String idioma) {
    	language=idioma;
        locale = new Locale(language);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(language));
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((language == null) ? 0 : language.hashCode());
		result = prime * result + ((locale == null) ? 0 : locale.hashCode());
		result = prime * result + ((options == null) ? 0 : options.hashCode());
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
		LanguageBean other = (LanguageBean) obj;
		if (language == null) {
			if (other.language != null)
				return false;
		} else if (!language.equals(other.language))
			return false;
		if (locale == null) {
			if (other.locale != null)
				return false;
		} else if (!locale.equals(other.locale))
			return false;
		if (options == null) {
			if (other.options != null)
				return false;
		} else if (!options.equals(other.options))
			return false;
		return true;
	}
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 7040201021521783992L;
}