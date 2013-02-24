package br.com.caelum.notasfiscais.mb;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.notasfiscais.dao.UsuarioDAO;
import br.com.caelum.notasfiscais.modelo.Usuario;

@Named @RequestScoped
public class AlteraUsuarioBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String senhaAtual;
	private String novaSenha;
	private String confirmaSenha;
	
	@Inject
	private UsuarioDAO dao;
	
	public String getSenhaAtual() {
		return senhaAtual;
	}

	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	public String getConfirmaSenha() {
		return confirmaSenha;
	}

	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}

	private void resetaInformacoes() {
		this.setSenhaAtual("");
		this.setNovaSenha("");
		this.setConfirmaSenha("");
	}

	public void alteraSenha(Usuario usuario) {
		if (!senhaAtual.equals(usuario.getSenha())
				|| !novaSenha.equals(confirmaSenha)) {
			System.out.println("Dados inconsistentes");
			
		} else {
			usuario.setSenha(novaSenha);	
			dao.atualiza(usuario);			
		}
		
		resetaInformacoes();
	}
	
	public void calcelaAlteracaoDeSenha() {
		resetaInformacoes();
	}
}
