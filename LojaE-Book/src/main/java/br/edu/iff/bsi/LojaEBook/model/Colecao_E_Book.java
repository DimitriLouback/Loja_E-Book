package br.edu.iff.bsi.LojaEBook.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

@Entity
public class Colecao_E_Book extends Produto {

	@NotBlank(message="Não pode ser em branco ou nulo")
	@Size(min=1,max=60,message="Tem que ter entre 1 e 60 caractéres")
	@Column(unique=true, length = 60)
	private String serie = " ";
	@PositiveOrZero(message="Tem que ser maior ou igual a 0")
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

	public Colecao_E_Book() {}
	
	public void setSerie(String serie) {
		this.serie = serie;
	}

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
