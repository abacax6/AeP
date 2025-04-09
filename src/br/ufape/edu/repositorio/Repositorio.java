package br.ufape.edu.repositorio;

import java.util.ArrayList;

import br.ufape.edu.item.Item;
import br.ufape.edu.solicitacao.Solicitacao;
import br.ufape.edu.usuarios.Usuario;

public class Repositorio implements IRepositorio {

	private ArrayList<Usuario> usuarios = new ArrayList<>();
	private ArrayList<Item> itensAnunciados = new ArrayList<>();
	private ArrayList<Item> itensResgatados = new ArrayList<>();
	private ArrayList<Solicitacao> solicitacoes = new ArrayList<>();

	// CONTADORES PARA GERAÇÃO DE IDs
	private int UltimoIdUsuario = 0;
	private int UltimoIdItem = 0;
	private int UltimoIdSolicitacao = 0;

	// USUÁRIOS
	@Override
	public void cadastrarUsuario(Usuario usuario) {
	    for (Usuario u : usuarios) {
	        if (u.getEmail().equalsIgnoreCase(usuario.getEmail())) {
	            System.out.println("Usuário com esse e-mail já está cadastrado.");
	            return;
	        }
	    }
	    
	    usuario.setId(++UltimoIdItem);
	    usuarios.add(usuario);
	    System.out.println("Usuário cadastrado com sucesso!");
	}

	@Override
	public Usuario buscarUsuarioPorEmail(String email) { // Busca o usuário pelo e-mail
		for (Usuario u : usuarios) {
			if (u.getEmail().equals(email)) { // Se o e-mail for igual ao que se busca, então
				return u; // Retorna o usuário
			}
		}
		return null; // Se não achou, retorna nulo
	}
	
	@Override
	public Usuario buscarUsuarioPorUsername(String username) { // Busca o usuário pelo Username
	    for (Usuario u : usuarios) {
	        if (u.getUsername().equals(username)) { // Se o username for igual ao que se busca, então
	            return u; // Retorna o usuário 
	        }
	    }
	    return null; // Se não achou, retorna nulo
	}

	@Override
	public ArrayList<Usuario> listarUsuarios() {
		return usuarios;
	}

	// ITENS
	@Override
	public void cadastrarItem(Item item) {
		item.setId(++UltimoIdItem);
		itensAnunciados.add(item);
	}

	@Override
	public void moverParaResgatados(Item item) {
		itensAnunciados.remove(item);
		itensResgatados.add(item);
	}

	@Override
	public ArrayList<Item> listarItensAnunciados() { // Método que retorna o ArrayList de itens anunciados
		return itensAnunciados;
	}

	@Override
	public ArrayList<Item> listarItensResgatados() { // Método que retorna o ArrayList de itens resgatados
		return itensResgatados;
	}

	@Override
	public Item buscarItemPorId(int id) { // BUSCA DE ITEM POR ID
		for (Item i : itensAnunciados) {
			if (i.getId() == id) {
				return i;
			}
		}
		return null; // Se não achou, retorna nulo
	}

	// SOLICITAÇÕES
	@Override
	public void cadastrarSolicitacao(Solicitacao solicitacao) { // Adicionar solicitacao
		solicitacao.setId(++UltimoIdSolicitacao);
		solicitacoes.add(solicitacao);
	}

	@Override
	public ArrayList<Solicitacao> listarSolicitacoesPendentes() { // Retorna um ArrayList, lista das solicitacoes pendentes
		ArrayList<Solicitacao> pendentes = new ArrayList<>();
		for (Solicitacao s : solicitacoes) {
			if ("Pendente".equalsIgnoreCase(s.getStatus())) {// Se o Status da solicitacao for "Pendente"
				pendentes.add(s); // Adiciona a solicitacao à lista
			}
		}
		return pendentes; // Retorna a lista das solicitacoes pendentes
	}

	@Override
	public ArrayList<Solicitacao> listarTodasSolicitacoes() { // Retorna o ArrayList de todas as solicitacoes
		return solicitacoes;
	}
	
	@Override
	public Solicitacao buscarSolicitacaoPorId(int id) {
			for(Solicitacao s : solicitacoes) {
				if(s.getId() == id) {
					return s;
				}
			}
		return null;
	}
}
