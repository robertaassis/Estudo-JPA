package br.com.alura.loja.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	// instancia so uma vez
	private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("loja"); // nome do persistence unit (persistence.xml)
	
	
	// static são funções que podem ser acessados diretamente do codigo sem a necessidade de instanciar antes o objeto
	public static EntityManager getEntityManager() { //  metodo que cria EntityManager
		return FACTORY.createEntityManager();
	}
	
	 
}
