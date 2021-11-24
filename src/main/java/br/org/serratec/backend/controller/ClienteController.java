package br.org.serratec.backend.controller;

import java.net.URI;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.org.serratec.backend.dto.AlterarClienteDTO;
import br.org.serratec.backend.dto.ClienteDTO;
import br.org.serratec.backend.dto.InserirClienteDTO;
import br.org.serratec.backend.exception.RecursoBadRequestException;
import br.org.serratec.backend.model.Cliente;
import br.org.serratec.backend.repository.ClienteRepository;
import br.org.serratec.backend.service.ClienteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	ClienteService clienteService;

	@Autowired
	ClienteRepository clienteRepository;

	@PostMapping
	@ApiOperation(value = "Cadastrar um cliente", notes = "Cadastro de cliente")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Cadastra um cliente"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro de servidor") })
	@ResponseStatus(HttpStatus.CREATED)

	public ResponseEntity<Object> inserir(@Valid @RequestBody InserirClienteDTO inserirClienteDTO) {
		try {
			ClienteDTO clienteDTO = clienteService.inserir(inserirClienteDTO);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(clienteDTO.getNomeUsuario()).toUri();
			return ResponseEntity.created(uri).body(clienteDTO);
		} catch (RecursoBadRequestException recursoBadRequestException) {
			return ResponseEntity.badRequest().body(recursoBadRequestException.getMessage());
		}
	}

	// ALTERAR CLIENTE
	@PutMapping("/{id}")
	@ApiOperation(value = "Alterar um cliente", notes = "Alteração de um cliente")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Altera um cliente"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro de servidor") })
	@ResponseStatus(HttpStatus.OK)

	public ResponseEntity<Object> alterar(@PathVariable Long id,
			@Valid @RequestBody AlterarClienteDTO alterarClienteDTO) throws RecursoBadRequestException {

		if (clienteService.alterar(id, alterarClienteDTO) != null) {
			return ResponseEntity.ok(alterarClienteDTO);
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping
	@ApiOperation(value = "Listar todos clientes", notes = "Listagem de clientes")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna todos os clientes"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro de servidor") })
	public ResponseEntity<List<ClienteDTO>> listar() {
		return ResponseEntity.ok(clienteService.listar());
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Buscar um cliente por id", notes = "Busca um cliente")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna um cliente"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro de servidor") })
	public ResponseEntity<ClienteDTO> buscar(@PathVariable Long id) {
		Optional<Cliente> ClienteDTO = clienteRepository.findById(id);
		if (!ClienteDTO.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(clienteService.buscar(id));
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Deletar um cliente", notes = "Deleta cliente")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Exclui um cliente"),
			@ApiResponse(code = 204, message = "Exclui um cliente e retorna vazio"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro de servidor") })
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		clienteService.deletar(id);
		return ResponseEntity.ok().build();
	}

}