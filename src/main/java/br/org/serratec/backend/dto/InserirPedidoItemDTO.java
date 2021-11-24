package br.org.serratec.backend.dto;

import br.org.serratec.backend.model.Pedido;
import br.org.serratec.backend.model.PedidoItem;
import br.org.serratec.backend.model.Produto;

public class InserirPedidoItemDTO {

	private Long id;
	private Pedido pedido;
	private Produto produto;
	private Integer qntProduto;
	private Double vlrUnit;
	private Double subTotal;

	public InserirPedidoItemDTO() {
		super();
	}

	public InserirPedidoItemDTO(PedidoItem pedidoItem) {
		super();
		this.pedido = pedidoItem.getPedido();
		this.produto = pedidoItem.getProduto();
		this.qntProduto = pedidoItem.getQntProduto();
		this.vlrUnit = pedidoItem.getVlrUnit();
		this.subTotal = pedidoItem.getSubTotal();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pedido getPedido() {
		return this.pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Produto getProduto() {
		return this.produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
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
