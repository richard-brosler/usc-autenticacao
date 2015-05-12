package br.usc.segusc.autenticacao.exception;
/**
 * Classe de exce��o utilizada no componente de autentica��o 
 * @author Andr� Luis Pelisoli
 * @author Bruno Rocha Roma
 * @author Marcelo Cabello Peres
 * @author P�mela Zagatti 
 * @author Richard Brosler
 * @author Rodrigo Bulgarelli
 * @version 1.0.0, Mai 9, 2015
 * @since 1.0.0
 *
 */
public class AutenticacaoInvalidaException extends Exception {
	/**
	 * Constructor com a recep��o de uma mensagem para ser mostrada na exce��o
	 * @param Msg String - recebe uma String para que ao gerar a exce��o mostre a mensagem
	 */
	public AutenticacaoInvalidaException(String Msg) {
		super(Msg);
	}
	/**
	 * Constructor com a mensagem padr�o de autentica��o inv�lida
	 */
	public AutenticacaoInvalidaException() {
		super("Autentica��o Inv�lida!");
	}

}
