package br.com.alura.loja.testes;

import java.math.BigDecimal;

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
		Categoria celulares = new Categoria("CELULARES"); // instancio a categoria
		
		
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
