package br.org.serratec.backend.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.FutureOrPresent;

import br.org.serratec.backend.model.Pedido;
import br.org.serratec.backend.model.Status;

public class AlterarPedidoDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5842252635524606909L;

	private Long id;

	@FutureOrPresent
	private LocalDate dataEntrega;

	@FutureOrPresent
	private LocalDate dataEnvio;
	
	@Enumerated(EnumType.STRING)
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