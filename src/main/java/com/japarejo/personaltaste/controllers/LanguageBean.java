package com.japarejo.personaltaste.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.faces.context.FacesContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;


@Controller(value="language")
@SessionScope
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
}