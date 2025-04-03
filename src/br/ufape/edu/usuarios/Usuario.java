package br.ufape.edu.usuarios;

import java.util.ArrayList;
import br.ufape.edu.solicitacao.Solicitacao;

public class Usuario {
	// ATRIBUTOS
	private int id;
	private String nome;
	private String senha;
	private String contato;
	private ArrayList<Solicitacao> solicitacoes;
	
	
	// CONSTRUTOR
	public Usuario(int id, String nome, String senha, String contato, ArrayList<Solicitacao> solicitacoes) {
		this.id = id;
		this.nome = nome;
		this.senha = senha;
		this.contato = contato;
		this.solicitacoes = solicitacoes;
	}
	
	// GETTERS E SETTERS
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getContato() {
		return contato;
	}
	public void setContato(String contato) {
		this.contato = contato;
	}
	public ArrayList<Solicitacao> getSolicitacoes() {
		return solicitacoes;
	}
	public void setSolicitacoes(ArrayList<Solicitacao> solicitacoes) {
		this.solicitacoes = solicitacoes;
	}
	
}
