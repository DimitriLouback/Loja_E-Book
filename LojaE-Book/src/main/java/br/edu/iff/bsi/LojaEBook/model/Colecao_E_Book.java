package br.edu.iff.bsi.LojaEBook.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class Colecao_E_Book extends Produto {

	@Column(unique=true, length = 60)
	private String serie;
	private int qtdVolumes;
	
	@OneToMany
	@JoinColumn(name="id_colecao_e_book")
	private List<E_Book> e_book;
	
	public Colecao_E_Book(double preco, String serie) {
		super(preco);
		this.serie = serie;
		e_book = new ArrayList();
		this.qtdVolumes = e_book.size();
	}

	private Colecao_E_Book() {}
	
	public String getSerie() {
		return serie;
	}

	public int getQtdVolumes() {
		return qtdVolumes;
	}
	
	public void adicionarEBook(E_Book e_book) {
		this.e_book.add(e_book);
		this.qtdVolumes++;
	}
	
	public void removerEBook(E_Book e_book) {
		this.e_book.remove(e_book);
		this.qtdVolumes--;
	}
	
}
