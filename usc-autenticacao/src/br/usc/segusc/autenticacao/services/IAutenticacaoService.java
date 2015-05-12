package br.usc.segusc.autenticacao.services;

import br.usc.segusc.autenticacao.entity.TipoAutenticacao;
import br.usc.segusc.autenticacao.entity.Usuario;
import br.usc.segusc.autenticacao.exception.AutenticacaoInvalidaException;
/**
 * Interface de Autenticação 
 * @author André Luis Pelisoli
 * @author Bruno Rocha Roma
 * @author Marcelo Cabello Peres
 * @author Pâmela Zagatti 
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
	 * Método autenticar, reponsável por realizar a autenticação retornando os dados
	 * complementares do usuário enviado
	 * @param usuario Usuario - recebe como parâmetro o usuário para que seja preenchido o perfil e a permissão deste
	 * @param tipo TipoAutenticacao - recebe o método de autenticação, se este será por senha ou por biometria
	 * @return Usuario - retorna o próprio usuário com os dados de perfil e permissão preenchidos
	 * @throws AutenticacaoInvalidaException - propaga a exceção de autenticação inválida
	 */
	public Usuario autenticar(Usuario usuario, TipoAutenticacao tipo) throws AutenticacaoInvalidaException;
}
