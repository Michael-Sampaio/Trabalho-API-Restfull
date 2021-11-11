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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.org.serratec.backend.dto.InserirPedidoDTO;
import br.org.serratec.backend.dto.PedidoDTO;
import br.org.serratec.backend.exception.PedidoException;
import br.org.serratec.backend.model.Pedido;
import br.org.serratec.backend.repository.PedidoRepository;
import br.org.serratec.backend.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    PedidoService pedidoService;

    @GetMapping
    public ResponseEntity <List<PedidoDTO>> listar() {
        return ResponseEntity.ok(pedidoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> buscar(@PathVariable Long id) {
        Optional<Pedido> PedidoDTO = pedidoRepository.findById(id);
        if (!PedidoDTO.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pedidoService.buscar(id));
    }
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Object> inserir(@Valid @RequestBody InserirPedidoDTO inserirPedidoDTO) {
		try {
			 PedidoDTO pedidoDTO = pedidoService.inserir(inserirPedidoDTO);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(pedidoDTO.getId())
					.toUri();
			return ResponseEntity.created(uri).body(pedidoDTO);
		} catch (PedidoException pedidoEx) {
			return ResponseEntity.unprocessableEntity().body(pedidoEx.getMessage());
		}
	}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        pedidoService.deletar(id);
        return ResponseEntity.ok().build();
    }

}

