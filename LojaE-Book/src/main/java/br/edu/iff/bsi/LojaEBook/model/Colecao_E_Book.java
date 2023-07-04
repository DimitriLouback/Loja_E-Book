package br.edu.iff.bsi.LojaEBook.model;

import jakarta.persistence.Entity;

@Entity
public class Colecao_E_Book extends Produto {

	private String serie;
	private int qtdVolumes;
	
	public Colecao_E_Book(double preco, String serie, int qtdVolumes) {
		super(preco);
		this.serie = serie;
		this.qtdVolumes = qtdVolumes;
	}

	public String getSerie() {
		return serie;
	}

	public int getQtdVolumes() {
		return qtdVolumes;
	}
	
	
	
}
