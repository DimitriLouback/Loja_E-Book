package br.edu.iff.bsi.LojaEBook.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class Colecao_E_Book extends Produto {

	private String serie;
	private int qtdVolumes;
	
	@OneToMany
	@JoinColumn(name="id_colecao_e_book")
	private List<E_Book> e_book;
	
	public Colecao_E_Book(double preco, String serie, int qtdVolumes) {
		super(preco);
		this.serie = serie;
		this.qtdVolumes = qtdVolumes;
	}

	public String getSerie() {
		return serie;
	}

	public int getQtdVolumes() {
		return qtdVolumes;
	}
	
	
	
}
