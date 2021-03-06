package br.com.caelum.notasfiscais.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;
import javax.persistence.EntityManager;

public class DAOFactory implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager em;
	
	@Produces
	public DAO createDAO(InjectionPoint injectionPoint) {
		ParameterizedType type = (ParameterizedType) injectionPoint.getType();
		Class classe = (Class) type.getActualTypeArguments()[0];

		return new DAO(classe, em);
	}
}
