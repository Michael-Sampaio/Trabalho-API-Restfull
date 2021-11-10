package br.org.serratec.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.backend.dto.AlterarPedidoDTO;
import br.org.serratec.backend.dto.PedidoDTO;
import br.org.serratec.backend.exception.PedidoException;
import br.org.serratec.backend.model.Pedido;
import br.org.serratec.backend.repository.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	PedidoRepository pedidoRepository;

	// Metodo para inserir pedido
	public PedidoDTO inserir(Pedido pedido) {
		pedido = pedidoRepository.save(pedido);
		return new PedidoDTO(pedido);
	}
	
	//METODO PARA ALTERAR UM PEDIDO
	public PedidoDTO alterar(AlterarPedidoDTO alterarPedidoDTO) {
			
			if (pedidoRepository.findById(alterarPedidoDTO.getId()) != null) {
	
			Pedido pedido = new Pedido();
			pedido.setId(alterarPedidoDTO.getId());
	
			return new PedidoDTO(pedido);
			}else {
				throw new PedidoException();
			}
		}

}
