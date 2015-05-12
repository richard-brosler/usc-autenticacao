package br.usc.segusc.autenticacao.services;

import br.usc.segusc.autenticacao.impl.Autenticacao;
/**
 * Classe de Factory para criar a autenticação
 * @author André Luis Pelisoli
 * @author Bruno Rocha Roma
 * @author Marcelo Cabello Peres
 * @author Pâmela Zagatti 
 * @author Richard Brosler
 * @author Rodrigo Bulgarelli
 * @version 1.0.0, Mai 9, 2015
 * @since 1.0.0
 * @see Autenticacao
 *
 */
public class AutenticacaoFactory {
	
	private static AutenticacaoFactory autent;
	private Autenticacao aut;
	/**
	 * Constructor padrão em modo private para que seja possível o singloton
	 */
	private AutenticacaoFactory(){
		aut = new Autenticacao();
	}
	/**
	 * Método getInstance para retornar a instância do Objeto singloton AutenticacaoFactory
	 * @return AutenticacaoFactory - retorna a instância do Objeto singloton AutenticacaoFactory
	 */
	public static AutenticacaoFactory getInstance(){
		if (autent==null){
			autent = new AutenticacaoFactory();
		}
		return autent;
	}
	/**
	 * Método getAutenticacaoServiceImpl para retornar a interface IAutenticacaoService
	 * @return IAutenticacaoService - retorna a interface IAutenticacaoService
	 */
	public static IAutenticacaoService getAutenticacaoServiceImpl(){
		return autent.aut;
	}
}
