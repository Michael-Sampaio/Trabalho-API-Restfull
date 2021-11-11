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

import br.org.serratec.backend.dto.PedidoItemDTO;
import br.org.serratec.backend.model.PedidoItem;
import br.org.serratec.backend.repository.PedidoItemRepository;
import br.org.serratec.backend.service.PedidoItemService;

@RestController
@RequestMapping("/pedidositem")
public class PedidoItemController {
	
	@Autowired
    PedidoItemRepository pedidoItemRepository;

    @Autowired
    PedidoItemService pedidoItemService;

    @GetMapping
    public ResponseEntity <List<PedidoItemDTO>> listar() {
        return ResponseEntity.ok(pedidoItemService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoItemDTO> buscar(@PathVariable Long id) {
        Optional<PedidoItem> PedidoItemDTO = pedidoItemRepository.findById(id);
        if (!PedidoItemDTO.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pedidoItemService.buscar(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoItem inserir(@Valid @RequestBody PedidoItem PedidoItem) {
        return pedidoItemRepository.save(PedidoItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        pedidoItemService.deletar(id);
        return ResponseEntity.ok().build();
    }
}
