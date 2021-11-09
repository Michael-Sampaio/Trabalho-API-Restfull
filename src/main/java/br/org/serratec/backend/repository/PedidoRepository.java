package br.org.serratec.backend.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.serratec.backend.model.Cliente;
import br.org.serratec.backend.model.Pedido;

public interface PedidoRepository extends JpaRepository <Pedido, Long> {
    public Pedido findByIdPedido(Long id);
	public Pedido findBydataPedido(LocalDate dataPedido);
	public Pedido findBydataEntrega(LocalDate dataEntrega);
	public Pedido findBydataEnvio(LocalDate dataEnvio);
	public Pedido findBycliente(Cliente cliente);
}
