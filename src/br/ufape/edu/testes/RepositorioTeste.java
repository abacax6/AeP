package br.ufape.edu.testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import br.ufape.edu.repositorio.Repositorio;
import br.ufape.edu.solicitacao.Solicitacao;
import br.ufape.edu.usuarios.Usuario;
import br.ufape.edu.item.Item;

/**
 * Classe de testes para a classe Repositorio.
 * Contém testes de unidade e integração para verificar o comportamento do repositório.
 * 
 * @author Victor Dias
 */

class RepositorioTeste {
    
    private Repositorio repositorio;
    private Usuario usuario;
    private Item item;
    private Solicitacao solicitacao;
    
    /**
     * Teste de unidade que verifica a geração automática e incremental de IDs de usuário.
     * Critérios:
     * - O primeiro usuário cadastrado deve receber ID 1
     * - O segundo usuário cadastrado deve receber ID 2
     */
    @Test // TESTE DE UNIDADE - GERAÇÃO AUTOMÁTICA E INCREMENTAL DO ID DE UM USUÁRIO CADASTRADO
    void quandoCadastrarUsuario_deveGerarIdIncremental() {
        Repositorio repo = new Repositorio();
        Usuario usuario1 = new Usuario("user1", "senha1", "email1@test.com", "(11) 11111-1111");
        Usuario usuario2 = new Usuario("user2", "senha2", "email2@test.com", "(22) 22222-2222");

        repo.cadastrarUsuario(usuario1);
        repo.cadastrarUsuario(usuario2);

        assertEquals(1, usuario1.getId());
        assertEquals(2, usuario2.getId());
    }
    
    /**
     * Teste de integração que verifica o efeito do cadastro de solicitação:
     * - Deve adicionar à lista de solicitações
     * - Deve atribuir ID automático ao item relacionado
     */
    @Test // TESTE DE INTEGRAÇÃO - AO GERAR UMA SOLICITAÇÃO DE CADASTRO, DEVEM SER ATUALIZADOS: A LISTA DE SOLICITAÇÕES E O ID DO ITEM A SER CADASTRADO
    void quandoCadastrarSolicitacao_deveAtualizarItensESolicitacoes() {
        Repositorio repo = new Repositorio();
        Usuario usuario = new Usuario("user", "senha", "email@test.com", "(11) 11111-1111");
        Item item = new Item("Livro", "Java 101", "(11) 11111-1111", "livro.jpg");
        Solicitacao solicitacao = new Solicitacao("Cadastro", item, usuario);

        repo.cadastrarSolicitacao(solicitacao);

        assertAll(
            () -> assertEquals(1, repo.listarTodasSolicitacoes().size()),
            () -> assertEquals(1, item.getId()) // ID gerado automaticamente
        );
    }
}