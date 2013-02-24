package br.com.caelum.notasfiscais.mb;

import java.io.Serializable;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.notasfiscais.anotations.Transactional;
import br.com.caelum.notasfiscais.dao.DAO;
import br.com.caelum.notasfiscais.modelo.Item;
import br.com.caelum.notasfiscais.modelo.NotaFiscal;
import br.com.caelum.notasfiscais.modelo.Produto;

@Named
@ConversationScoped
public class NotaFiscalBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private NotaFiscal notaFiscal = new NotaFiscal();
	
	private Item item = new Item();
	private Long idProduto;
	
	@Inject
	private DAO<NotaFiscal> daoNotaFiscal;
	
	@Inject
	private DAO<Produto> daoProduto;

	@Inject
	private Conversation conversation;
	
	@Transactional
	public String gravar() {
		daoNotaFiscal.adiciona(notaFiscal);
		conversation.end();
		notaFiscal = new NotaFiscal();
		
		return "notafiscal?faces-redirect=true";
	}
	
	@Transactional
	public void guardaItem() {
		Produto produto = daoProduto.buscaPorId(idProduto);
		item.setProduto(produto);
		item.setValorUnitario(produto.getPreco());
		
		notaFiscal.getItens().add(item);
		item.setNotaFiscal(notaFiscal);
		
		item = new Item();
	}

	public String avancar() {
		if(conversation.isTransient()) {
			conversation.begin();
		}
		return "itens?faces-redirect=true";
	}
	
	public String voltar() {
		return "notafiscal?faces-redirect=true";
	}
	
	public NotaFiscal getNotaFiscal() {
		return notaFiscal;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}
}