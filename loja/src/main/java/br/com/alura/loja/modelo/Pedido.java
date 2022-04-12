package br.com.alura.loja.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity 
@Table(name = "pedidos")
public class Pedido {

	@Id // informando que id é chave primaria
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="valor_total") // esse vai ser o nome dele na tabela
	private BigDecimal valorTotal;
	private LocalDate data = LocalDate.now();
	
	@ManyToOne
	private Cliente cliente; // chave estrangeira
	
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL) /* um pedido vai ter varios itens; precisa colocar mappedby pedido (nome do atributo) pq la na entidade ItemPedido ja esta mapeado. 
	Se eu nao colocasse mappedby, ele acharia que eh um novo relacionamento e criaria uma nova tabela. RELACIONAMENTO BIDIRECIONAL.
	CASCADETYPE ALL DIZ QUE TUDO QUE EU FIZER EM UM PEDIDO FAZ TAMBÉM NO ITEM_PEDIDO, OU SEJA, removar/cadastrar etc um pedido, ja faz também no itemPedido.
	Cascade propaga as operações realizadas em uma entidade em seu relacionamento*/
	private List<ItemPedido> itens = new ArrayList<>(); // inicializa lista vazia
	
	public Pedido() {
		super();
	}
	
	public void adicionarItem(ItemPedido item) {
		item.setPedido(this); // setava o pedido
		this.itens.add(item); // guarda na lista de itens
	}
	
	public Pedido(Cliente cliente) {
		this.cliente = cliente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
	
}
