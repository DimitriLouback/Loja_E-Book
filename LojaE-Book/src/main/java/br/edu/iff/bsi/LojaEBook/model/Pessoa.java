package br.edu.iff.bsi.LojaEBook.model;

public class Pessoa {

	private String id;
	private String nome;
	private String email;
	private String cpf;
	
	public Pessoa(String id, String nome, String email, String cpf) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
	}

	public String getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getCpf() {
		return cpf;
	}
	
	
	
}
