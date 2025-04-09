package br.ufape.edu.usuarios;

import java.util.ArrayList;
import br.ufape.edu.solicitacao.Solicitacao;

public class Usuario {
	// ATRIBUTOS
	private int id;
	private String username;
	private String senha;
	private String email;
	private String telefone;
	private ArrayList<Solicitacao> solicitacoes;
	
	// CONSTRUTOR
	public Usuario(String username, String senha, String email, String telefone) {
		super();
		this.username = username;
		this.senha = senha;
		this.email = email;
		this.telefone = telefone;
	}
	
	// GETTERS e SETTERS
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public ArrayList<Solicitacao> getSolicitacoes() {
		return solicitacoes;
	}
	public void setSolicitacoes(ArrayList<Solicitacao> solicitacoes) {
		this.solicitacoes = solicitacoes;
	}
		
	
}
