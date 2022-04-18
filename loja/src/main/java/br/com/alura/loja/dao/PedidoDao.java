package br.com.alura.loja.dao;

import java.math.BigDecimal;
import java.util.*;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.vo.RelatorioDeVendasVo;

public class PedidoDao {

	private EntityManager em;

	
	public PedidoDao(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Pedido pedido) {
		this.em.persist(pedido); // insere na tabela
	}
	
	// valor total dos pedidos
	public BigDecimal valorTotalVendido() {
		String jpql = "SELECT SUM(p.valorTotal) FROM Pedido AS p";
		return em.createQuery(jpql, BigDecimal.class).getSingleResult();
	}
	
	// relatorio de vendas - mostra produto, quantidade vendida e ultima venda do que mais vendeu pro que menos vendeu
	public List<RelatorioDeVendasVo> relatorioDeVendas(){ // retorna 3 informações, de entidades diferentes, por isso é uma classe que eu criei
		String jpql= "SELECT new br.com.alura.loja.vo.RelatorioDeVendasVo(produto.nome, SUM(item.quantidade), MAX(pedido.data))" // "Instancia" a classe no sql; precisa colocar antes o package dela
				+ " FROM Pedido AS pedido JOIN pedido.itens item JOIN item.produto produto" // jpa sabe fazer os relacionamentos sozinhos, já que eles tão interligados
				// itemPedido ta interligado com produto e pedido ta interligado com itemPedido
				+ " GROUP BY produto.nome ORDER BY item.quantidade DESC"; 
		return em.createQuery(jpql, RelatorioDeVendasVo.class).getResultList();
	}
	
	
}
