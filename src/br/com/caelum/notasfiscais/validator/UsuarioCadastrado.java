package br.com.caelum.notasfiscais.validator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("usuarioCadastrado")
public class UsuarioCadastrado implements Validator {

	@Override
	public void validate(FacesContext fCtx, UIComponent component, Object value)
			throws ValidatorException {
		// TODO Auto-generated method stub

	}

}
