package br.ufape.edu.sistema;

import br.ufape.edu.repositorio.Repositorio;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import br.ufape.edu.item.Item;
import br.ufape.edu.usuarios.Administrador;
import br.ufape.edu.usuarios.Usuario;
import br.ufape.edu.solicitacao.*;

public class Sistema {

	/* ATRIBUTOS DO SISTEMA */

	// Scanner do sistema
	Scanner sc = new Scanner(System.in);
	
	// O Sistema possui um repositório
	private Repositorio repositorio = new Repositorio();
	
	// O Sistema possui um admin
	private Administrador admin = new Administrador(0, "admin", "admin123", "admin@ufape.edu.br", "(87) 9 0000-0000",
			repositorio);
	
	/* INICIAR O SISTEMA */
	// Método que chama "o menu iniciar"
	public void iniciar() {
		repositorio.listarItensAnunciados();
		int opcao;
		do {
			System.out.println("\n===== Achados & Perdidos UFAPE =====");
			System.out.println("1. Login");
			System.out.println("2. Cadastro");
			System.out.println("0. Sair");
			System.out.print("Escolha uma opção: ");
			opcao = sc.nextInt();
			sc.nextLine(); // limpa o buffer

			switch (opcao) {
			case 1:
				login();
				break;
			case 2:
				cadastroUsuario();				
				break;
			case 0:
				System.out.println("Encerrando...");
				break;
			default:
				System.out.println("Opção inválida.");
			}

		} while (opcao != 0);

		sc.close();
	}

	/* LOGIN */
	public void login() {
		System.out.println("Insira o username: ");
		String username = sc.next();
		System.out.println("Insira a senha: ");
		String senha = sc.next();
		autenticarLogin(username, senha);
	}
	
	// Autenticação de login
	public Usuario autenticarLogin(String username, String senha) {
		Usuario usuario = repositorio.buscarUsuarioPorUsername(username);
		// LOGIN ADM
		if (username.equals(admin.getUsername()) && senha.equals(admin.getSenha())) { // Se as credenciais forem as do
																						// admin
			System.out.println("Logado como administrador.");
			exibirPainelAdmin();
			return admin;
		}
		// LOGIN USUARIO
		else if (usuario == null) { // Se não houver um usuario com esse nome
			System.out.println("Usuario inexistente!");
			return null;
		} else if (senha.equals(usuario.getSenha())) { // Se o usuário existe, e a senha for a correta
			System.out.println("Logado como usuário " + usuario.getUsername() + ".");
			exibirPainelUsuario(usuario);
			return usuario;
		} else if (!senha.equals(usuario.getSenha())) { // Se o usuário existe, e a senha não for a correta
			System.out.println("Senha incorreta! Tente novamente.");
			return null;
		}

		return null; // Se chegar até aqui, é porque houve algum erro desconhecido, caso de exception
	}
		
	/* CADASTRO */
	
	// Inputs e criação do usuário
	public void cadastroUsuario() {
		System.out.println("\n===== Cadastrar novo Usuario =====");
		String u;
		String s;
		String e;
		String t;
		// username
		while(true) {
			System.out.println("Insira o username: ");
			String username = sc.nextLine();
			if(validarUsername(username)) {
				u = username;
				break;
			}
		}

		// senha
		while(true) {
			System.out.println("Insira uma senha: ");
			String senha = sc.nextLine();
			if(validarSenha(senha)) {
				s = senha;
				break;
			}
		}

		// email
		while(true) {
			System.out.println("Insira um e-mail válido: ");
			String email = sc.nextLine();
			if(validarEmail(email)) {
				e = email;
				break;
			}
		}
		
		//telefone
		while(true) {
			System.out.println("Insira um número de telefone: ");
			String telefone = sc.nextLine();
			if(validarTelefone(telefone)) {
				t = telefone;
				break;
			}
		}
		validarCadastroUsuario(u, s, e, t);
	}
	
	// Verificação e formatação correta dos dados de cadastro de usuario
	public void validarCadastroUsuario(String username, String senha, String email, String telefone) {
        Usuario usuario = new Usuario(username, senha, email, telefone);
        repositorio.cadastrarUsuario(usuario);
    }
	
	public boolean validarUsername(String username) {
		// Validação do username
        if (username == null || !username.matches("^[a-zA-Z0-9]{3,12}$")) {
            System.out.println("Username inválido! Deve conter apenas letras e números (3-12 caracteres)");
            return false;
        }
        return true;
	}
	
	public boolean validarSenha(String senha) {
		// Validação da senha
        if (senha == null || !senha.matches("^[a-zA-Z0-9]{4,8}$")) {
            System.out.println("Senha inválida! Deve conter apenas letras e números (4-8 caracteres)");
            return false;
        }
        return true;
	}

	public boolean validarEmail(String email) {
		// Validação do email
        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            System.out.println("Email inválido!.");
            return false;
        }
        return true;
	}
	
	public boolean validarTelefone(String telefone) {
		// Formatação do telefone
		if (telefone != null) {
            String apenasNumeros = telefone.replaceAll("[^0-9]", "");
            
            if (apenasNumeros.length() < 10) { // Mínimo 10 dígitos (DDD + número)
                System.out.println("Telefone inválido! Deve conter DDD + número (mínimo 10 dígitos)");
                return false;
            }
            
            // Formatação automática
            String telefoneFormatado;
            if (apenasNumeros.length() == 10) { // Ex: 1198765432 → (11) 9876-5432
                telefoneFormatado = String.format("(%s) %s-%s",
                    apenasNumeros.substring(0, 2),
                    apenasNumeros.substring(2, 6),
                    apenasNumeros.substring(6));
            } else { // 11 dígitos (Ex: 11987654321) → (11) 98765-4321
                telefoneFormatado = String.format("(%s) %s-%s",
                    apenasNumeros.substring(0, 2),
                    apenasNumeros.substring(2, 7),
                    apenasNumeros.substring(7));
            }
            
            // System.out.println("Telefone formatado: " + telefoneFormatado);
            return true;
        }
        System.out.println("Telefone inválido! Insira um número de telefone válido");
        return false;
	}
	
	/* PAINEL ADMIN */
	// Método que chama o menu "Painel Admin"
	public void exibirPainelAdmin() {
	    int opcao;
	    do {
	        System.out.println("\n===== Painel do Administrador =====");
	        System.out.println("1. Ver solicitações pendentes");
	        System.out.println("2. Ver solicitações aprovadas");
	        System.out.println("3. Ver solicitações rejeitadas");
	        System.out.println("0. Sair do painel");
	        System.out.print("Escolha uma opção: ");
	        opcao = sc.nextInt();
	        sc.nextLine(); // limpa buffer

	        switch (opcao) {
	            case 1:
	                admin.verListaSolicitacoesPendentes();
	                break;
	            case 2:
	            	admin.verListaSolicitacoesAprovadas();
	                break;
	            case 3:
	            	admin.verListaSolicitacoesRejeitadas();
	                break;
	            case 0:
	                System.out.println("Saindo do painel do administrador...");
	                break;
	            default:
	                System.out.println("Opção inválida.");
	        }
	    } while (opcao != 0);
	}

	/* PAINEL USUARIO */
	public void exibirPainelUsuario(Usuario usuario) {
	    int opcao;
	    do {
	        System.out.println("\n===== Painel do Usuário: " + usuario.getUsername() + " =====");
	        System.out.println("1. Solicitar Cadastro de item");
	        System.out.println("2. Ver Minhas Solicitações de Cadastro");
	        System.out.println("3. Solicitar Resgate de Item");
	        System.out.println("4. Ver Minhas Solicitações de Resgate");
	        System.out.println("0. Sair do painel");
	        System.out.print("Escolha uma opção: ");
	        opcao = sc.nextInt();
	        sc.nextLine(); // limpar buffer

	        switch (opcao) {
	            case 1:
	                solicitarCadastroItem(usuario);
	                break;
	            case 2:
	            	visualizarSolicitacoesDeCadastro(usuario);
	                break;
	            case 3:
	                solicitarResgateDeItem(usuario);
	                break;
	            case 4:
	            	visualizarSolicitacoesDeResgate(usuario);
	                break;
	            case 0:
	                System.out.println("Saindo do painel do usuário...");
	                break;
	            default:
	                System.out.println("Opção inválida.");
	        }
	    } while (opcao != 0);
	}
	
	public void solicitarResgateDeItem(Usuario usuario) {
		ArrayList<Item> itens = repositorio.listarItensAnunciados();

	    if (itens.isEmpty()) {
	        System.out.println("Nenhum item disponível para resgate.");
	        return;
	    }

	    System.out.println("\nItens Disponíveis para Resgate");
	    for (Item item : itens) {
	        System.out.println("ID: " + item.getId() + " | Nome: " + item.getNome() + " | Descrição: " + item.getDescricao());
	    }
		
	    System.out.print("\nDigite o ID do item que deseja solicitar o resgate: ");
	    int itemId = sc.nextInt();
	    sc.nextLine(); // limpar buffer
	    
	    System.out.println("\n=== Solicitação de Resgate de Item ===");

		System.out.print("Descrição da situação: ");
		String descricao = sc.nextLine();

		System.out.print("Caminho da Imagem Para Comprovação (opcional): ");
		String imagem = sc.nextLine();								
		File imgFile = new File(imagem); // Verificação se o caminho da imagem existe (não obrigatório)
		if (!imgFile.exists()) {
			System.out.println("Aviso: O caminho informado não corresponde a um arquivo existente ou não foi innformado. O cadastro continuará mesmo assim.");
		}

	    Item itemSelecionado = repositorio.buscarItemPorId(itemId);

	    if (itemSelecionado != null) {
	        Solicitacao solicitacao = new Solicitacao("resgate", itemSelecionado, usuario);
	        solicitacao.setDescricaoResgate(descricao);
	        solicitacao.setImagemComprovante(imagem);
	        repositorio.cadastrarSolicitacao(solicitacao); // aqui o ID será atribuído automaticamente
	        System.out.println("Solicitação enviada com sucesso. Aguarde a aprovação do administrador.");
	    } else {
	        System.out.println("Item com o ID informado não foi encontrado.");
	    }
	}

	public void visualizarSolicitacoesDeCadastro(Usuario usuario) {
		System.out.println("\n--- Suas Solicitações de Cadastro ---");
		ArrayList<Solicitacao> todas = repositorio.listarTodasSolicitacoes();
		for (Solicitacao s : todas) {
			if (s.getSolicitante().equals(usuario) && s.getTipo().equalsIgnoreCase("cadastro")) {
				System.out.println("ID: " + s.getId() + " | Tipo: " + s.getTipo() + " | Status: " + s.getStatus());
				System.out.println("Item: " + s.getItem().getNome() + " - " + s.getItem().getDescricao());
				
				System.out.println("---------------");
			}
		}
	}
	
	public void visualizarSolicitacoesDeResgate(Usuario usuario) {
		System.out.println("\n--- Suas Solicitações de Resgate ---");
		ArrayList<Solicitacao> todas = repositorio.listarTodasSolicitacoes();
		for (Solicitacao s : todas) {
			if (s.getSolicitante().equals(usuario) && s.getTipo().equalsIgnoreCase("resgate")) {
				System.out.println("ID: " + s.getId() + " | Tipo: " + s.getTipo() + " | Status: " + s.getStatus());
				System.out.println("Item: " + s.getItem().getNome() + " - " + s.getItem().getDescricao());
				System.out.println("Imagem do item: " + s.getItem().getImg());
				System.out.println("---------------");
			}
		}
	}

	public void solicitarCadastroItem(Usuario usuario) {
		System.out.println("\n=== Solicitação de Cadastro de Item ===");

		System.out.print("Nome do item: ");
		String nome = sc.nextLine();

		System.out.print("Descrição do item: ");
		String descricao = sc.nextLine();

		System.out.print("Telefone de contato: ");
		String telefone = sc.nextLine();

		String imagem = "";
		boolean imagemValida = false;

		while (!imagemValida) {
			System.out.println("Digite o caminho ou nome da imagem do item (obrigatório):");
			imagem = sc.nextLine();

			File imgFile = new File(imagem);
			if (imagem.trim().isEmpty()) {
				System.out.println("A imagem é obrigatória. Por favor, insira um caminho válido.");
			} else if (!imgFile.exists() || imgFile.isDirectory()) {
				System.out.println(" O arquivo informado não existe ou não é uma imagem válida.");
			} else {
				imagemValida = true;
			}
		}

		Item item = new Item(nome, descricao, telefone, imagem);
		
		Solicitacao solicitacao = new Solicitacao("Cadastro", item, usuario);

		repositorio.cadastrarSolicitacao(solicitacao);
		System.out.println("Solicitação enviada com sucesso! Aguarde a aprovação do administrador.");
	}
}