package br.com.alura.loja.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDeProduto {
	public static void main(String[] args) {
		cadastrarProduto();
		
		EntityManager em = JPAUtil.getEntityManager(); // metodo static
		
		ProdutoDao produtoDao = new ProdutoDao(em);
		
		Produto p = produtoDao.buscarPorId(1l);
		System.out.println(p.getPreco());
		
		List<Produto> todos = produtoDao.buscarTodos();
		todos.forEach(p2-> System.out.println(p2.getNome()));
		
		List<Produto> todosPorNome = produtoDao.buscarPorNome(p.getNome());
		todosPorNome.forEach(p3-> System.out.println(p3.getNome()));
		
		List<Produto> todosPorCelulares = produtoDao.buscarPorNomeCategoria("CELULARES");
		todosPorCelulares.forEach(p3-> System.out.println(p3.getNome()));
		
		BigDecimal precoDoProduto = produtoDao.buscarPorNomeDevolvePreco(p.getNome());
		System.out.println(precoDoProduto);
		
	}

	private static void cadastrarProduto() {
		Categoria celulares = new Categoria("CELULARES"); // instancio a entidade
		
		
		Produto celular = new Produto("XIAOMI", "MUITO TOP", new BigDecimal("800"), celulares);

		
		EntityManager em = JPAUtil.getEntityManager(); // metodo static
		
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);

		
		// operações de escrita no banco de dados exige o inicio e commit de uma transação
		em.getTransaction().begin(); // inicia transacao
		categoriaDao.cadastrar(celulares); // insere primeiro a categoria, ja que ela vai estar associada ao produto
		produtoDao.cadastrar(celular); // insere na tabela produtos
		em.getTransaction().commit(); // finaliza transacao
		em.close();
	}
}
