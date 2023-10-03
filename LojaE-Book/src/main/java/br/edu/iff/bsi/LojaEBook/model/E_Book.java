package br.edu.iff.bsi.LojaEBook.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
public class E_Book extends Produto {

	@NotBlank(message="Não pode ser em branco ou nulo")
	@Size(min=1,max=60,message="Tem que ter entre 1 e 60 caractéres")
	@Column(unique=true, length = 60)
	private String titulo = " ";
	@Size(min=1,max=20,message="Tem que ter entre 1 e 20 caractéres")
	@Column(length = 20)
	private String genero;
	@Size(min=1,max=60,message="Tem que ter entre 1 e 60 caractéres")
	@Column(length = 60)
	private String autor;
	@Size(min=1,max=20,message="Tem que ter entre 1 e 20 caractéres")
	@Column(length = 20)
	private String editora;
	@Positive(message="Tem que ser maior que 0")
	private int qtdPaginas;
	
	
	public E_Book(double preco, String titulo, String genero, String autor, String editora, int qtdPaginas) {
		super(preco);
		this.titulo = titulo;
		this.genero = genero;
		this.autor = autor;
		this.editora = editora;
		this.qtdPaginas = qtdPaginas;
	}
	

	public E_Book() {}
	
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
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public void setGenero(String genero) {
		this.genero = genero;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public void setQtdPaginas(int qtdPaginas) {
		this.qtdPaginas = qtdPaginas;
	}
	
	
	
	
}
