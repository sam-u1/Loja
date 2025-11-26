package br.ucs.poo.Loja.Excessoes;

public class ProdutoNaoEncontradoException extends Exception {

	public static final String MESSAGE = "O produto não existe!\n";
	
	public ProdutoNaoEncontradoException(String message) {
		super(MESSAGE+" "+message);
	}
}
