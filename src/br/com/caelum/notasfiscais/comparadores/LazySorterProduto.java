package br.com.caelum.notasfiscais.comparadores;

import java.util.Comparator;

import org.primefaces.model.SortOrder;

import br.com.caelum.notasfiscais.modelo.Produto;

public class LazySorterProduto implements Comparator<Produto> {
	
	private String campoOrdenacao;
	private SortOrder sentidoOrdenacao;

	public LazySorterProduto(String campoOrdenacao, SortOrder sentidoOrdenacao) {
		this.campoOrdenacao = campoOrdenacao;
		this.sentidoOrdenacao = sentidoOrdenacao;
	}
	
	@Override
	public int compare(Produto p1, Produto p2) {
		try {
			Object valor1 = Produto.class.getField(this.campoOrdenacao).get(p1);
			Object valor2 = Produto.class.getField(this.campoOrdenacao).get(p2);

			int resultado = ((Comparable) valor1).compareTo(valor2);

			return SortOrder.ASCENDING.equals(sentidoOrdenacao) ? resultado : -1 * resultado;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
