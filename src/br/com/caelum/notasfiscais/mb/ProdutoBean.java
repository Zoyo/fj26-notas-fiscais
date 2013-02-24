package br.com.caelum.notasfiscais.mb;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;

import br.com.caelum.notasfiscais.anotations.EmailComercial;
import br.com.caelum.notasfiscais.anotations.EmailFinanceiro;
import br.com.caelum.notasfiscais.anotations.Transactional;
import br.com.caelum.notasfiscais.dao.DAO;
import br.com.caelum.notasfiscais.modelo.Produto;

@Named @ViewScoped
public class ProdutoBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Produto produto = new Produto();
	private List<Produto> produtos;
	private Double somaTodos;
	
	@Inject
	private LazyDataModel<Produto> dataModel;

	@Inject
	private DAO<Produto> dao;
	
	@Inject @EmailComercial
	private String emailComercial;
	
	@Inject @EmailFinanceiro
	private String emailFinanceiro;
	
	@Inject @Any
	private Instance<String> todosEmails;

	// variavel para representar o objeto selecionado na tabela no atributo
	// selection do datatable
	private Produto produtoSelecionado;

	@PostConstruct
	public void init() {
//		this.dataModel.setRowCount(dao.contaTodos());
		this.dataModel.setPageSize(5);
	}

	@Transactional
	public void grava() {
		System.out.println("Avisando dept. comercial: " + emailComercial);
		System.out.println("Avisando dept. comercial: " + emailFinanceiro);
		
		if (getProduto().getId() == null) {
			dao.adiciona(getProduto());
		} else {
			dao.atualiza(getProduto());
		}

		this.produtos = dao.listaTodos();
		this.somaTodos = getSomaLista();

		limpaFormularioProduto();
	}

	public Produto getProduto() {
		return this.produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto.clone();
	}

	@Transactional
	public List<Produto> getProdutos() {
		if (produtos == null) {
			System.out.println("Carrengando produtos...");

			produtos = dao.listaTodos();

			somaTodos = getSomaLista();
		}
		return produtos;
	}

	private Double getSomaLista() {
		Double total = 0.0;

		if (this.produtos != null) {
			for (Produto p : this.produtos) {
				total += p.getPreco();
			}
		}

		return total;
	}

	private void limpaFormularioProduto() {
		this.setProduto(new Produto());
	}

	public Double getSomaTodos() {
		return somaTodos;
	}

	// public void remove(Produto p) {
	@Transactional
	public void remove() {
		for (String s : todosEmails) {
			System.out.println("Avisando remoção de produto para: " + s);
		}
		
		dao.remove(produtoSelecionado);
		this.produtos = dao.listaTodos();
		this.somaTodos = getSomaLista();
	}

	public void alteraProduto() {
		// este método foi criado para tornar possivel a edição do produto
		// através do menu de contexto
		this.setProduto(produtoSelecionado);
	}

	public void cancelaEdicaoProduto() {		
		this.produto = new Produto();
	}

	public LazyDataModel<Produto> getDataModel() {
		return dataModel;
	}

	public Produto getProdutoSelecionado() {
		return produtoSelecionado;
	}

	public void setProdutoSelecionado(Produto produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
	}
}