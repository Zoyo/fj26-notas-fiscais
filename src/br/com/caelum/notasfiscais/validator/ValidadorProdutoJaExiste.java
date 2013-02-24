package br.com.caelum.notasfiscais.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.faces.validation.InputField;

@FacesValidator("produtoJaExiste")
public class ValidadorProdutoJaExiste implements Validator {
	
	@Inject
	private EntityManager em;
	
	@Inject @InputField
	private String nome;
	
	@Inject @InputField
	private Long id;

	@Override
	public void validate(FacesContext fCtx, UIComponent component, Object value)
			throws ValidatorException {
		
		if (!nome.isEmpty() && id == null) {
			String jpql = "select count(p) from Produto p where p.nome like :nome";
			Query query = em.createQuery(jpql);
			query.setParameter("nome", nome);
			Long count = (Long) query.getSingleResult();
			if (count != 0) {
				throw new ValidatorException(new FacesMessage(
						"Este produto já está cadastrado"));
			}
		}
	}

}