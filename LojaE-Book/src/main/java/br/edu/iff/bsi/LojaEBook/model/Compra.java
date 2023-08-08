package br.edu.iff.bsi.LojaEBook.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Compra implements Serializable {

private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataHora;
	private int qtdProdutos;
	private double precoFinal;
	private boolean finalizado;
	private String cpfCliente;
	
	@ManyToMany
	@JoinTable(name = "associacao_compra_produto",
				joinColumns = @JoinColumn(name = "fk_compra"),
				inverseJoinColumns = @JoinColumn(name = "fk_produto"))
	private List<Produto> produto;
	
	public Compra(String cpfCliente) {
		this.finalizado = false;
		this.qtdProdutos = 0;
		this.produto = new ArrayList();
		this.cpfCliente = cpfCliente;
	}
	
	public Compra(Long id, String cpfCliente) {
		this.id = id;
		this.finalizado = false;
		this.qtdProdutos = 0;
		this.produto = new ArrayList();
		this.cpfCliente = cpfCliente;
	}

	private Compra() {}
	
	public Long getId() {
		return id;
	}

	public Calendar getDataHora() {
		return dataHora;
	}

	public int getQtdProdutos() {
		return qtdProdutos;
	}

	public double getPrecoFinal() {
		return precoFinal;
	}
	
	public String getCpfCliente() {
		return cpfCliente;
	}

	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}

	public void adicionarProduto(Produto produto) {
		this.produto.add(produto);
		this.qtdProdutos++;
		this.precoFinal+=produto.getPreco();
	}
	
	public void removerProduto(Produto produto) {
		this.produto.remove(produto);
		this.qtdProdutos--;
		this.precoFinal-=produto.getPreco();
	}
	
	public void finalizar() {
		this.finalizado = true;
		this.dataHora = Calendar.getInstance();
	}
	
	public boolean isFinalizado() {
		return this.finalizado;
	}
}
