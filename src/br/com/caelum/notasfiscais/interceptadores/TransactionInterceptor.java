package br.com.caelum.notasfiscais.interceptadores;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

import br.com.caelum.notasfiscais.anotations.Transactional;

@Interceptor @Transactional
public class TransactionInterceptor implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager em;
	
	@AroundInvoke
	public Object intercept(InvocationContext ctx) throws Exception {
		System.out.println("Abrindo Transa��o");
		em.getTransaction().begin();
		
		//Passando para o JSF tratar a requisi��o e pegando o retorno da l�gica
		Object resultado = ctx.proceed();
		
		em.getTransaction().commit();
		
		return resultado;
	}
}
