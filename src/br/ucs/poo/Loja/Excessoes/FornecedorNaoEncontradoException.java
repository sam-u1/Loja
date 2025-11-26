package br.ucs.poo.Loja.Excessoes;

public class FornecedorNaoEncontradoException extends Exception {
	
	public static final String MESSAGE = "O fornecedor não existe!\n";
	
	public FornecedorNaoEncontradoException(String message) {
		super(MESSAGE+" "+message);
	}
}
