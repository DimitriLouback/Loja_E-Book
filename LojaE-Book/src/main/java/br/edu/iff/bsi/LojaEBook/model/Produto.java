package br.edu.iff.bsi.LojaEBook.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public abstract class Produto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private double preco;

	@ManyToMany(mappedBy="produto")
	private List<Compra> compra;
	
	public Produto(double preco) {
		super();
		this.preco = preco;
	}

	public Long getId() {
		return id;
	}

	public double getPreco() {
		return preco;
	}
	
	
}
