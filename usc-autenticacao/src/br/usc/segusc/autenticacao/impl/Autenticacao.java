package br.usc.segusc.autenticacao.impl;

import br.usc.segusc.log.entity.LogLevel;
import br.usc.segusc.log.services.LogFactory;
import br.usc.segusc.autenticacao.entity.TipoAutenticacao;
import br.usc.segusc.autenticacao.entity.Usuario;
import br.usc.segusc.autenticacao.exception.AutenticacaoInvalidaException;
import br.usc.segusc.autenticacao.services.AutenticacaoFactory;
import br.usc.segusc.autenticacao.services.IAutenticacaoService;
import br.usc.segusc.autorizacao.services.AutorizacaoFactory;
import br.usc.segusc.autorizacao.services.IAutorizacaoService;
import br.usc.segusc.biometria.exception.BiometriaInvalidaException;
import br.usc.segusc.biometria.service.BiometriaFactory;
import br.usc.segusc.biometria.service.IBiometriaService;
import br.usc.segusc.configuracao.exception.ConfiguracaoInvalidaException;
import br.usc.segusc.configuracao.services.ConfiguracaoFactory;
import br.usc.segusc.configuracao.services.IConfiguracaoService;
import br.usc.segusc.criptografia.entity.AlgoritmoCriptografia;
import br.usc.segusc.criptografia.services.CriptografiaFactory;
import br.usc.segusc.criptografia.services.ICriptografiaService;
import br.usc.segusc.repositorio.exception.LoginInvalidoException;
import br.usc.segusc.repositorio.services.IRepositorioService;
import br.usc.segusc.repositorio.services.RepositorioFactory;
/**
 *
 * Componente de Autentica��o para projeto da disciplina de PSOO 
 * @author Andr� Pelisoli
 * @author Bruno Rocha Roma
 * @author Marcelo Cabello Peres
 * @author Richard Brosler
 * @version 1.0.0, Mai 9, 2015
 * @since 1.0.0
 * @see CriptografiaFactory
 * @see ConfiguracaoFactory
 * @see RepositorioFactory
 * @see BiometriaFactory
 * @see AutenticacaoFactory
 * @see LogFactory
 * @see AutenticacaoInvalidaException
 * @see ConfiguracaoInvalidaException
 * @see BiometriaInvalidaException
 * @see br.usc.segusc.repositorio.exception.LoginInvalidoException
 * @see br.usc.segusc.autorizacao.exception.LoginInvalidoException
 *
 */
public class Autenticacao implements IAutenticacaoService {

	@Override
	public Usuario autenticar(Usuario usuario, TipoAutenticacao tipo) throws AutenticacaoInvalidaException {
		//Obtendo a interface de criptografia
		ICriptografiaService cifrador = CriptografiaFactory.getCriptografiaServiceImpl();
		
		//Obtendo a interface de configuracao
		IConfiguracaoService config = ConfiguracaoFactory.getConfiguracaoServiceImpl();
		
		//Obtendo as configura��es do sistema
		IRepositorioService repositorio = RepositorioFactory.getRepositorioServiceImpl();
		
		//Obtendo o Autorizador
		IAutorizacaoService autorizador = AutorizacaoFactory.getAutorizacaoServiceImpl();
		
		//levantando o tipo de criptografia a ser usada na senha
		String senhaCifrada="", tipocriptografica="MD5";
		
		try {
			//obtendo o tipo de criptografia via configuracao
			tipocriptografica=config.getValue("CRIPTGRAFIA");
		
			//validando o usu�rio para poder dar continuidade
			switch (tipo) {
			case SENHA:
				if (tipocriptografica.equals("MD5"))
					senhaCifrada = cifrador.encriptar(usuario.getSenha(),AlgoritmoCriptografia.MD5);
				else
					senhaCifrada = cifrador.encriptar(usuario.getSenha(),AlgoritmoCriptografia.SHA1);
				
				//verificando o usu�rio no reposit�rio
				if (repositorio.consultarLogin(usuario.getLogin())==null)
					throw new AutenticacaoInvalidaException("Login n�o encontrado!"); //login n�o encontrado
				
				//checando a senha no reposit�rio
				if (!repositorio.consultarLogin(usuario.getLogin()).equals(senhaCifrada))
					throw new AutenticacaoInvalidaException("Senha Inv�lida!"); //Senha errada
				
				break;
			case BIOMETRIA:
				//Obtendo a biometria
				IBiometriaService biom = BiometriaFactory.getBiometriaServiceImpl();
				//verificando a biometria
				biom.scanear(usuario.getBiometria());
				break;
			}
			//Obtendo o perfil do usu�rio
			usuario.setPerfil(autorizador.obterPerfis(usuario.getLogin()));
			
			//Obtendo a permiss�o do usu�rio
			usuario.setPermissao(autorizador.obterPermissoes(usuario.getLogin()));
			
			//Retornando o usu�rio com os dados
			return usuario;
		} catch (Exception e) {
			LogFactory.getLogServiceImpl().logger(LogLevel.ERROR, e.getMessage());
			throw new AutenticacaoInvalidaException();
		}
	}
}
