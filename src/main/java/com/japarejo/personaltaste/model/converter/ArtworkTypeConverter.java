package com.japarejo.personaltaste.model.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.japarejo.personaltaste.model.entities.ArtworkType;
import com.japarejo.personaltaste.model.repositories.FavouritesRepository;

@Service("ArtworkTypeConverter")
//@FacesConverter("ArtworkTypeConverter")
public class ArtworkTypeConverter implements Converter {

	@Autowired
	FavouritesRepository favRepo;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		ArtworkType result=new ArtworkType();
		result.setName(value);
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
