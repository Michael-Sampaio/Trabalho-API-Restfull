package br.org.serratec.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.serratec.backend.model.Pedido;
import br.org.serratec.backend.model.PedidoItem;

public interface PedidoItemRepository extends JpaRepository<PedidoItem, Long> {

    public Optional<PedidoItem> findById(Long id);

    public PedidoItem findBypedido(Pedido pedido);
}
