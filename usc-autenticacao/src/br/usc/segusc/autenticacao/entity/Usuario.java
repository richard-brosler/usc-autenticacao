package br.usc.segusc.autenticacao.entity;

import br.usc.segusc.autorizacao.entity.Permissao;
import br.usc.segusc.autorizacao.entity.Perfil;

public class Usuario {
	private String login;
	private String senha;
	private int [][] biometria;
	private Perfil perfil;
	private Permissao permissao;
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public int[][] getBiometria() {
		return biometria;
	}
	public void setBiometria(int[][] biometria) {
		this.biometria = biometria;
	}
	public Perfil getPerfil() {
		return perfil;
	}
	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	public Permissao getPermissao() {
		return permissao;
	}
	public void setPermissao(Permissao permissao) {
		this.permissao = permissao;
	}
}
