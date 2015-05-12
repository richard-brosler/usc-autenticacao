package br.usc.segusc.autenticacao.exception;

public class AutenticacaoInvalidaException extends Exception {
	public AutenticacaoInvalidaException(String Msg) {
		super(Msg);
	}
	public AutenticacaoInvalidaException() {
		super("Autenticação Inválida!");
	}

}
