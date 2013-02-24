package br.com.caelum.notasfiscais.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.notasfiscais.modelo.Usuario;

public class UsuarioDAO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private DAO<Usuario> dao;
	
	private Usuario usuarioLogado = new Usuario();
	
	@Inject
	private EntityManager em;
	
	public boolean existe(Usuario usuario) {
		Query query = em.createQuery("from Usuario where login = :login and senha = :senha")
						.setParameter("login", usuario.getLogin())
						.setParameter("senha", usuario.getSenha());

		boolean encontrado = !query.getResultList().isEmpty();
		
		if(encontrado) {
			usuarioLogado = (Usuario) query.getSingleResult();
		}
		
		return encontrado;
	}

	public void adiciona(Usuario t) {
		dao.adiciona(t);
	}

	public void remove(Usuario t) {
		dao.remove(t);
	}

	public void atualiza(Usuario t) {
		dao.atualiza(t);
	}

	public Usuario buscaPorId(Long id) {
		return dao.buscaPorId(id);
	}

	public List<Usuario> listaTodosPaginada(int firstResult, int maxResults) {
		return dao.listaTodosPaginada(firstResult, maxResults);
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}
	
	
}