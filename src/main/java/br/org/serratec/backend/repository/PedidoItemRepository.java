package br.org.serratec.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import br.org.serratec.backend.model.PedidoItem;

public interface PedidoItemRepository extends JpaRepository<PedidoItem, Long>{
//	public PedidoItem findBysubTotal(Double subTotal);
//	public PedidoItem findBypedido(Pedido pedido);
}
