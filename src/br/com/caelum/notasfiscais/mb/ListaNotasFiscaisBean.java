package br.com.caelum.notasfiscais.mb;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;

import br.com.caelum.notasfiscais.dao.DAO;
import br.com.caelum.notasfiscais.modelo.NotaFiscal;

@Named @ViewScoped
public class ListaNotasFiscaisBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private DAO<NotaFiscal> dao;
	
	@Inject
	private LazyDataModel<NotaFiscal> dataModel;
	
	@PostConstruct
	public void init() {
		this.dataModel.setRowCount(dao.contaTodos());
		this.dataModel.setPageSize(5);
	}

	public LazyDataModel<NotaFiscal> getDataModel() {
		return dataModel;
	}
}
