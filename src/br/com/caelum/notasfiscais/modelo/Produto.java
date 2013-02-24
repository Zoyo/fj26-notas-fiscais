package br.com.caelum.notasfiscais.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Produto implements Cloneable, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue
	private Long id;

	@NotEmpty(message="Nome do produto deve ser preenchido")
	public String nome;

	private String descricao;

	private Double preco;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	@Override
	public Produto clone() {
		Produto copia = new Produto();
		copia.setId(this.getId());
		copia.setNome(this.getNome());
		copia.setDescricao(this.getDescricao());
		copia.setPreco(this.getPreco());
		
		return copia;
	}
}
