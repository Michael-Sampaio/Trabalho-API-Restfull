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

import br.org.serratec.backend.model.Produto;
import br.org.serratec.backend.service.ProdutoService;

@RestController
@RequestMapping("/produtos")

public class ProdutoController {

    @Autowired
    private br.org.serratec.backend.repository.ProdutoRepository ProdutoRepository;

    @Autowired
    ProdutoService produtoService;

    @GetMapping
    public List<Produto> listar() {
        return ProdutoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscar(@PathVariable Long id) {
        Optional<Produto> Produto = ProdutoRepository.findById(id);
        if (!Produto.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(Produto.get());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto inserir(@Valid @RequestBody Produto Produto) {
        return ProdutoRepository.save(Produto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        produtoService.deletar(id);
        return ResponseEntity.ok().build();
    }

}
