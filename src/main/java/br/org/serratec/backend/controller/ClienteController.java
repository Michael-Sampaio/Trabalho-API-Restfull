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

import br.org.serratec.backend.dto.InserirClienteDTO;
import br.org.serratec.backend.dto.ClienteDTO;
import br.org.serratec.backend.exception.EmailException;
import br.org.serratec.backend.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	ClienteService clienteService;

	@GetMapping
	public ResponseEntity<List<ClienteDTO>> listar() {
		return ResponseEntity.ok(clienteService.listar());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Object> inserir(@Valid @RequestBody InserirClienteDTO inserirClienteDTO) {
		try {
			 ClienteDTO clienteDTO = clienteService.inserir(inserirClienteDTO);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(clienteDTO.getId())
					.toUri();
			return ResponseEntity.created(uri).body(clienteDTO);
		} catch (EmailException emailEx) {
			return ResponseEntity.unprocessableEntity().body(emailEx.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		clienteService.deletar(id);
		return ResponseEntity.ok().build();
	}

}
