package br.org.serratec.backend.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

import br.org.serratec.backend.model.Cliente;
import br.org.serratec.backend.model.Pedido;
import br.org.serratec.backend.model.Status;

@NotBlank
@Embeddable
public class PedidoDTO {

	private Long id;
	private LocalDate dataPedido;
	private LocalDate dataEntrega;
	private LocalDate dataEnvio;
	private Status status;
	private Cliente cliente;
	private List<PedidoItemDTO> pedidosItemDTO;
	private Double totalGeral;

	public PedidoDTO() {
	}

	public PedidoDTO(Pedido pedido) {
		super();
		this.id = pedido.getId();
		this.dataPedido = pedido.getDataPedido();
		this.dataEntrega = pedido.getDataEntrega();
		this.dataEnvio = pedido.getDataEnvio();
		this.status = pedido.getStatus();
		this.cliente = pedido.getCliente();
		this.pedidosItemDTO = pedido.getPedidosItem().stream().map(pedidoItem -> new PedidoItemDTO(pedidoItem))
				.collect(Collectors.toList());
		this.totalGeral = pedido.getTotalGeral();
	}

	public Long getId() {
		return id;
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

	public List<PedidoItemDTO> getPedidosItemDTO() {
		return pedidosItemDTO;
	}

	public void setPedidosItemDTO(List<PedidoItemDTO> pedidosItemDTO) {
		this.pedidosItemDTO = pedidosItemDTO;
	}

	public Double getTotalGeral() {
		return totalGeral;
	}

	public void setTotalGeral(Double totalGeral) {
		this.totalGeral = totalGeral;
	}

}
