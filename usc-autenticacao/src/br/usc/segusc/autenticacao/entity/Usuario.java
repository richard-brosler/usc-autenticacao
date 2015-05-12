package br.usc.segusc.autenticacao.entity;

import br.usc.segusc.autorizacao.entity.Permissao;
import br.usc.segusc.autorizacao.entity.Perfil;

/**
 * Classe usuario utilizada para transporte de informações dos usuários
 * @author André Luis Pelisoli
 * @author Bruno Rocha Roma
 * @author Marcelo Cabello Peres
 * @author Pâmela Zagatti 
 * @author Richard Brosler
 * @author Rodrigo Bulgarelli
 * @version 1.0.0, Mai 9, 2015
 * @since 1.0.0
 * @see Permissao
 * @see Perfil
 *
 */
public class Usuario {
	private String login;
	private String senha;
	private int [][] biometria;
	private Perfil perfil;
	private Permissao permissao;
	/**
	 * getter do field Login
	 * @return String - retorna o valor do field login
	 */
	public String getLogin() {
		return login;
	}
	/**
	 * setter do field Login
	 * @param login String - recebe uma string para o field Login
	 */
	public void setLogin(String login) {
		this.login = login;
	}
	/**
	 * getter do field Senha
	 * @return String - retorna o valor do field Senha
	 */
	public String getSenha() {
		return senha;
	}
	/**
	 * setter do field senha
	 * @param senha String - recebe uma string para o field Senha
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}
	/**
	 * getter do field Biometria
	 * @return int[][] - retorna um array de inteiros para representar a biometria armazenada no field Biometria
	 */
	public int[][] getBiometria() {
		return biometria;
	}
	/**
	 * setter do field Biometria
	 * @param biometria int [][] - recebe um array de inteiros que representa a biometria e armazena no field Biometria
	 */
	public void setBiometria(int[][] biometria) {
		this.biometria = biometria;
	}
	/**
	 * getter do field Perfil
	 * @return Perfil - retorna o objeto Perfil contendo os dados relativos ao perfil do usuário
	 */
	public Perfil getPerfil() {
		return perfil;
	}
	/**
	 * setter do field Perfil
	 * @param perfil Perfil - recebe um objeto Perfil para atribuir ao field Perfil
	 */
	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	/**
	 * getter do field Permissao
	 * @return Permissao - retorna o objeto Permissao contendo os dados relativos às permissões do usuário
	 */
	public Permissao getPermissao() {
		return permissao;
	}
	/**
	 * setter do field Permissao
	 * @param permissao Permissao - recebe um objeto Permissao para atribuir ao field Permissao 
	 */
	public void setPermissao(Permissao permissao) {
		this.permissao = permissao;
	}
}
