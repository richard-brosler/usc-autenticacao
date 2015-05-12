package br.usc.segusc.autenticacao.services;

import br.usc.segusc.autenticacao.impl.Autenticacao;
/**
 * Classe de Factory para criar a autentica��o
 * @author Andr� Luis Pelisoli
 * @author Bruno Rocha Roma
 * @author Marcelo Cabello Peres
 * @author P�mela Zagatti 
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
	 * Constructor padr�o em modo private para que seja poss�vel o singloton
	 */
	private AutenticacaoFactory(){
		aut = new Autenticacao();
	}
	/**
	 * M�todo getInstance para retornar a inst�ncia do Objeto singloton AutenticacaoFactory
	 * @return AutenticacaoFactory - retorna a inst�ncia do Objeto singloton AutenticacaoFactory
	 */
	public static AutenticacaoFactory getInstance(){
		if (autent==null){
			autent = new AutenticacaoFactory();
		}
		return autent;
	}
	/**
	 * M�todo getAutenticacaoServiceImpl para retornar a interface IAutenticacaoService
	 * @return IAutenticacaoService - retorna a interface IAutenticacaoService
	 */
	public static IAutenticacaoService getAutenticacaoServiceImpl(){
		return autent.aut;
	}
}
