package br.org.serratec.backend.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.serratec.backend.model.Cliente;
import br.org.serratec.backend.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

	public Optional<Pedido> findById(Long id);

	public Pedido findBydataPedido(LocalDate dataPedido);

	public Pedido findBydataEntrega(LocalDate dataEntrega);

	public Pedido findBydataEnvio(LocalDate dataEnvio);

	public Pedido findBycliente(Cliente cliente);
}
