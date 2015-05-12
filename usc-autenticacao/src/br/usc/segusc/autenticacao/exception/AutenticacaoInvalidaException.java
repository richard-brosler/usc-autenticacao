package br.usc.segusc.autenticacao.exception;
/**
 * Classe de exceção utilizada no componente de autenticação 
 * @author André Luis Pelisoli
 * @author Bruno Rocha Roma
 * @author Marcelo Cabello Peres
 * @author Pâmela Zagatti 
 * @author Richard Brosler
 * @author Rodrigo Bulgarelli
 * @version 1.0.0, Mai 9, 2015
 * @since 1.0.0
 *
 */
public class AutenticacaoInvalidaException extends Exception {
	/**
	 * Constructor com a recepção de uma mensagem para ser mostrada na exceção
	 * @param Msg String - recebe uma String para que ao gerar a exceção mostre a mensagem
	 */
	public AutenticacaoInvalidaException(String Msg) {
		super(Msg);
	}
	/**
	 * Constructor com a mensagem padrão de autenticação inválida
	 */
	public AutenticacaoInvalidaException() {
		super("Autenticação Inválida!");
	}

}
