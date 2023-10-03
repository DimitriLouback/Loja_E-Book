package br.edu.iff.bsi.LojaEBook.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

@Entity
public class Cargo implements Serializable {

private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotBlank(message="Não pode ser em branco ou nulo")
	@Size(min=1,max=30,message="Tem que ter entre 1 e 30 caractéres")
	@Column(unique=true, length = 30)
	private String funcao;
	@Positive(message="Tem que ser maior que 0")
	private double salario;
	@PositiveOrZero(message="Tem que ser maior ou igual a 0")
	private int nivelAcesso;
	
	public Cargo(String funcao, double salario, int nivelAcesso) {
		super();
		this.funcao = funcao;
		this.salario = salario;
		this.nivelAcesso = nivelAcesso;
	}

	public Cargo() {}
	
	public Long getId() {
		return id;
	}

	public String getFuncao() {
		return funcao;
	}

	public double getSalario() {
		return salario;
	}
	
	public int getNivelAcesso() {
		return nivelAcesso;
	}

	public void setNivelAcesso(int nivelAcesso) {
		this.nivelAcesso = nivelAcesso;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}
	
	
	
}
