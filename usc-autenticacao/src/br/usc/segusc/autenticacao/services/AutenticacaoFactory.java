package br.usc.segusc.autenticacao.services;

import br.usc.segusc.autenticacao.impl.Autenticacao;

public class AutenticacaoFactory {
	
	private static AutenticacaoFactory autent;
	private Autenticacao aut;
	
	private AutenticacaoFactory(){
		aut = new Autenticacao();
	}
	
	public static AutenticacaoFactory getInstance(){
		if (autent==null){
			autent = new AutenticacaoFactory();
		}
		return autent;
	}
	
	public static IAutenticacaoService getAutenticacaoServiceImpl(){
		return autent.aut;
	}
}
