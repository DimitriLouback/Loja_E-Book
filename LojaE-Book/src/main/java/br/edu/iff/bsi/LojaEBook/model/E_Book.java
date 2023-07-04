package br.edu.iff.bsi.LojaEBook.model;

import jakarta.persistence.Entity;

@Entity
public class E_Book extends Produto {

	private String titulo;
	private String genero;
	private String autor;
	private String editora;
	private int qtdPaginas;
	
	public E_Book(double preco, String titulo, String genero, String autor, String editora, int qtdPaginas) {
		super(preco);
		this.titulo = titulo;
		this.genero = genero;
		this.autor = autor;
		this.editora = editora;
		this.qtdPaginas = qtdPaginas;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getGenero() {
		return genero;
	}

	public String getAutor() {
		return autor;
	}

	public String getEditora() {
		return editora;
	}

	public int getQtdPaginas() {
		return qtdPaginas;
	}
	
	
	
	
}
