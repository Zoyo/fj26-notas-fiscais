package br.com.caelum.notasfiscais.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.validation.ValidationException;

@FacesValidator("comecaComMaiuscula")
public class ComecaComMaiuscula implements Validator {
	
	public void validate(FacesContext fCtx, UIComponent component, Object value) throws ValidationException {
		String valor = value.toString();
		if (!valor.matches("[A-Z].*")) {
			throw new ValidatorException(new FacesMessage(component.getId()
					+ " deve começar com maiuscula"));
		}
	}
}
