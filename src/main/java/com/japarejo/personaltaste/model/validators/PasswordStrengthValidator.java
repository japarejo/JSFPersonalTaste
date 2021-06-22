package com.japarejo.personaltaste.model.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("passwordStrengthValidator")
public class PasswordStrengthValidator implements Validator{

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String password=(String)value;
		if(password.length()<5) {
			FacesMessage message=new FacesMessage("Password too short (minimum 5 characters)");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		boolean containsUppercase=false;
		boolean containsLowercase=false;
		boolean containsDigit=false;
		char ch;
		 for(int i=0;i < password.length();i++) {
		        ch = password.charAt(i);
		        if( Character.isDigit(ch)) {
		             containsDigit= true;
		        } else if (Character.isUpperCase(ch)) {
		            containsUppercase = true;
		        } else if (Character.isLowerCase(ch)) {
		            containsLowercase = true;
		        }
		        if(containsDigit && containsLowercase && containsUppercase)
		            break;
		    }
		if(!containsDigit) {
			FacesMessage message=new FacesMessage("Password should contain at least one digit");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		if(!containsUppercase) {
			FacesMessage message=new FacesMessage("Password should contain at least one uppercase character");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);		
		}
		if(!containsLowercase) {
			FacesMessage message=new FacesMessage("Password should contain at least one lowercase character");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);		
		}
	}

	
}
