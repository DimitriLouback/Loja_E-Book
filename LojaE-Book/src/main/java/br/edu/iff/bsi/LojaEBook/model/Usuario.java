package br.edu.iff.bsi.LojaEBook.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	//@Pattern(regexp="[0-9]{3}.[0-9]{3}.[0-9]{3}-[0-9]{2}", message="Deve seguir o padrão do CPF")
	@Column(unique=true, length = 14)
	private String login;
	@Column()
	private String senha;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Permissao> permissoes;
	
	
	public Usuario(String login, String senha) {
		this.login = login;
		this.senha = senha;
		this.permissoes = new ArrayList<>();
	}
	
	public Usuario() {}

	public Long getId() {
		return id;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public List<Permissao> getPermissoes() {
		return permissoes;
	}
	
	public void addPermissao(Permissao permissao) {
		this.permissoes.add(permissao);
	}
	
	
	
}
