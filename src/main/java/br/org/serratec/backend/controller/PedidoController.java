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

import br.org.serratec.backend.model.Pedido;
import br.org.serratec.backend.service.PedidoService;

@RestController
@RequestMapping("/pedidos")

public class PedidoController {

    @Autowired
    private br.org.serratec.backend.repository.PedidoRepository PedidoRepository;

    @Autowired
    PedidoService pedidoService;

    @GetMapping
    public List<Pedido> listar() {
        return PedidoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscar(@PathVariable Long id) {
        Optional<Pedido> Pedido = PedidoRepository.findById(id);
        if (!Pedido.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(Pedido.get());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pedido inserir(@Valid @RequestBody Pedido Pedido) {
        return PedidoRepository.save(Pedido);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        pedidoService.deletar(id);
        return ResponseEntity.ok().build();
    }

}
