package br.com.caelum.notasfiscais.mb;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.caelum.notasfiscais.dao.UsuarioDAO;
import br.com.caelum.notasfiscais.modelo.Usuario;

@Named @RequestScoped
public class LoginBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioDAO dao;
	
	@Inject
	private UsuarioLogado usuarioLogado;
	
	private Usuario usuario = new Usuario();

	public Usuario getUsuario() {
		return usuario;
	}

	public String efetuaLogin() {
		boolean loginValido = dao.existe(this.usuario);

		if (loginValido) {
			this.usuario = dao.getUsuarioLogado();
			this.usuarioLogado.setUsuario(usuario);
			return "produto?faces-redirect=true";
		} else {
			this.usuario = new Usuario();
			return "login?faces-redirect=true";
		}
	}

	public boolean isLogado() {
		return this.usuarioLogado.isLogado();
	}

	public String logout() {
		// este bloco mata a sessão do usuário
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext()
				.getSession(true);
		session.invalidate();

		this.usuarioLogado.setUsuario(null);
		this.usuario = new Usuario();

		return "login?faces-redirect=true";
	}
}
