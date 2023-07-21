package br.edu.iff.bsi.LojaEBook.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Cargo implements Serializable {

private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(unique=true, length = 30)
	private String funcao;
	private double salario;
	
	public Cargo(String funcao, double salario) {
		super();
		this.funcao = funcao;
		this.salario = salario;
	}

	private Cargo() {}
	
	public Long getId() {
		return id;
	}

	public String getFuncao() {
		return funcao;
	}

	public double getSalario() {
		return salario;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}
	
	
	
}
