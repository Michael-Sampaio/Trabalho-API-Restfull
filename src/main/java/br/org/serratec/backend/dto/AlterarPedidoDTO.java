package br.org.serratec.backend.dto;

import java.time.LocalDate;

import br.org.serratec.backend.model.Pedido;
import br.org.serratec.backend.model.Status;

public class AlterarPedidoDTO {

	private Long id;
	private LocalDate dataEntrega;
	private LocalDate dataEnvio;
	private Status status;

	public AlterarPedidoDTO() {
	}

	public AlterarPedidoDTO(Pedido pedido) {
		super();
		this.dataEntrega = pedido.getDataEntrega();
		this.dataEnvio = pedido.getDataEnvio();
		this.status = pedido.getStatus();
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

}