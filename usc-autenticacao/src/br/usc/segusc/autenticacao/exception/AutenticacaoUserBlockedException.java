package br.usc.segusc.autenticacao.exception;
/**
 * Classe de exceção utilizada no componente de autenticação 
 * @author André Luis Pelisoli
 * @author Bruno Rocha Roma
 * @author Marcelo Cabello Peres
 * @author Pâmela Zagatti 
 * @author Richard Brosler
 * @author Rodrigo Bulgarelli
 * @version 1.0.0, Mai 21, 2015
 * @since 1.0.0
 *
 */

public class AutenticacaoUserBlockedException extends
		AutenticacaoInvalidaException {
	/**
	 * Constructor com a mensagem padrão de usuário bloqueado
	 */
	public AutenticacaoUserBlockedException() {
		super("Usuário Bloqueado");
	}
}
