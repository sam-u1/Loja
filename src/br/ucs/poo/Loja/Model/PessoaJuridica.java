package br.ucs.poo.Loja.Model;

@SuppressWarnings("serial")
public class PessoaJuridica extends Pessoa {
    private String cnpj;

    public PessoaJuridica() {
    	
    }
    public PessoaJuridica(String nome, String numero, String cnpj,Endereco end) {
        super(nome, numero,end);
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
