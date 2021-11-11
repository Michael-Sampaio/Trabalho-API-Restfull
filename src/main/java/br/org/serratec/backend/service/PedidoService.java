package br.org.serratec.backend.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.backend.dto.AlterarPedidoDTO;
import br.org.serratec.backend.dto.InserirPedidoDTO;
import br.org.serratec.backend.dto.PedidoDTO;
import br.org.serratec.backend.exception.PedidoException;
import br.org.serratec.backend.model.Pedido;
import br.org.serratec.backend.repository.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	PedidoRepository pedidoRepository;

	/**
	 * METODO PARA LISTAR PEDIDOS
	 * 
	 * @return LISTA DE PEDIDOS
	 */
	public List<PedidoDTO> listar() {
		List<Pedido> pedidos = pedidoRepository.findAll();
		return pedidos.stream().map(pedidoItem -> new PedidoDTO(pedidoItem)).collect(Collectors.toList());
	}

	/**
	 * METODO PARA LISTAR PEDIDO POR NUMERO COM TOTAL GERAL
	 * 
	 * @param id
	 * @return UM PEDIDO COM O TOTAL GERAL
	 */
	public PedidoDTO buscar(Long id) {
		Optional<Pedido> pedido = pedidoRepository.findById(id);
		// return pedidoRepository.findBytotalGeral(totalGeral);
		return new PedidoDTO(pedido.get());
	}

	/**
	 * METODO PARA INSERIR UM PEDIDO
	 * 
	 * @param pedido
	 * @return UM NOVO PEDIDO
	 * @throws PedidoExceptiom
	 */
	public PedidoDTO inserir(InserirPedidoDTO inserirPDTO) throws PedidoException {

		Pedido pedido = new Pedido();
		pedido.setId(inserirPDTO.getId());
		pedidoRepository.save(pedido);
		return new PedidoDTO(pedido);
	}

	/**
	 * METODO PARA ALTERAR UM PEDIDO
	 * 
	 * @param alterarPedidoDTO
	 * @return UM NOVO REGISTRO DE PEDIDO
	 */
	public PedidoDTO alterar(AlterarPedidoDTO alterarPedidoDTO) {

		if (pedidoRepository.findById(alterarPedidoDTO.getId()) != null) {

			Pedido pedido = new Pedido();
			pedido.setId(alterarPedidoDTO.getId());

			return new PedidoDTO(pedido);

		} else {
			throw new PedidoException();
		}
	}

	/**
	 * METODO PARA DELETAR PEDIDO
	 * 
	 * @param id
	 */
	public void deletar(Long id) {
		if (pedidoRepository.existsById(id)) {
			pedidoRepository.deleteById(id);
		}
	}

}