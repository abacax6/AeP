package br.ufape.edu.item;

public class Item{

	private String nome;
	private String descricao;
	private String contato; 
	private String img;
	private int id;
	
	
	public Item(String nome, String descricao, String contato, String img, int id) {
		
		this.nome = nome;
		this.descricao = descricao;
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

	

	
	

}
