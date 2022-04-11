package br.com.alura.loja.dao;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Categoria;

public class CategoriaDao {

	private EntityManager em;


	public CategoriaDao(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Categoria categoria) {
		this.em.persist(categoria); // insere na tabela
	}
	
	public void atualizar(Categoria categoria) {
		this.em.merge(categoria); // garante que vai pro estado managed pra fazer o set futuramente
	}
	
	public void remover(Categoria categoria) {
		categoria = em.merge(categoria); // precisa atribuir a uma variavel pois o (categoria) esta detached; so fica managed a variavel atribuida
		this.em.remove(categoria); // precisa estar em managed pra dar certo de fazer remove
	}
	
	
}
