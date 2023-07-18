package br.edu.iff.bsi.LojaEBook.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.micrometer.common.lang.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(length = 60)
	private String nome;
	@Column(length = 60)
	private String email;
	@Column(unique=true, length = 14)
	private String cpf;
	@Column(length = 20)
	private String senha;
	
	@Nullable
	@ElementCollection
	@Column(length = 16)
	private List<String> telefone = new ArrayList<String>();
	
	public Pessoa(String nome, String email, String cpf, String senha, String telefone) {
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.senha = senha;
		this.telefone.add(telefone);
	}

	public Pessoa() {}
	
	public Long getId() {
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

	public String getSenha() {
		return senha;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void adicionarTelefone(String telefone) {
		this.telefone.add(telefone);
	}
	
	public void removerTelefone(String telefone) {
		this.telefone.remove(telefone);
	}
	
}
