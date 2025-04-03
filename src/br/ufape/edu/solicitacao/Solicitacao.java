package br.ufape.edu.solicitacao;
import br.ufape.edu.item.Item;
import br.ufape.edu.usuarios.Usuario;

public class Solicitacao {
	// ATRIBUTOS 
	private int id; // id da solicitação
	private String tipo; // tipo dessa solicitação
	private Item item; // item relacionado a solicitação
	private String status; // status da solicitação
	private Usuario solicitante; // usuario criou a solicitação
	
	// CONSTRUTOR
	public Solicitacao(int id, String tipo, Item item, Usuario usuario) {
		setId(id);
		setTipo(tipo);
		setItem(item);
		setSolicitante(usuario);
	}

	// GETTERS E SETTERS
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Usuario getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(Usuario solicitante) {
		this.solicitante = solicitante;
	}
	
}
