package br.ucs.poo.Loja.Model;


@SuppressWarnings("serial")
public class PessoaFisica extends Pessoa {
    private String cpf;
    private String senha;
    private static int cod;
    private int idConta;
    
    public PessoaFisica(String nome, String numero, String cpf,String senha,Endereco end) {
        super(nome, numero,end);
        this.cpf = cpf;
        this.senha=senha;
        this.idConta = cod++;
    }
   
    public int getId() {
        return idConta;
    }
    
    public void setIdConta() {
        this.idConta = cod;
        cod++;
    }
    
    
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public void alterarPessoa(String nome, String numero,String sen,String rua,String bairro,String cidade){
    	this.setNome(nome);
    	this.setNumero(numero);
    	this.setSenha(sen);
    	this.alterarEndereco(rua, bairro, cidade);;
    }
	
	
}
