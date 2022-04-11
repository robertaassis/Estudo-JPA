package br.com.alura.loja.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

// entidade é uma classe que faz o mapeamento de uma tabela do banco de dados

@Entity // tabela de banco de dados é um espelho dessa classe; é uma entidade da JPA (tem uma tabela no banco de dados representando ela)
@Table(name = "produtos") // explicitando o nome da tabela

public class Produto {
	
	// colunas do banco
	
	@Id // informando que id é chave primaria
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String descricao;
	private BigDecimal preco;
	private LocalDate dataCadastro = LocalDate.now(); // pega a data atual sempre que criar o produto
	
	 // @Enumerated(EnumType.STRING) // coloco enumtype.string porque se eu colocar enumtype.original, ele vai colocar na tabela baseado na ordem do enum (int). 
	/* Ex: no enum ta CELULARES, INFORMATICA, LIVROS. Se eu instanciasse um produto como CELULARES, ele salvaria na coluna como id 1, pq é o primeiro na ordem
	 como eu coloquei STRING, ele salva na coluna como CELULARES (varchar) */
	
	@ManyToOne // eu só preciso colocar a cardinalidade. Como um produto só pode ter uma categoria e vários produtos podem ter a mesma categoria então é n:1
	private Categoria categoria; /* nao preciso informar que categoria é uma chave estrangeira pois por ser do tipo Categoria, 
	o JPA já acessa ela e ve que é de uma tabela diferente, então já entende o relacionamento dessas duas tabelas sozinho
	ja que ta aqui como atributo, porem é de outro tipo e nesse outro tipo, é de outra tabela */
	
	

	public Produto() {
		super();
	}
	
	public Produto(String nome, String descricao, BigDecimal preco, Categoria categoria) {
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.categoria = categoria;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	public LocalDate getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	
	

}
