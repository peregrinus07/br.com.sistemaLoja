package br.com.sistemaLoja.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("upperCaseConverter")
public class UpperCaseConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
 
		if(arg2 == null) {
			
			return null;			
		} else {
			return arg2.toUpperCase();
		}	
		
 
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		
		if(arg2 == null){
			
			 return "";
			
		} else {
			
			return arg2.toString();
			
		}
		
		
	}
	
	

}
