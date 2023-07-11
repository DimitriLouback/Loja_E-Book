package br.edu.iff.bsi.LojaEBook.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Compra implements Serializable {

private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataHora;
	private int qtdProdutos;
	private double precoFinal;
	
	@ManyToMany
	@JoinTable(name = "associacao_compra_produto",
				joinColumns = @JoinColumn(name = "fk_compra"),
				inverseJoinColumns = @JoinColumn(name = "fk_produto"))
	private List<Produto> produto;
	
	public Compra(Calendar dataHora, int qtdProdutos, double precoFinal) {
		super();
		this.dataHora = dataHora;
		this.qtdProdutos = qtdProdutos;
		this.precoFinal = precoFinal;
	}

	public Long getId() {
		return id;
	}

	public Calendar getDataHora() {
		return dataHora;
	}

	public int getQtdProdutos() {
		return qtdProdutos;
	}

	public double getPrecoFinal() {
		return precoFinal;
	}
	
	
	
	
}
