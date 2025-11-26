package br.ucs.poo.Loja.Model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Pessoa implements Serializable{
	private String nome;
    private String numero;
    private Endereco endereco;

    public Endereco getEndereco() {
		return endereco;
	}

    public Pessoa() {
    	
    }
    public String dadosEndereco() {
    	StringBuilder sc = new StringBuilder();
    	sc.append(" - Rua: "+ endereco.getRua()+" - Bairro: "+endereco.getBairro()+" - Cidade: "+endereco.getCidade());
    	sc.append(System.lineSeparator());
    	return sc.toString();
    }
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Pessoa(String nome, String numero,Endereco end) {
        this.nome = nome;
        this.numero = numero;
        this.endereco=end;
    }
	
	public void alterarEndereco(String rua,String bairro,String cidade) {
		endereco.setRua(rua);
		endereco.setBairro(bairro);
		endereco.setCidade(cidade);
	}
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
