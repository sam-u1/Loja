package br.ucs.poo.Loja.Model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Estoque implements Serializable{
	
    private int quantidade;
    
    public Estoque(int quantidade) {
    	this.setQuantidade(quantidade);
    }
    
	
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
}