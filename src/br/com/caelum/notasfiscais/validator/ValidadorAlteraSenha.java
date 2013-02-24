package br.com.caelum.notasfiscais.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

import org.jboss.seam.faces.validation.InputField;

@FacesValidator("validaAlteraSenha")
public class ValidadorAlteraSenha implements Validator {
	
	@Inject @InputField
	private String pwUsuario; // Senha que est� gravada no banco

	@Inject	@InputField
	private String pwAtual; // Senha que foi informada no formulario

	@Inject	@InputField
	private String pwNova;

	@Inject	@InputField
	private String pwConfirma;

	@Override
	public void validate(FacesContext fCtx, UIComponent componenrt, Object value)
			throws ValidatorException {
		if (!pwUsuario.isEmpty() && !pwAtual.isEmpty() && !pwNova.isEmpty()
				&& !pwConfirma.isEmpty()) {
			if (!pwUsuario.equals(pwAtual) || !pwNova.equals(pwConfirma)) {
				throw new ValidatorException(new FacesMessage(
						"Dados inconsistentes"));
			}
		}
	}
}
