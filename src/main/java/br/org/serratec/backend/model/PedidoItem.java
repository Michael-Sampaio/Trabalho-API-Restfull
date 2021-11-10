package br.org.serratec.backend.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class PedidoItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_item_pedido")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_pedido")
	private Pedido pedido;
	
	@OneToOne
	@JoinColumn(name = "id_produto")
	private Produto produto;
	
	
	@Column(name = "quantidade")
	private Integer qntProduto;

	@Column(name = "preco_venda")
	private Double vlrUnit;

	
	public PedidoItem() {
		// TODO Auto-generated constructor stub
	}
	
		
	public PedidoItem(Long id, Cliente cliente, Pedido pedido, Integer qntProduto, Double vlrUnit, Double vlrTotal,
			Double vlrAcrescimo, Double vlrDesconto, Double vlrLiquido) {
		super();
		this.id = id;
		this.pedido = pedido;
		this.qntProduto = qntProduto;
		this.vlrUnit = vlrUnit;
		
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Integer getQntProduto() {
		return qntProduto;
	}

	public void setQntProduto(Integer qntProduto) {
		this.qntProduto = qntProduto;
	}

	public Double getVlrUnit() {
		return vlrUnit;
	}

	public void setVlrUnit(Double vlrUnit) {
		this.vlrUnit = vlrUnit;
	}

	
}
