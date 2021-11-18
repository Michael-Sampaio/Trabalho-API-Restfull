package br.org.serratec.backend.dto;

import java.io.Serializable;

import br.org.serratec.backend.model.Pedido;
import br.org.serratec.backend.model.PedidoItem;
import br.org.serratec.backend.model.Produto;

public class InserirPedidoItemDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1451153263290931159L;

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

	public Pedido getId_pedido() {
		return pedido;
	}

	public void setId_pedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Produto getId_produto() {
		return produto;
	}

	public void setId_produto(Produto produto) {
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
