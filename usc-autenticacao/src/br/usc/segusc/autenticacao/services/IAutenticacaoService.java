package br.usc.segusc.autenticacao.services;

import br.usc.segusc.autenticacao.entity.TipoAutenticacao;
import br.usc.segusc.autenticacao.entity.Usuario;
import br.usc.segusc.autenticacao.exception.AutenticacaoInvalidaException;
/**
 * Interface de Autentica��o 
 * @author Andr� Luis Pelisoli
 * @author Bruno Rocha Roma
 * @author Marcelo Cabello Peres
 * @author P�mela Zagatti 
 * @author Richard Brosler
 * @author Rodrigo Bulgarelli
 * @version 1.0.0, Mai 9, 2015
 * @since 1.0.0
 * @see TipoAutenticacao
 * @see Usuario
 * @see AutenticacaoInvalidaException
 *
 */
public interface IAutenticacaoService {
	/**
	 * M�todo autenticar, repons�vel por realizar a autentica��o retornando os dados
	 * complementares do usu�rio enviado
	 * @param usuario Usuario - recebe como par�metro o usu�rio para que seja preenchido o perfil e a permiss�o deste
	 * @param tipo TipoAutenticacao - recebe o m�todo de autentica��o, se este ser� por senha ou por biometria
	 * @return Usuario - retorna o pr�prio usu�rio com os dados de perfil e permiss�o preenchidos
	 * @throws AutenticacaoInvalidaException - propaga a exce��o de autentica��o inv�lida
	 */
	public Usuario autenticar(Usuario usuario, TipoAutenticacao tipo) throws AutenticacaoInvalidaException;
}
