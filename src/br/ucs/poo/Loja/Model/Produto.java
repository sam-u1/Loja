package br.ucs.poo.Loja.Model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Produto implements Comparable<Produto> , Serializable{
	
	private int codigo;
	private String nome;
	private Fornecedor fornecedor;
	private Estoque estoque;
	private double preco;

	public Produto(int codigo, String nome, double preco, Estoque estoque, Fornecedor fornecedor) {
		this.codigo = codigo;
		this.nome = nome;
		this.preco = preco;
		this.setEstoque(estoque);
		this.fornecedor = fornecedor;
	}
	public Produto() {
		
	}
	public void diminuirEstoque(int quantidade) {
		int valor = this.estoque.getQuantidade() - quantidade;
		this.estoque.setQuantidade(valor);
	}
	public void alterarEstoque(int quantidade) {
		this.estoque.setQuantidade(quantidade);
	}

	public boolean atualizarEstoque(Produto produto, int novaQuantidade) {

		if (produto != null) {
			estoque.setQuantidade(novaQuantidade);
			return true;
		} else {
			return false;
		}
	}

	public void alterarProduto(String novoNome, double novoPreco, int novaQuantidade,Fornecedor fornecedor) {
		this.setNome(novoNome);
		this.setPreco(novoPreco);
		this.alterarEstoque(novaQuantidade);
		this.setFornecedor(fornecedor);
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getQuantidade() {
		return estoque.getQuantidade();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Estoque getEstoque() {
		return estoque;
	}

	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	@Override
	public int compareTo(Produto o) {
		// TODO Auto-generated method stub
		Produto outro = o;
		return this.getNome().compareTo(outro.getNome());
	}
}