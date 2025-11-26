package br.ucs.poo.Loja.Excessoes;

public class PessoaNaoEncontradaException extends Exception{

	public static final String MESSAGE = "A pessoa não foi encontrada!\n";
	
	public PessoaNaoEncontradaException(String message) {
		super(MESSAGE+" "+message);
	}
}
