package br.org.serratec.backend.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

import br.org.serratec.backend.model.Cliente;
import br.org.serratec.backend.model.Pedido;
import br.org.serratec.backend.model.PedidoItem;
import br.org.serratec.backend.model.Status;

@NotBlank
@Embeddable
public class PedidoDTO implements Serializable {

	private static final long serialVersionUID = 3015792880071967124L;

	private LocalDate dataPedido;
	private LocalDate dataEntrega;
	private LocalDate dataEnvio;
	private Status status;
	private Cliente cliente;
	private List<PedidoItem> pedidosItem;
	private Double totalGeral;

	public PedidoDTO() {
	}

	public PedidoDTO(Pedido pedido) {
		super();
		this.dataPedido = pedido.getDataPedido();
		this.dataEntrega = pedido.getDataEntrega();
		this.dataEnvio = pedido.getDataEnvio();
		this.status = pedido.getStatus();
		this.cliente = pedido.getCliente();
		this.pedidosItem = pedido.getPedidosItem();
		this.totalGeral = pedido.getTotalGeral();
		this.pedidosItem = pedido.getPedidosItem();
	}

	public LocalDate getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(LocalDate dataPedido) {
		this.dataPedido = dataPedido;
	}

	public LocalDate getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(LocalDate dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public LocalDate getDataEnvio() {
		return dataEnvio;
	}

	public void setDataEnvio(LocalDate dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Double getTotalGeral() {
		return totalGeral;
	}

	public void setTotalGeral(Double totalGeral) {
		this.totalGeral = totalGeral;
	}

	public List<PedidoItem> getPedidosItem() {
		return pedidosItem;
	}

	public void setPedidosItem(List<PedidoItem> pedidosItem) {
		this.pedidosItem = pedidosItem;
	}

}
