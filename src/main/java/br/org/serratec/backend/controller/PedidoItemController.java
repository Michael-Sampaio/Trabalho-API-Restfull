package br.org.serratec.backend.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.backend.exception.RecursoBadRequestException;
import br.org.serratec.backend.dto.PedidoItemDTO;
import br.org.serratec.backend.dto.InserirPedidoItemDTO;
import br.org.serratec.backend.model.PedidoItem;
import br.org.serratec.backend.repository.PedidoItemRepository;
import br.org.serratec.backend.service.PedidoItemService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/pedidositem")
public class PedidoItemController {

	@Autowired
	PedidoItemRepository pedidoItemRepository;

	@Autowired
	PedidoItemService pedidoItemService;

	@GetMapping
	@ApiOperation(value = "Listar Itens de pedido", notes = "Listagem de Itens de pedido")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna todos os Itens de pedido"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro de servidor") })

	public ResponseEntity<List<PedidoItemDTO>> listar() {
		return ResponseEntity.ok(pedidoItemService.listar());
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Buscar um Item de pedido por id", notes = "Busca um Item de pedido")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna um Item de pedido"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro de servidor") })

	public ResponseEntity<PedidoItemDTO> buscar(@PathVariable Long id) {
		Optional<PedidoItem> PedidoItemDTO = pedidoItemRepository.findById(id);
		if (!PedidoItemDTO.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(pedidoItemService.buscar(id));
	}

	@PostMapping
	@ApiOperation(value = "Cadastrar um Item de pedido", notes = "Cadastro de Item de pedido")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Cadastra um item de pedido"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro de servidor") })

	@ResponseStatus(HttpStatus.CREATED)
	public PedidoItemDTO inserir(@Valid @RequestBody InserirPedidoItemDTO inserirPedidoItemDTO)
			throws RecursoBadRequestException {
		return pedidoItemService.inserir(inserirPedidoItemDTO);
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Deletar um item de pedido", notes = "Deleta item de pedido")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Exclui um item de pedido"),
			@ApiResponse(code = 204, message = "Exclui um item de pedido e retorna vazio"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro de servidor") })

	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		pedidoItemService.deletar(id);
		return ResponseEntity.ok().build();
	}

}
