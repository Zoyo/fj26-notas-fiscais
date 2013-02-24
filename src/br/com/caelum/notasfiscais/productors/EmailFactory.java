package br.com.caelum.notasfiscais.productors;

import javax.enterprise.inject.Produces;

import br.com.caelum.notasfiscais.anotations.EmailComercial;
import br.com.caelum.notasfiscais.anotations.EmailFinanceiro;

public class EmailFactory {

	@Produces @EmailComercial
	private String emailComercial = "comercial@uberdist.com.br";
	
	@Produces @EmailFinanceiro
	private String emailFinanceiro = "financeiro@uberdist.com.br";
}
