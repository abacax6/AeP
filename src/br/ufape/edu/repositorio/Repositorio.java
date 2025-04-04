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

	// USUÁRIOS
	@Override
	public void cadastrarUsuario(Usuario usuario) {
		usuarios.add(usuario);
	}

	@Override
	public Usuario bucaUsuarioPorEmail(String email) {
		for (Usuario u : usuarios) {
			if (u.getContato().equals(email)) { 
				return u;
			}
		}
		return null;
	}

	@Override
	public ArrayList<Usuario> listarUsuarios() {
		return usuarios;
	}

	// ITENS
	@Override
	public void cadastrarItem(Item item) {
		itensAnunciados.add(item);
	}

	@Override
	public void moverParaResgatados(Item item) {
		itensAnunciados.remove(item);
		itensResgatados.add(item);
	}

	@Override
	public ArrayList<Item> listarItensAnunciados() {
		return itensAnunciados;
	}

	@Override
	public ArrayList<Item> listarItensResgatados() {
		return itensResgatados;
	}

	@Override
	public Item buscarItemPorId(int id) {
		for (Item i : itensAnunciados) {
			if (i.getId() == id) {
				return i;
			}
		}
		return null;
	}

	// SOLICITAÇÕES
	@Override
	public void cadastrarSolicitacao(Solicitacao solicitacao) {
		solicitacoes.add(solicitacao);
	}

	@Override
	public void aprovarSolicitacao(Solicitacao solicitacao) {
		solicitacao.setStatus("Aprovada");
	}

	@Override
	public void descartarSolicitacao(Solicitacao solicitacao) {
		solicitacao.setStatus("Descartada");
	}

	@Override
	public ArrayList<Solicitacao> listarSolicitacoesPendentes() {
		ArrayList<Solicitacao> pendentes = new ArrayList<>();
		for (Solicitacao s : solicitacoes) {
			if ("Pendente".equalsIgnoreCase(s.getStatus())) {
				pendentes.add(s);
			}
		}
		return pendentes;
	}

	@Override
	public ArrayList<Solicitacao> listarTodasSolicitacoes() {
		return solicitacoes;
	}
}
