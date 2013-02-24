package br.com.caelum.notasfiscais.datamodel;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.caelum.notasfiscais.comparadores.LazySorterProduto;
import br.com.caelum.notasfiscais.dao.DAO;
import br.com.caelum.notasfiscais.modelo.Produto;

public class DataModelProduto extends LazyDataModel<Produto> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private DAO<Produto> dao;
	
	@Override
	public List<Produto> load(int inicio, int quantidade,
			String campoOrdenacao, SortOrder sentidoOrdenacao,
			Map<String, String> filtrods) {
		
		List<Produto> lista = dao.listaTodosPaginada(inicio, quantidade);
		
		int size = dao.contaTodos();
		this.setRowCount(size);
//		this.setRowIndex(size);
		
		if (size > quantidade) {
//			este if verifica o tamanho da lista e refaz a paginação para o caso de 
//			excluirmos o ultimo elemento da página e a mesma vir zerada.
			if (lista.size() == 0) {
//				this.setRowIndex(size - quantidade); // coloquei pra teste mas não sei exatamente pra que serve				
				lista = dao.listaTodosPaginada(inicio - quantidade, quantidade);
			}
		}else {
			lista = dao.listaTodos();
		}
		
		if(campoOrdenacao != null) {
			Collections.sort(lista, new LazySorterProduto(campoOrdenacao, sentidoOrdenacao));
		}

		return lista;
	}
}
