package br.org.serratec.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.backend.dto.AlterarPedidoDTO;
import br.org.serratec.backend.dto.InserirPedidoDTO;
import br.org.serratec.backend.dto.PedidoDTO;
import br.org.serratec.backend.exception.RecursoBadRequestException;
import br.org.serratec.backend.exception.RecursoNotFoundException;
import br.org.serratec.backend.model.Pedido;
import br.org.serratec.backend.repository.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	PedidoRepository pedidoRepository;

	/**
	 * METODO PARA INSERIR UM PEDIDO
	 * 
	 * @param pedido
	 * @return UM NOVO PEDIDO
	 */
	public PedidoDTO inserir(InserirPedidoDTO inserirPedidoDTO) {

		Pedido pedido = new Pedido();
		pedido.setId(inserirPedidoDTO.getId());
		pedido.setCliente(inserirPedidoDTO.getCliente());
		pedido.setDataPedido(inserirPedidoDTO.getDataPedido());
		pedido.setDataEnvio(inserirPedidoDTO.getDataEnvio());
		pedido.setDataEntrega(inserirPedidoDTO.getDataEntrega());
		pedido.setStatus(inserirPedidoDTO.getStatus());
		pedidoRepository.save(pedido);

		return new PedidoDTO(pedido);
	}

	/**
	 * METODO PARA ALTERAR UM PEDIDO
	 * 
	 * @param alterarPedidoDTO
	 * @return UM PEDIDO ALTERADO
	 */
	public PedidoDTO alterar(Long id, AlterarPedidoDTO alterarPedidoDTO) throws RecursoNotFoundException {

		if (pedidoRepository.existsById(id)) {
			Pedido pedido = new Pedido(alterarPedidoDTO);
			pedido.setId(id);
			pedido.setDataPedido(alterarPedidoDTO.getDataPedido());
			pedido.setDataEnvio(alterarPedidoDTO.getDataEnvio());
			pedido.setDataEntrega(alterarPedidoDTO.getDataEntrega());
			pedido.setStatus(alterarPedidoDTO.getStatus());
			pedido.setCliente(alterarPedidoDTO.getCliente());
			pedidoRepository.save(pedido);

			return new PedidoDTO(pedido);
		}
		throw new RecursoNotFoundException("Pedido não encontrado");
	}

	/**
	 * METODO PARA LISTAR PEDIDOS
	 * 
	 * @return LISTA DE PEDIDOS
	 */
	public List<PedidoDTO> listar() {
		List<Pedido> pedidos = pedidoRepository.findAll();
		List<PedidoDTO> pedidosDTO = new ArrayList<PedidoDTO>();

		for (Pedido pedido : pedidos) {
			PedidoDTO pedidoDTO = new PedidoDTO(pedido);
			pedidosDTO.add(pedidoDTO);
		}
		return pedidosDTO;
	}

	/**
	 * METODO PARA LISTAR PEDIDO POR NUMERO COM TOTAL GERAL
	 * 
	 * @param id
	 * @return UM PEDIDO COM O TOTAL GERAL
	 */
	public PedidoDTO buscar(Long id) {
		Optional<Pedido> pedido = pedidoRepository.findById(id);
		if (pedido.isPresent()) {
			return new PedidoDTO(pedido.get());
		} else {
			throw new RecursoBadRequestException("Pedido não encontrado");
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
