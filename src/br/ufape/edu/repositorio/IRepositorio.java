package br.ufape.edu.repositorio;

import java.util.ArrayList;

import br.ufape.edu.item.Item;
import br.ufape.edu.solicitacao.Solicitacao;
import br.ufape.edu.usuarios.Usuario;

public interface IRepositorio {

	void cadastrarUsuario(Usuario usuario);
	Usuario buscarUsuarioPorEmail(String email);
	ArrayList<Usuario> listarUsuarios();

	void cadastrarItem(Item item);
	void moverParaResgatados(Item item);
	ArrayList<Item> listarItensAnunciados();
	ArrayList<Item> listarItensResgatados();
	Item buscarItemPorId(int id);
	

	void cadastrarSolicitacao(Solicitacao solicitacao);
	void aprovarSolicitacao(Solicitacao solicitacao);
	void descartarSolicitacao(Solicitacao solicitacao);
	ArrayList<Solicitacao> listarSolicitacoesPendentes();
	ArrayList<Solicitacao> listarTodasSolicitacoes();

}
