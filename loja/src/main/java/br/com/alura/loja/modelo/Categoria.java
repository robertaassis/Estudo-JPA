package br.com.alura.loja.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // tabela de banco de dados é um espelho dessa classe; é uma entidade da JPA (tem uma tabela no banco de dados representando ela)
@Table(name = "categorias") // explicitando o nome da tabela
public class Categoria {
	
	@Id // informando que id é chave primaria
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	
	public Categoria(String nome) { // nao precisa do id pois é auto
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
	
}
