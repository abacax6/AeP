package br.ufape.edu.usuarios;
import br.ufape.edu.solicitacao.Solicitacao;
import java.util.ArrayList;
import br.ufape.edu.repositorio.IRepositorio;


public class Administrador extends Usuario {
	private IRepositorio repositorio;
    // Construtor do Administrador, chamando o construtor da classe pai (Usuario)
    public Administrador(int id, String nome, String senha, String contato, IRepositorio repositorio) {
        // Como Administrador também é um Usuario, chamamos o construtor de Usuario
        super(id, nome, senha, contato, new ArrayList<Solicitacao>());
        this.repositorio = repositorio;
    }

    // Método para visualizar uma solicitação específica
    public void visualizarSolicitacao(Solicitacao solicitacao) {
        System.out.println("\n===== Detalhes da Solicitação =====");
        System.out.println("ID: " + solicitacao.getId());
        System.out.println("Tipo: " + solicitacao.getTipo());
        System.out.println("Item: " + solicitacao.getItem());
        System.out.println("Status: " + solicitacao.getStatus());
        System.out.println("Solicitante: " + solicitacao.getSolicitante().getNome());
    }

    // Método para aprovar uma solicitação
    public void aprovarSolicitacao(Solicitacao solicitacao) {
    	 if (solicitacao.getStatus().equalsIgnoreCase("Pendente")) {
             solicitacao.setStatus("Aprovada");
             repositorio.moverParaResgatados(solicitacao.getItem());
             System.out.println("Solicitação " + solicitacao.getId() + " aprovada com sucesso.");
         } else {
             System.out.println("Solicitação " + solicitacao.getId() + " não pode ser aprovada. Status atual: " + solicitacao.getStatus());
         }
     }

    // Método para rejeitar uma solicitação
    public void rejeitarSolicitacao(Solicitacao solicitacao) {
    	 if (solicitacao.getStatus().equalsIgnoreCase("Pendente")) {
             solicitacao.setStatus("Rejeitada");
             System.out.println("Solicitação " + solicitacao.getId() + " rejeitada com sucesso.");
         } else {
             System.out.println("Solicitação " + solicitacao.getId() + " não pode ser rejeitada. Status atual: " + solicitacao.getStatus());
         }
     }
 } 


