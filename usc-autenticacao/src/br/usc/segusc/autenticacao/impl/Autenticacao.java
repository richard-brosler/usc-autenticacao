package br.usc.segusc.autenticacao.impl;

import br.usc.segusc.log.entity.LogLevel;
import br.usc.segusc.log.services.LogFactory;
import br.usc.segusc.autenticacao.entity.TipoAutenticacao;
import br.usc.segusc.autenticacao.entity.Usuario;
import br.usc.segusc.autenticacao.exception.AutenticacaoInvalidaException;
import br.usc.segusc.autenticacao.services.IAutenticacaoService;
import br.usc.segusc.biometria.service.BiometriaFactory;
import br.usc.segusc.biometria.service.IBiometriaService;
import br.usc.segusc.configuracao.services.ConfiguracaoFactory;
import br.usc.segusc.configuracao.services.IConfiguracaoService;
import br.usc.segusc.criptografia.entity.AlgoritmoCriptografia;
import br.usc.segusc.criptografia.services.CriptografiaFactory;
import br.usc.segusc.criptografia.services.ICriptografiaService;
import br.usc.segusc.repositorio.services.IRepositorioService;
import br.usc.segusc.repositorio.services.RepositorioFactory;

public class Autenticacao implements IAutenticacaoService {

	@Override
	public Usuario autenticar(Usuario usuario, TipoAutenticacao tipo)
			throws AutenticacaoInvalidaException {
		//obtendo a interface de criptografia
		ICriptografiaService cifrador = CriptografiaFactory.getCriptografiaServiceImpl();
		//obtendo a interface de configuracao
		IConfiguracaoService config = ConfiguracaoFactory.getConfiguracaoServiceImpl();
		//obtendo as configurações do sistema
		IRepositorioService repositorio = RepositorioFactory.getRepositorioServiceImpl();
		//levantando o tipo de criptografia a ser usada na senha
		String senhaCifrada="", tipocriptografica="MD5";
		try {
			//obtendo o tipo de criptografia via configuracao
			tipocriptografica=config.getValue("CRIPTGRAFIA");
			//validando o usuário para poder dar continuidade
			switch (tipo) {
			case SENHA:
				if (tipocriptografica.equals("MD5"))
					senhaCifrada = cifrador.encriptar(usuario.getSenha(),AlgoritmoCriptografia.MD5);
				else
					senhaCifrada = cifrador.encriptar(usuario.getSenha(),AlgoritmoCriptografia.SHA1);
				if (repositorio.consultarLogin(usuario.getLogin())==null)
					throw new AutenticacaoInvalidaException(); //login não encontrado
				if (!repositorio.consultarLogin(usuario.getLogin()).equals(senhaCifrada))
					throw new AutenticacaoInvalidaException(); //Senha errada
				break;
			case BIOMETRIA:
				IBiometriaService biom = BiometriaFactory.getBiometriaServiceImpl();
				biom.scanear(usuario.getBiometria());
				break;
			}
			return usuario;
		} catch (Exception e) {
			LogFactory.getLogServiceImpl().logger(LogLevel.ERROR, e.getMessage());
			throw new AutenticacaoInvalidaException();
		}
	}
}
