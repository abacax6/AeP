package br.ufape.edu.usuarios;

import br.ufape.edu.solicitacao.Solicitacao;
import java.util.ArrayList;
import java.util.Scanner;

import br.ufape.edu.repositorio.Repositorio;

public class Administrador extends Usuario {
	private Repositorio repositorio; // Repositorio, será o mesmo do Sistema
	Scanner sc = new Scanner(System.in); // SCANNER ESTÁ GLOBAL NA CLASSE (creio que em Sistema tbm esteja)

	// Construtor do Administrador, chamando o construtor da classe pai (Usuario)
	public Administrador(int id, String username, String senha, String email, String telefone,
			Repositorio repositorio) {
		// Como Administrador também é um Usuario, chamamos o construtor de Usuario
		super(username, senha, email, telefone);
		this.repositorio = repositorio;
	}

	// Método para visualizar uma solicitação específica
	public void visualizarSolicitacao(Solicitacao s) {
		System.out.println("\nID: " + s.getId());
		System.out.println("Tipo: " + s.getTipo());
		System.out.println("Solicitante: " + s.getSolicitante().getUsername());
		System.out.println("Item: " + s.getItem().getNome());
		System.out.println("ItemID: " + s.getItem().getId());
		System.out.println("Descrição: " + s.getItem().getDescricao());
		System.out.print("\n=== Aprovar (A) ou Rejeitar (R)? ===");
		sc.nextLine();
		String resposta = sc.nextLine();
		  if (resposta.equalsIgnoreCase("A")) {
              aprovarSolicitacao(s);      
              System.out.println("Solicitação aprovada.");
          } else {
        	  descartarSolicitacao(s);
        	  System.out.println("Solicitação rejeitada.");
          }
	}

	// Método para visualizar solicitações pendentes
	public void visualizarSolicitacoesPendentes() {
		System.out.println("\n=== Solicitações Pendentes ===");
		ArrayList<Solicitacao> pendentes = repositorio.listarSolicitacoesPendentes();

		if (pendentes.isEmpty()) {
			System.out.println("Nenhuma solicitação pendente.");
			return;
		}

		for (Solicitacao s : pendentes) {
			System.out.println("\nID: " + s.getId());
			System.out.println("Tipo: " + s.getTipo());
			System.out.println("Solicitante: " + s.getSolicitante().getUsername());
			System.out.println("Item: " + s.getItem().getNome());
			System.out.println("ItemID: " + s.getItem().getId());
			System.out.println("Descrição: " + s.getItem().getDescricao());
			if (s.getTipo().equalsIgnoreCase("Cadastro")) {
				if (s.getItem().getImg() != null && !s.getItem().getImg().isEmpty()) { // Imagem do item (caso tenha sido incluída)
					System.out.println("Imagem do item: " + s.getItem().getImg());
				} else {
					System.out.println("Imagem do item: Não informada.");
				}
			} else if (s.getTipo().equalsIgnoreCase("Resgate")) { 
				if (s.getImagemComprovante() != null && !s.getImagemComprovante().isEmpty()) {// Imagem do comprovante (se tiver)
					System.out.println("Comprovante de resgate: " + s.getImagemComprovante());
				} else {
					System.out.println("Comprovante de resgate: Não enviado.");
				}

				if (s.getDescricaoResgate() != null && !s.getDescricaoResgate().isEmpty()) { // Descrição da situação do resgate
					System.out.println("Descrição da situação: " + s.getDescricaoResgate());
				} else {
					System.out.println("Descrição da situação: Não informada.");
				}
			}
			System.out.print("=== ================== ===\n");
			
		}
		System.out.println("Digite o ID da solicitação que deseja visualizar: ");
		int resposta = sc.nextInt();

		if (resposta == repositorio.buscarSolicitacaoPorId(resposta).getId()) {
			visualizarSolicitacao(repositorio.buscarSolicitacaoPorId(resposta));
		} else {
			// Exception? (Id inválido, Solicitacao inexistente, etc?)

		}
	}

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

	// Método para aprovar uma solicitação de cadastro
	public void aprovarSolicitacaoCadastro(Solicitacao solicitacao) {
		// Se a solicitação tiver o status "Pendente" e o item tiver o status
		// "Aguardando", então:
		if (solicitacao.getStatus().equalsIgnoreCase("Pendente")
				&& solicitacao.getItem().getStatus().equalsIgnoreCase("Aguardando")) {
			solicitacao.setStatus("Aprovada");
			solicitacao.getItem().setStatus("Anunciado"); // STATUS DO ITEM, AO SER APROVADO = "ANUNCIADO"
			repositorio.cadastrarItem(solicitacao.getItem());
			System.out.println(
					"Solicitação de cadastro do item de id " + solicitacao.getItem().getId() + " aprovada com sucesso.");
		} else {
			// Se não deu certo, alguma Exception ()
			return;
		}
	}

	// Método para aprovar uma solicitação de resgate
	public void aprovarSolicitacaoResgate(Solicitacao solicitacao) {
		// Se a solicitação tiver o status "Pendente" e o item tiver o status
		// "Anunciado", então:
		if (solicitacao.getStatus().equalsIgnoreCase("Pendente")
				&& solicitacao.getItem().getStatus().equalsIgnoreCase("Anunciado")) {
			solicitacao.setStatus("Aprovada");
			solicitacao.getItem().setStatus("Resgatado");
			repositorio.moverParaResgatados(solicitacao.getItem());
			System.out.println(
					"Solicitação de resgate do item de id" + solicitacao.getItem().getId() + " aprovada com sucesso.");
		} else {
			// Se não deu certo, alguma Exception (Item não existe, Item já foi resgatado,
			// etc)
			return;
		}
	}

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
