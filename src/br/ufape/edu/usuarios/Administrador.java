package br.ufape.edu.usuarios;

import br.ufape.edu.solicitacao.Solicitacao;
import java.util.ArrayList;
import java.util.Scanner;

import br.ufape.edu.repositorio.Repositorio;

/**
 * Representa o administrador do sistema Achados e Perdidos com permissões especiais.
 * Responsável por gerenciar solicitações de cadastro e resgate de itens.
 * 
 * @author Michael Raul
 */

public class Administrador extends Usuario {
	private Repositorio repositorio; // Repositorio, será o mesmo do Sistema
	Scanner sc = new Scanner(System.in); // SCANNER ESTÁ GLOBAL NA CLASSE (creio que em Sistema tbm esteja)

	
	 /**
     * Constrói um administrador com acesso ao repositório do sistema.
     * @param id Identificador único
     * @param username Nome de usuário
     * @param senha Senha de acesso
     * @param email Email institucional
     * @param telefone Contato telefônico
     * @param repositorio Referência ao repositório do sistema
     */
	// Construtor do Administrador, chamando o construtor da classe pai (Usuario)
	public Administrador(int id, String username, String senha, String email, String telefone,
			Repositorio repositorio) {
		// Como Administrador também é um Usuario, chamamos o construtor de Usuario
		super(username, senha, email, telefone);
		this.repositorio = repositorio;
	}

	 /**
     * Exibe detalhes completos de uma solicitação e opções de aprovação/rejeição.
     * @param s Solicitação a ser visualizada
     */
	// Método para visualizar uma solicitação específica
	public void visualizarSolicitacao(Solicitacao s) {
		System.out.println("\n=== Você está visualizando a solicitação " + s.getId() + " ===");
		System.out.println("\nID: " + s.getId());
		System.out.println("Tipo: " + s.getTipo());
		System.out.println("Status: " + s.getStatus());
		System.out.println("Solicitante: " + s.getSolicitante().getUsername());
		System.out.println("Item: " + s.getItem().getNome());
		System.out.println("ItemID: " + s.getItem().getId());
		System.out.println("Descrição: " + s.getItem().getDescricao());
		
		if(s.getStatus().equals("Pendente")) {		
			System.out.print("=== Aprovar (A), Rejeitar (R) ou Voltar (V)? === \n");
			String resposta = sc.nextLine();
			  if (resposta.equalsIgnoreCase("A")) {
	              aprovarSolicitacao(s);      
	          } else if (resposta.equalsIgnoreCase("R")){
	        	  descartarSolicitacao(s);       	  
	          }	else if (resposta.equalsIgnoreCase("V")) {
	        	  return;
	          }
		} else if((s.getStatus().equals("Aprovada") || s.getStatus().equals("Descartada"))) {
				System.out.print("=== Voltar (V)? === \n");
				String resposta = sc.nextLine();
				if (resposta.equalsIgnoreCase("V")) {
					return;
				}
		}
	}

	 /**
     * Lista todas as solicitações pendentes e permite selecionar uma para ação.
     */
	// Método para visualizar solicitações pendentes
	public void verListaSolicitacoesPendentes() {
		System.out.println("\n=== Solicitações Pendentes ===");
		ArrayList<Solicitacao> pendentes = repositorio.listarSolicitacoesPendentes();

		if (pendentes.isEmpty()) {
			System.out.println("Nenhuma solicitação pendente.");
			return;
		}

		for (Solicitacao s : pendentes) {
			System.out.println("\nID: " + s.getId());
			System.out.println("Tipo: " + s.getTipo());
			System.out.println("Status: " + s.getStatus());
			System.out.println("Solicitante: " + s.getSolicitante().getUsername());
			System.out.println("Item: " + s.getItem().getNome());
			System.out.println("ItemID: " + s.getItem().getId());
			System.out.println("Descrição: " + s.getItem().getDescricao());
			System.out.print("=== ================== ===\n");
			
		}
		System.out.println("Digite o ID da solicitação que deseja visualizar: ");
		int resposta = sc.nextInt();
		sc.nextLine(); // Limpeza do \n (buffer)

		if (resposta == repositorio.buscarSolicitacaoPorId(resposta).getId()) {
			visualizarSolicitacao(repositorio.buscarSolicitacaoPorId(resposta));
			
		} else {
			// Exception? (Id inválido, Solicitacao inexistente, etc?)

		}
	}
	
	/**
     * Lista solicitações aprovadas com informações resumidas.
     */
	// Método para visualizar solicitações aprovadas
	public void verListaSolicitacoesAprovadas() {
		System.out.println("\n=== Solicitações Aprovadas ===");
		ArrayList<Solicitacao> aprovadas = repositorio.listarSolicitacoesAprovadas();

		if (aprovadas.isEmpty()) {
			System.out.println("Nenhuma solicitação aprovada até o momento.");
			return;
		}

		for (Solicitacao s : aprovadas) {
			System.out.println("\nID: " + s.getId());
			System.out.println("Tipo: " + s.getTipo());
			System.out.println("Status: " + s.getStatus());
			System.out.println("Solicitante: " + s.getSolicitante().getUsername());
			System.out.println("Item: " + s.getItem().getNome());
			System.out.println("ItemID: " + s.getItem().getId());
			System.out.println("Descrição: " + s.getItem().getDescricao());
			System.out.print("=== ================== ===\n");
			
		}
		System.out.print("=== Voltar (V)? === \n");
		String resposta = sc.nextLine();
		if (resposta.equalsIgnoreCase("V")) {
			return;
		}
		
	}
	
	/**
     * Lista solicitações rejeitadas com informações resumidas.
     */
	// Método para visualizar solicitações reprovadas
	public void verListaSolicitacoesRejeitadas() {
		System.out.println("\n=== Solicitações Rejeitadas ===");
		ArrayList<Solicitacao> reprovadas = repositorio.listarSolicitacoesRejeitadas();

		if (reprovadas.isEmpty()) {
			System.out.println("Nenhuma solicitação descartada até o momento.");
			return;
		}

		for (Solicitacao s : reprovadas) {
			System.out.println("\nID: " + s.getId());
			System.out.println("Tipo: " + s.getTipo());
			System.out.println("Solicitante: " + s.getSolicitante().getUsername());
			System.out.println("Item: " + s.getItem().getNome());
			System.out.println("ItemID: " + s.getItem().getId());
			System.out.println("Descrição: " + s.getItem().getDescricao());
			System.out.print("=== ================== ===\n");
			
		}
		System.out.print("=== Voltar (V)? === \n");
		String resposta = sc.nextLine();
		if (resposta.equalsIgnoreCase("V")) {
			return;
		}
		
	}

	 /**
     * Aprova uma solicitação baseada em seu tipo (Cadastro/Resgate).
     * @param solicitacao Solicitação a ser aprovada
     */
	// Método para aprovar uma solicitação (INCORPORA TANTO APROVAR CADASTRO COMO RESGATE, COM BASE NO TIPO)
	void aprovarSolicitacao(Solicitacao solicitacao) {
		// Identificar qual o tipo da solicitação
		if (solicitacao.getTipo().equalsIgnoreCase("Cadastro")) {
			aprovarSolicitacaoCadastro(solicitacao);
		}
		else if(solicitacao.getTipo().equalsIgnoreCase("Resgate")) {
			aprovarSolicitacaoResgate(solicitacao);
		}

	}

	 /**
     * Aprova especificamente solicitações de cadastro de itens.
     * @param solicitacao Solicitação do tipo "Cadastro"
     */
	// Método para aprovar uma solicitação de cadastro
	public void aprovarSolicitacaoCadastro(Solicitacao solicitacao) {
		// Se a solicitação tiver o status "Pendente" e o item tiver o status
		// "Aguardando", então:
		if (solicitacao.getStatus().equalsIgnoreCase("Pendente")
				&& solicitacao.getItem().getStatus().equalsIgnoreCase("Aguardando")) {
			solicitacao.setStatus("Aprovada");
			solicitacao.getItem().setStatus("Anunciado"); // STATUS DO ITEM, AO SER APROVADO = "ANUNCIADO"
			repositorio.cadastrarItem(solicitacao.getItem());
			System.out.println("\nSolicitação de cadastro do item de id " + solicitacao.getItem().getId() + " aprovada com sucesso.");
			return;
		} else {
			// Se não deu certo, alguma Exception ()
			return;
		}
	}

	/**
     * Aprova especificamente solicitações de resgate de itens.
     * @param solicitacao Solicitação do tipo "Resgate"
     */
	// Método para aprovar uma solicitação de resgate
	public void aprovarSolicitacaoResgate(Solicitacao solicitacao) {
		// Se a solicitação tiver o status "Pendente" e o item tiver o status
		// "Anunciado", então:
		if (solicitacao.getStatus().equalsIgnoreCase("Pendente")
				&& solicitacao.getItem().getStatus().equalsIgnoreCase("Anunciado")) {
			solicitacao.setStatus("Aprovada");
			solicitacao.getItem().setStatus("Resgatado");
			repositorio.moverParaResgatados(solicitacao.getItem());
			System.out.println("\nSolicitação de resgate do item de id " + solicitacao.getItem().getId() + " aprovada com sucesso.");
		} else {
			// Se não deu certo, alguma Exception (Item não existe, Item já foi resgatado,
			// etc)
			return;
		}
	}

	/**
     * Rejeita uma solicitação pendente, marcando-a como descartada.
     * @param solicitacao Solicitação a ser rejeitada
     */
	// Método para rejeitar uma solicitação
	public void descartarSolicitacao(Solicitacao solicitacao) {
		if (solicitacao.getStatus().equalsIgnoreCase("Pendente")) {
			solicitacao.setStatus("Descartada");
			System.out.println("Solicitação " + solicitacao.getId() + " descartada com sucesso.");
		} else {
			// Se não deu certo, jogar alguma Exception? (Solicitação, Item já foi resgatado, etc)
			return;
		}
	}
}
