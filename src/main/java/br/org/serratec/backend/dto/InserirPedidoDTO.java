package br.org.serratec.backend.dto;

import java.time.LocalDate;

import javax.persistence.Embeddable;

import br.org.serratec.backend.model.Cliente;
import br.org.serratec.backend.model.Pedido;
import br.org.serratec.backend.model.Status;

@Embeddable
public class InserirPedidoDTO {

	private Long id;
	private LocalDate dataPedido;
	private LocalDate dataEntrega;
	private LocalDate dataEnvio;
	private Status status;
	private Cliente cliente;

	public InserirPedidoDTO() {
	}

	public InserirPedidoDTO(Pedido pedido) {
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

	public void setId(Long id) {
		this.id = id;
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

}
