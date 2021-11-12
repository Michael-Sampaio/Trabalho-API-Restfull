package br.org.serratec.backend.dto;

public class InserirPedidoItem {

	private Long id;
	private Long id_pedido;
	private Long id_produto;
	private Integer qntProduto;
	private Double vlrUnit;
	private Double subTotal;

	public InserirPedidoItem() {
		super();
	}

	public InserirPedidoItem(Long id, Long id_pedido, Long id_produto, Integer qntProduto, Double vlrUnit,
			Double subTotal) {
		super();
		this.id = id;
		this.id_pedido = id_pedido;
		this.id_produto = id_produto;
		this.qntProduto = qntProduto;
		this.vlrUnit = vlrUnit;
		this.subTotal = subTotal;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId_pedido() {
		return id_pedido;
	}

	public void setId_pedido(Long id_pedido) {
		this.id_pedido = id_pedido;
	}

	public Long getId_produto() {
		return id_produto;
	}

	public void setId_produto(Long id_produto) {
		this.id_produto = id_produto;
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

	public Double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
	}

}
