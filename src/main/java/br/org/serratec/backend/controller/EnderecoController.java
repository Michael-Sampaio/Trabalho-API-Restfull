package br.org.serratec.backend.controller;

import java.net.URI;
import java.util.List;

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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.org.serratec.backend.dto.EnderecoDTO;
import br.org.serratec.backend.dto.InserirEnderecoDTO;
import br.org.serratec.backend.exception.RecursoBadRequestException;
import br.org.serratec.backend.service.EnderecoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

	@Autowired
	EnderecoService enderecoService;

	@PostMapping
	@ApiOperation(value = "Cadastrar um endereço", notes = "Cadastro de endereço")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Cadastra um endereço"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro de servidor") })
	@ResponseStatus(HttpStatus.CREATED)
	
	public ResponseEntity<Object> inserir(@Valid @RequestBody InserirEnderecoDTO inserirEnderecoDTO) {
		try {
			EnderecoDTO enderencoDTO = enderecoService.inserirEndereco(inserirEnderecoDTO);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(enderencoDTO.getCep()).toUri();
			return ResponseEntity.created(uri).body(enderencoDTO);
		} catch (RecursoBadRequestException recursoBadRequestException) {
			return ResponseEntity.badRequest().body(recursoBadRequestException.getMessage());
		}
	}

	@GetMapping
	@ApiOperation(value = "Listar Endereços", notes = "Listagem de endereços")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna todos os endereços"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro de servidor") })

	public ResponseEntity<List<EnderecoDTO>> listar() {
		return ResponseEntity.ok(enderecoService.listar());
	}

	@GetMapping("{cep}")
	@ApiOperation(value = "Buscar um endereço por cep", notes = "Busca um endereço")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna um endereço"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro de servidor") })

	public ResponseEntity<EnderecoDTO> buscar(@PathVariable String cep) {
		EnderecoDTO enderecoDTO = enderecoService.buscar(cep);

		if (enderecoDTO == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(enderecoDTO);

	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Deletar um endereço", notes = "Deleta endereço")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Exclui um endereço"),
			@ApiResponse(code = 204, message = "Exclui um endereço e retorna vazio"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro de servidor") })

	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		enderecoService.deletar(id);
		return ResponseEntity.ok().build();
	}

}
