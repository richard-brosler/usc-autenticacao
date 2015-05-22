package br.usc.segusc.autenticacao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import br.usc.segusc.log.entity.LogLevel;
import br.usc.segusc.log.services.ILogService;
import br.usc.segusc.log.services.LogFactory;
import br.usc.segusc.autenticacao.entity.TipoAutenticacao;
import br.usc.segusc.autenticacao.entity.Usuario;
import br.usc.segusc.autenticacao.exception.AutenticacaoInvalidaException;
import br.usc.segusc.autenticacao.exception.AutenticacaoUserBlockedException;
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
import br.usc.segusc.repositorio.services.IRepositorioService;
import br.usc.segusc.repositorio.services.RepositorioFactory;

/**
 * Componente de Autentica��o para projeto da disciplina de PSOO 
 * @author Andr� Luis Pelisoli
 * @author Bruno Rocha Roma
 * @author Marcelo Cabello Peres
 * @author P�mela Zagatti 
 * @author Richard Brosler
 * @author Rodrigo Bulgarelli
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
	private List<Usuario> lstLogin;
	private Predicate<Usuario> filtro;
	public Autenticacao() {
		lstLogin = new ArrayList<Usuario>();
	}
	@Override
	public Usuario autenticar(Usuario usuario, TipoAutenticacao tipo) throws AutenticacaoInvalidaException {
		//Obtendo o logger do sistema
		ILogService log = LogFactory.getLogServiceImpl();

		//Obtendo a interface de criptografia
		log.logger(LogLevel.INFO, "Obtendo o servi�o de Criptografia");
		ICriptografiaService cifrador = CriptografiaFactory.getCriptografiaServiceImpl();
		
		//Obtendo a interface de configuracao
		log.logger(LogLevel.INFO, "Obtendo o servi�o de Configura��o");
		IConfiguracaoService config = ConfiguracaoFactory.getConfiguracaoServiceImpl();
		
		//Obtendo as configura��es do sistema
		log.logger(LogLevel.INFO, "Obtendo o servi�o de Reposit�rio");
		IRepositorioService repositorio = RepositorioFactory.getRepositorioServiceImpl();
		
		//Obtendo o Autorizador
		log.logger(LogLevel.INFO, "Obtendo o servi�o de Autoriza��o");
		IAutorizacaoService autorizador = AutorizacaoFactory.getAutorizacaoServiceImpl();
		
		//levantando o tipo de criptografia a ser usada na senha
		String senhaCifrada="", tipocriptografica="MD5";

		//Adicionando o usu�rio a lista para checar quantas tentativas frustradas ocorreram
		lstLogin.add(usuario);
		
		//criando predicate para filtrar os valores
		filtro = v -> v.getLogin().equals(usuario.getLogin());
		
		//obtendo a quantidade de logins falhos
		int qtdeLogin = (int) lstLogin.stream().filter(filtro).count();
				
		try {
			//verificando se o usu�rio est� bloqueado
			log.logger(LogLevel.INFO, "Verificando se o usu�rio possui outras tentantivas de login.");
			if ( qtdeLogin>3 ){
				log.logger(LogLevel.WARNING, "Usu�rio "+usuario.getLogin()+" est� bloqueado!");
				throw new AutenticacaoUserBlockedException();
			}
			
			//obtendo o tipo de criptografia via configuracao
			log.logger(LogLevel.INFO, "Obtendo o m�todo de criptografia");
			tipocriptografica=config.getValue("CRIPTGRAFIA");
		
			//validando o usu�rio para poder dar continuidade
			switch (tipo) {
			case SENHA:
				log.logger(LogLevel.INFO, "Cifrando a senha");
				if (tipocriptografica.equals("MD5"))
					senhaCifrada = cifrador.encriptar(usuario.getSenha(),AlgoritmoCriptografia.MD5);
				else
					senhaCifrada = cifrador.encriptar(usuario.getSenha(),AlgoritmoCriptografia.SHA1);
				
				//verificando o usu�rio no reposit�rio
				log.logger(LogLevel.INFO, "Obtendo o reposit�rio do usu�rio "+usuario.getLogin());
				if (repositorio.consultarLogin(usuario.getLogin())==null)
					throw new AutenticacaoInvalidaException("Login n�o encontrado!"); //login n�o encontrado

				//checando a senha no reposit�rio
				log.logger(LogLevel.INFO, "Validando a senha do usu�rio "+usuario.getLogin());
				if (!repositorio.consultarLogin(usuario.getLogin()).equals(senhaCifrada)){
					log.logger(LogLevel.WARNING, "Tentativas frustradas do " + 
								usuario.getLogin()+ " " + qtdeLogin );
					throw new AutenticacaoInvalidaException("Senha Inv�lida!"); //Senha errada
				}
				break;
			case BIOMETRIA:
				//Obtendo a biometria
				log.logger(LogLevel.INFO, "Obtendo o servi�o de Biometria");
				IBiometriaService biom = BiometriaFactory.getBiometriaServiceImpl();
				
				//verificando a biometria, caso n�o encontrada ser� gerada uma exce��o
				log.logger(LogLevel.INFO, "Obtendo a Biometria do usu�rio "+usuario.getLogin());
				
				log.logger(LogLevel.INFO, "Mostrando a Biometria do usu�rio "+usuario.getBiometria().toString());
				try {
					biom.scanear(usuario.getBiometria());
				} catch (BiometriaInvalidaException e){
					log.logger(LogLevel.WARNING, "Tentativas frustradas do " + 
								usuario.getLogin()+ " " + qtdeLogin );
					throw e;
				}
				break;
			}
			//retirando os usu�rios da lista
			lstLogin.removeIf(filtro);
			
			//Obtendo o perfil do usu�rio
			log.logger(LogLevel.INFO, "Obtendo o perfil do usu�rio "+usuario.getLogin()+
									  " e j� o setando ao objeto usu�rio");
			usuario.setPerfil(autorizador.obterPerfis(usuario.getLogin()));
			
			//Obtendo a permiss�o do usu�rio
			log.logger(LogLevel.INFO, "Obtendo a permiss�o do usu�rio "+usuario.getLogin()+
									  " e j� o setando ao objeto usu�rio");
			usuario.setPermissao(autorizador.obterPermissoes(usuario.getLogin()));
			
			//Retornando o usu�rio com os dados
			log.logger(LogLevel.INFO, "Retornando o usu�rio "+usuario.getLogin());
			return usuario;
		} catch (AutenticacaoUserBlockedException e) {
			LogFactory.getLogServiceImpl().logger(LogLevel.ERROR, e.getMessage());
			throw e;
		} catch (Exception e) {
			LogFactory.getLogServiceImpl().logger(LogLevel.ERROR, e.getMessage());
			throw new AutenticacaoInvalidaException(e.getMessage());
		}
	}
}
