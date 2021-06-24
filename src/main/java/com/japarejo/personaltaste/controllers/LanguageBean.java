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
	
	private static final long serialVersionUID = 1L;	
	
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
}