package br.com.alura.loja.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ClienteDao;
import br.com.alura.loja.dao.PedidoDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Cliente;
import br.com.alura.loja.modelo.ItemPedido;
import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;
import br.com.alura.loja.vo.RelatorioDeVendasVo;

public class CadastroDePedido {

	public static void main(String[] args) {
		// pra cadastrar um pedido precisa de categoria, pra cadastrar pedido precisa de produto
		popularBancoDeDados();
		
		EntityManager em = JPAUtil.getEntityManager();
		
		ProdutoDao produtoDao = new ProdutoDao(em);
		ClienteDao clienteDao = new ClienteDao(em);
		Produto produto = produtoDao.buscarPorId(1l); // pega o produto recem salvo no banco de dados
		Cliente cliente = clienteDao.buscarPorId(1l); // pega o cliente recem salvo no bd
		
		em.getTransaction().begin();
		
		
		Pedido pedido = new Pedido(cliente); // crio pedido linkado a esse cliente
		pedido.adicionarItem(new ItemPedido(10, pedido, produto)); // adiciono esse pedido aos itens
		
		PedidoDao pedidoDao = new PedidoDao(em);
		pedidoDao.cadastrar(pedido); // esta salvando o pedido, mas nao o item_pedido. Item _pedido eh vinculado ao pedido entao sem pedido, nao tem pq ter item_pedido
		 // por isso usei o cascade para ele saber automaticamente a fazer o insert na entidade do itemPedido
		em.getTransaction().commit();
		
		BigDecimal totalVendido = pedidoDao.valorTotalVendido(); 
		System.out.println("VALOR TOTAL: " + totalVendido);
		
		List<RelatorioDeVendasVo> relatorio = pedidoDao.relatorioDeVendas();		
		relatorio.forEach(System.out::println);
	}

	private static void popularBancoDeDados() {
		Categoria celulares = new Categoria("CELULARES"); // cria categoria
		
		
		Produto celular = new Produto("XIAOMI", "MUITO TOP", new BigDecimal("800"), celulares); // cria produto
 
		
		Cliente cliente = new Cliente("Rodrigo", "123456"); // cria cliente
		
		EntityManager em = JPAUtil.getEntityManager(); // metodo static
		
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		ClienteDao clienteDao = new ClienteDao(em);
		
		
		// operações de escrita no banco de dados exige o inicio e commit de uma transação
		em.getTransaction().begin(); // inicia transacao
		categoriaDao.cadastrar(celulares); // insere primeiro a categoria, ja que ela vai estar associada ao produto
		produtoDao.cadastrar(celular); // insere na tabela produtos
		clienteDao.cadastrar(cliente);
		em.getTransaction().commit(); // finaliza transacao
		em.close();
	}

}
