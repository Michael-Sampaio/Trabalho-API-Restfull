package br.org.serratec.backend.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import br.org.serratec.backend.dto.AlterarPedidoDTO;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pedido")
	private Long id;

	@Column(name = "data_pedido")
	@DateTimeFormat
	private LocalDate dataPedido;

	@Column(name = "data_entrega")
	@DateTimeFormat
	private LocalDate dataEntrega;

	@Column(name = "data_envio")
	@DateTimeFormat
	private LocalDate dataEnvio;

	@Enumerated(EnumType.STRING)
	@Column
	private Status status;

	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;

	@JsonManagedReference
	@OneToMany(mappedBy = "pedido")
	private List<PedidoItem> pedidosItem;

	@Transient
	private Double totalGeral;

	public Pedido(AlterarPedidoDTO alterarPedidoDTO) {
	}

	public Pedido() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDataPedido() {
		return this.dataPedido;
	}

	public void setDataPedido(LocalDate dataPedido) {
		this.dataPedido = dataPedido;
	}

	public LocalDate getDataEntrega() {
		return this.dataEntrega;
	}

	public void setDataEntrega(LocalDate dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public LocalDate getDataEnvio() {
		return this.dataEnvio;
	}

	public void setDataEnvio(LocalDate dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<PedidoItem> getPedidosItem() {
		return pedidosItem;
	}

	public void setPedidosItem(List<PedidoItem> pedidosItem) {
		this.pedidosItem = pedidosItem;
	}

	public Double getTotalGeral() {
		totalGeral = 0.0;
		for (PedidoItem pedidoItem : pedidosItem) {
			totalGeral += pedidoItem.getSubTotal();
		}
		return totalGeral;
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
		Pedido other = (Pedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
