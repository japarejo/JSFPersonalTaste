package com.japarejo.personaltaste.model.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.japarejo.personaltaste.model.entities.ArtworkType;
import com.japarejo.personaltaste.services.ArtworkService;


@Component("ArtworkTypeConverter")
public class ArtworkTypeConverter implements Converter {

	@Autowired
	ArtworkService service;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		ArtworkType result=service.findArtworkTypeByName(value);				
		if(result==null) {
			new ArtworkType();
			result.setName(value);
		}
		return result;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value!=null && value instanceof ArtworkType)
			return ((ArtworkType)value).getName();
		else
			return "";
	}

}
