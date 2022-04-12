package br.com.alura.loja.dao;

import java.math.BigDecimal;
import java.util.*;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.modelo.Produto;

public class PedidoDao {

	private EntityManager em;

	
	public PedidoDao(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Pedido pedido) {
		this.em.persist(pedido); // insere na tabela
	}
	
	
	
	
	
}
