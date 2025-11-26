package br.ucs.poo.Loja.Model;

@SuppressWarnings("serial")
public class Fornecedor extends PessoaJuridica implements Comparable<Fornecedor> {
	private int idFornecedor;

	public Fornecedor(String nome, String numero, String cnpj, int idFornecedor, Endereco end) {
		super(nome, numero, cnpj, end);
		this.idFornecedor = idFornecedor;
	}
	
	public int getIdFornecedor() {
		return idFornecedor;
	}

	public void setIdFornecedor(int idFornecedor) {
		this.idFornecedor = idFornecedor;
	}

	@Override
	public int compareTo(Fornecedor o) {
		Fornecedor outro = o;
		return this.getNome().compareTo(outro.getNome());
	}

	public void alterarFornecedor(String novoNome, String novoNumero, String novoCnpj, String rua, String bairro,String cidade) {

		this.setNome(novoNome);
		this.setNumero(novoNumero);
		this.setCnpj(novoCnpj);
		this.alterarEndereco(rua, bairro, cidade);
		
	}
}