package br.usc.segusc.autenticacao.services;

import br.usc.segusc.autenticacao.entity.TipoAutenticacao;
import br.usc.segusc.autenticacao.entity.Usuario;
import br.usc.segusc.autenticacao.exception.AutenticacaoInvalidaException;

public interface IAutenticacaoService {
	public Usuario autenticar(Usuario usuario, TipoAutenticacao tipo) throws AutenticacaoInvalidaException;
}
