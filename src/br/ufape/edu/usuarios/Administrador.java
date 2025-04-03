package br.ufape.edu.usuarios;

public class Administrador {
import br.ufape.edu.solicitacao.Solicitacao;
import java.util.ArrayList;

    
public class Administrador extends Usuario {

    // Construtor do Administrador, chamando o construtor da classe pai (Usuario)
    public Administrador(int id, String nome, String senha, String contato) {
        // Como Administrador também é um Usuario, chamamos o construtor de Usuario
        super(id, nome, senha, contato, new ArrayList<Solicitacao>());
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
        solicitacao.setStatus("Aprovada");
        System.out.println("A solicitação " + solicitacao.getId() + " foi aprovada.");
    }

    // Método para rejeitar uma solicitação
    public void rejeitarSolicitacao(Solicitacao solicitacao) {
        solicitacao.setStatus("Rejeitada");
        System.out.println("A solicitação " + solicitacao.getId() + " foi rejeitada.");
    }
}


