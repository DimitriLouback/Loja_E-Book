package br.edu.iff.bsi.LojaEBook.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Positive;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Produto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Positive(message="Tem que ser maior que 0")
	private double preco;

	@ManyToMany(mappedBy="produto")
	private List<Compra> compra;
	
	public Produto(double preco) {
		super();
		this.preco = preco;
	}
	

	public Produto() {}
	
	public Long getId() {
		return id;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	
}
