package br.ufape.edu.item;

/**
 * Classe que representa um Item a ser anunciado ou resgatado no sistema.
 * @author Paulo Vinicius
 */

public class Item {

	private String nome;
	private String descricao;
	private String contato;
	private String img;
	private String status; // "Anunciado" "Resgatado" "Aguardando" "null"-> quando a solicitacao foi descartada 					// DIFERENTE DE STATUS DA SOLICITACAO -> "Pendente" "Descartada" "Aprovada"
	private int id;

	
	// CONSTRUTOR
	public Item(String nome, String descricao, String contato, String img) {

		this.nome = nome;
		this.descricao = descricao;
		this.contato = contato;
		this.img = img;
		this.status = "Aguardando";
	}

	// GETTERS e SETTERS
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Override
	public String toString() {
		return id + ";" + nome + ";" + descricao + ";" + contato + ";" + img;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
