package br.ufape.edu.item;

public class Item{

	private String nome;
	private String discricao;
	private String contato; 
	private String img;
	private int id;
	
	
	public Item(String nome, String discricao, String contato, String img, int id) {
		
		this.nome = nome;
		this.discricao = discricao;
		this.contato = contato;
		this.img = img;
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDiscricao() {
		return discricao;
	}

	public void setDiscricao(String discricao) {
		this.discricao = discricao;
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
        return id + ";" + nome + ";" + discricao + ";" + contato + ";" + img;
    }

	

	
	

}
