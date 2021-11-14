package br.org.serratec.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.org.serratec.backend.dto.AlterarPedidoItemDTO;

@Entity
@Table(name = "item_pedido")
public class PedidoItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_item_pedido")
	private Long id;

	@JsonBackReference
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

	@Transient
	private Double subTotal;

	public PedidoItem() {
	}

	public PedidoItem(Long id, Pedido pedido, Produto produto, Integer qntProduto, Double vlrUnit, Double subTotal) {
		super();
		this.id = id;
		this.pedido = pedido;
		this.produto = produto;
		this.qntProduto = qntProduto;
		this.vlrUnit = vlrUnit;
		this.subTotal = subTotal;
	}

	public PedidoItem(AlterarPedidoItemDTO alterarPedidoItemDTO) {
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Double getSubTotal() {
		subTotal = vlrUnit * qntProduto;
		return subTotal;
	}

	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PedidoItem other = (PedidoItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
