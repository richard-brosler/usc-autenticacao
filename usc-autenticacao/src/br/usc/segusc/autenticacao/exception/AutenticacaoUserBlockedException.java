package br.usc.segusc.autenticacao.exception;
/**
 * Classe de exce��o utilizada no componente de autentica��o 
 * @author Andr� Luis Pelisoli
 * @author Bruno Rocha Roma
 * @author Marcelo Cabello Peres
 * @author P�mela Zagatti 
 * @author Richard Brosler
 * @author Rodrigo Bulgarelli
 * @version 1.0.0, Mai 21, 2015
 * @since 1.0.0
 *
 */

public class AutenticacaoUserBlockedException extends
		AutenticacaoInvalidaException {
	/**
	 * Constructor com a mensagem padr�o de usu�rio bloqueado
	 */
	public AutenticacaoUserBlockedException() {
		super("Usu�rio Bloqueado");
	}
}
