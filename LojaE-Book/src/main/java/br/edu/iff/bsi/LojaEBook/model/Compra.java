package br.edu.iff.bsi.LojaEBook.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
public class Compra implements Serializable {

private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@PastOrPresent(message="Não pode ser no futuro")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataHora;
	@PositiveOrZero(message="Tem que ser maior ou igual a 0")
	private int qtdProdutos;
	@PositiveOrZero(message="Tem que ser maior ou igual a 0")
	private double precoFinal;
	private boolean finalizado;
	@NotBlank(message="Não pode ser em branco ou nulo")
	@Pattern(regexp="[0-9]{3}.[0-9]{3}.[0-9]{3}-[0-9]{2}", message="Deve seguir o padrão do CPF")
	private String cpfCliente = " ";
	
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

	public Compra() {}
	
	public Long getId() {
		return id;
	}

	public String getDataHora() {
		if (dataHora != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            return dateFormat.format(dataHora.getTime());
        } else {
            return "";
        }
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
