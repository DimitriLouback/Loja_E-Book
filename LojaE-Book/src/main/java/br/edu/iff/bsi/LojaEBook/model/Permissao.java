package br.edu.iff.bsi.LojaEBook.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Permissao implements Serializable {

private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nome;
	
	@ManyToMany(mappedBy = "permissoes")
	private List<Usuario> usuarios;
	
	public Permissao(String nome) {
		this.nome = nome;
	}
	
	public Permissao() {}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}
	
	
	
}
