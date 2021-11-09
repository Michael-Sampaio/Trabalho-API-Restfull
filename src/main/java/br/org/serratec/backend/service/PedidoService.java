package br.org.serratec.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.backend.dto.AlterarPedidoDTO;
import br.org.serratec.backend.dto.PedidoDTO;
import br.org.serratec.backend.exception.EmailException;
import br.org.serratec.backend.exception.PedidoException;
import br.org.serratec.backend.model.Endereco;
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
 public PedidoDTO alterar(AlterarPedidoDTO pedidoAlterarDTO) throws EmailException {
		
		if (pedidoRepository.findByEmail(pedidoAlterarDTO.getpedido()) = null) {
			throw new PedidoException("PEDIDO NÃO EXISTE, ESCOLHA PEDIDO EXISTENTE");
		}

		Pedido pedido = new Pedido();
		pedido.setId(pedidoAlterarDTO.getId());

		return new PedidoDTO(pedido);
	}

}
