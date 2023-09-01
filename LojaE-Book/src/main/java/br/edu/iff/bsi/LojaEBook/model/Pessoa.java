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
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@MappedSuperclass
public abstract class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Size(min=1,max=60,message="Tem que ter entre 1 e 60 caractéres")
	@Column(length = 60)
	private String nome;
	@Email(message="Tem que ser em formato de email")
	@Column(length = 60)
	private String email;
	@NotBlank(message="Não pode ser em branco ou nulo")
	@Pattern(regexp="[0-9]{3}.[0-9]{3}.[0-9]{3}-[0-9]{2}", message="Deve seguir o padrão do CPF")
	@Column(unique=true, length = 14)
	private String cpf = " ";
	@Size(min=1,max=20,message="Tem que ter entre 1 e 20 caractéres")
	@Column(length = 20)
	private String senha;
	
	@Nullable
	@ElementCollection
	@Size(min=1,max=20,message="Tem que ter entre 1 e 3 telefones")
	@Column(length = 16)
	private List<@Pattern(regexp="\\([0-9]{2}\\) [0-9]{5}-[0-9]{4}", message="Deve seguir o padrão do Telefone")String> telefone = new ArrayList<String>();
	
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
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
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
	
	public List<String> getTelefone() {
		return telefone;
	}
	
	public int getQtdTelefones() {
		return this.telefone.size();
	}
	
}
