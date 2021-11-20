package br.org.serratec.backend.dto;

import java.time.LocalDate;

import br.org.serratec.backend.model.Cliente;
import br.org.serratec.backend.model.Pedido;
import br.org.serratec.backend.model.Status;

public class AlterarPedidoDTO {

	private Long id;
	private LocalDate dataPedido;
	private LocalDate dataEntrega;
	private LocalDate dataEnvio;
	private Status status;
	private Cliente cliente;

	public AlterarPedidoDTO() {
	}

	public AlterarPedidoDTO(Pedido pedido) {
		super();
		this.dataPedido = pedido.getDataPedido();
		this.dataEntrega = pedido.getDataEntrega();
		this.dataEnvio = pedido.getDataEnvio();
		this.status = pedido.getStatus();
		this.cliente = pedido.getCliente();
	}

	public Long getId() {
		return this.id;
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

	public LocalDate getDataPedido() {
		return this.dataPedido;
	}

	public void setDataPedido(LocalDate dataPedido) {
		this.dataPedido = dataPedido;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}