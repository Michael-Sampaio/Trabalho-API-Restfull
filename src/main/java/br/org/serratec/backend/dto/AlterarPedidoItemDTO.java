package br.org.serratec.backend.dto;

import br.org.serratec.backend.model.PedidoItem;

public class AlterarPedidoItemDTO {

	private Long id;
	private Long id_pedido;
	private Long id_produto;
	private Integer qntProduto;
	private Double vlrUnit;
	private Double subTotal;

	public AlterarPedidoItemDTO() {
	}

	public AlterarPedidoItemDTO(PedidoItem pedidoItem) {
		super();
		this.id_pedido = pedidoItem.getPedido().getId();
		this.id_produto = pedidoItem.getProduto().getId();
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
