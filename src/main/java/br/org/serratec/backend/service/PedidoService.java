package br.org.serratec.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.backend.dto.PedidoDTO;
import br.org.serratec.backend.exception.RecursoBadRequestException;
import br.org.serratec.backend.model.Pedido;
import br.org.serratec.backend.repository.PedidoRepository;

@Service
public class PedidoService {
	
	@Autowired
	PedidoRepository pedidoRepository;
	
//Metodo para inserir pedido
	public PedidoDTO inserir(Pedido pedido) {
		pedido = pedidoRepository.save(pedido);
		return new PedidoDTO(pedido);
	}	
	
//Metodo para editar pedido

	public PedidoDTO editar(Long id, PedidoDTO pedidoDTO) throws RecursoBadRequestException {

		Pedido pedido = new Pedido();
		pedido.setId(pedidoDTO.getId());
		
		pedidoRepository.save(pedido);
		return new PedidoDTO(pedido);
	}
	 
}
