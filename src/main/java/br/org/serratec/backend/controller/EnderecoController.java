package br.org.serratec.backend.controller;

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

import br.org.serratec.backend.dto.EnderecoDTO;
import br.org.serratec.backend.model.Endereco;
import br.org.serratec.backend.service.EnderecoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

	@Autowired
	EnderecoService enderecoService;

	@GetMapping("{cep}")
	@ApiOperation(value = "Buscar um endereço por cep", notes = "Busca um endereço")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna um endereço"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro de servidor") })

	public ResponseEntity<EnderecoDTO> buscar(@PathVariable Endereco endereco) {
		EnderecoDTO enderecoDTO = enderecoService.buscar(endereco);
	
		if (enderecoDTO == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(enderecoDTO);

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

	@PostMapping
	@ApiOperation(value = "Cadastrar um endereço", notes = "Cadastro de endereço")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Cadastra um endereço"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro de servidor") })

  @ResponseStatus(HttpStatus.CREATED)
	public EnderecoDTO inserir(@Valid @RequestBody Endereco endereco) {
		return enderecoService.buscar(endereco);
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
