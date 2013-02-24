package br.com.caelum.notasfiscais.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

import org.jboss.seam.faces.validation.InputField;

@FacesValidator("nomeEDescricao")
public class ValidadorNomeEDescricao implements Validator {

	@Inject	@InputField
	private String nome;

	@Inject	@InputField
	private String descricao;

	@Override
	public void validate(FacesContext fCtx, UIComponent component, Object value)
			throws ValidatorException {

		if (nome != null && descricao != null
				&& nome.equalsIgnoreCase(descricao)) {
			throw new ValidatorException(new FacesMessage(
					"Nome e Descrição do produto não podem ser iguais"));
		}
	}

}
