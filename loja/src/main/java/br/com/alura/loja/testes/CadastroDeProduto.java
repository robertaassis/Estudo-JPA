package br.com.alura.loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.loja.modelo.Produto;

public class CadastroDeProduto {
	public static void main(String[] args) {
		Produto celular = new Produto();
		celular.setNome("XIAOMI REDMI");
		celular.setDescricao("Muito top");
		celular.setPreco(new BigDecimal("800"));
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("loja"); // nome do persistence unit (persistence.xml)
		EntityManager em = factory.createEntityManager(); // sempre que for pra fazer qualquer operação no banco precisa instanciar essa interface
		
		
		// operações de escrita no banco de dados exige o inicio e commit de uma transação
		em.getTransaction().begin(); // inicia transacao
		em.persist(celular); // insere na tabela produtos
		em.getTransaction().commit(); // finaliza transacao
		em.close();
	}
}
