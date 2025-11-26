package br.ucs.poo.Loja.Model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("serial")
public class Pedidos implements Serializable{
	 private static int codigo;
	 private int cod;
	 private Map<Produto,Integer> produtos = new HashMap<>();
	 private double valorTotal;
	 private Data data;
	 private String status;
	 private DataEnvio dataEnvio;
	 private Cliente cliente;
	 
	 public Pedidos() {
		 
	 }
	 public void retornaEstoque() {
		 for(Map.Entry<Produto, Integer> entrada : produtos.entrySet()) {
			 entrada.getKey().alterarEstoque(entrada.getValue());
		 }
	 }
	 public Pedidos(Map<Produto,Integer> listaCompras,double total,Data data,Cliente cliente) {
		 this.valorTotal=total;
		 this.data=data;
		 this.produtos.putAll(listaCompras);
		 this.status = "NOVO";
		 this.dataEnvio = null;
		 this.setCod(codigo++);
		 this.cliente = cliente;
	 }
	public double getValorTotal() {
		return valorTotal;
	}
	
	public void setValorTotal(int valorTotal) {
		this.valorTotal = valorTotal;
	}
	public Data getData() {
		return data;
	}
	public void setData(Data data) {
		this.data = data;
	}
	public Map<Produto,Integer> getProdutos() {
		return produtos;
	}
	public void setProdutos(Map<Produto,Integer> produtos) {
		this.produtos = produtos;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String condicao) {
		this.status = condicao;
	}
	public DataEnvio getDataEnvio() {
		return dataEnvio;
	}
	public void setDataEnvio(DataEnvio dataEnvio) {
		this.dataEnvio = dataEnvio;
	}
	public int getCod() {
		return cod;
	}
	public void setCod(int cod) {
		this.cod = cod;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}
