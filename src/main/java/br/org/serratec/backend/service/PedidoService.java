package br.org.serratec.backend.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
	
	
	//METODO PARA INSERIR UM PEDIDO
	public PedidoDTO inserir(Pedido pedido) {
		pedido = pedidoRepository.save(pedido);
		return new PedidoDTO();
	}

	// METODO PARA ALTERAR UM PEDIDO
	public PedidoDTO alterar(AlterarPedidoDTO alterarPedidoDTO) {

		if (pedidoRepository.findById(alterarPedidoDTO.getId()) != null) {

			Pedido pedido = new Pedido();
			pedido.setId(alterarPedidoDTO.getId());

			return new PedidoDTO();
		} else {
			throw new PedidoException();
		}
	}
	//METODO PARA LISTAR PEDIDO POR NUMERO
	public PedidoDTO buscar(Long id) {
		Optional<Pedido> pedido = pedidoRepository.findById(id);
		//return pedidoRepository.findBytotalGeral(totalGeral);
		return new PedidoDTO(pedido.get());
	}
	
	public List<PedidoDTO> listar(){
		List<Pedido> pedidos = pedidoRepository.findAll();
		return pedidos.stream().map(pedidoItem -> new PedidoDTO(pedidoItem)).collect(Collectors.toList());
	}
	
	//METODO PARA DELETAR PEDIDO
	public void deletar(Long id) {
		if (pedidoRepository.existsById(id)) {
			pedidoRepository.deleteById(id);
		}
	}

}