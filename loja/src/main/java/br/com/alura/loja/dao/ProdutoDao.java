package br.com.alura.loja.dao;

import java.math.BigDecimal;
import java.util.*;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Produto;

public class ProdutoDao {

	private EntityManager em;

	
	public ProdutoDao(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Produto produto) {
		this.em.persist(produto); // insere na tabela
	}
	
	public void atualizar(Produto produto) {
		this.em.merge(produto);
	}
	
	public void remover(Produto produto) {
		produto = em.merge(produto);
		this.em.remove(produto);
	}
	
	public Produto buscarPorId(Long id) {
		return em.find(Produto.class, id); // primeiro parametro é quem é a entidade
	}
	
	public List<Produto> buscarTodos(){
		String jpql = "SELECT p FROM Produto AS p "; // carrega o proprio objeto p da entidade Produto; passa o nome da entidade, nao da tabela
		return em.createQuery(jpql, Produto.class).getResultList(); // infere que vai devolver uma lista do tipo Produto (por isso ta como Produto.class); monta a query e getResultList dispara no banco de dados
	}
	
	
	public List<Produto> buscarPorNome(String nome){
		String jpql = "SELECT p FROM Produto AS p WHERE p.nome = :nome"; // coloca o nome do atributo na entidade, nao da coluna na tabela
		return em.createQuery(jpql, Produto.class).setParameter("nome", nome).getResultList(); // informa que :nome vai receber o parametro nome
	}
	
	public List<Produto> buscarPorNomeCategoria(String nome){
		String jpql = "SELECT p FROM Produto AS p WHERE p.categoria.nome = :nome"; // ja sabe automaticamente que eh um relacionamento, entao nao precisa fazer o join manual; chama o nome da categoria sem ter que fazer o join
		return em.createQuery(jpql, Produto.class).setParameter("nome", nome).getResultList(); // informa que :nome vai receber o parametro nome
	}
	
	
	public BigDecimal buscarPorNomeDevolvePreco(String nome){ // como vai devolver preco e ele esta como bigDecimal na entidade, entao é bigDecimal
		String jpql = "SELECT p.preco FROM Produto AS p WHERE p.nome = :nome"; // traz so o preco
		return em.createQuery(jpql, BigDecimal.class).setParameter("nome", nome).getSingleResult(); // devolve um unico registro BigDecimal e nao lista o getSingleResult
	}
	
	
	
}
