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

import br.org.serratec.backend.dto.AlterarProdutoDTO;
import br.org.serratec.backend.exception.RecursoBadRequestException;
import br.org.serratec.backend.dto.InserirProdutoDTO;
import br.org.serratec.backend.dto.ProdutoDTO;
import br.org.serratec.backend.exception.RecursoNotFoundException;
import br.org.serratec.backend.model.Produto;
import br.org.serratec.backend.service.ProdutoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/produtos")

public class ProdutoController {

    @Autowired
    private br.org.serratec.backend.repository.ProdutoRepository ProdutoRepository;

    @Autowired
    ProdutoService produtoService;

    @PostMapping
    @ApiOperation(value = "Cadastrar um produto", notes = "Cadastro de produtos")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Cadastra um produto"),
            @ApiResponse(code = 401, message = "Erro de autenticação"),
            @ApiResponse(code = 403, message = "Recurso proibido"),
            @ApiResponse(code = 404, message = "Recurso não encontrado"),
            @ApiResponse(code = 500, message = "Erro de servidor") })
    @ResponseStatus(HttpStatus.CREATED)

    public ResponseEntity<Object> inserir(@Valid @RequestBody InserirProdutoDTO inserirProdutoDTO) {
        try {
            ProdutoDTO produtoDTO = produtoService.inserir(inserirProdutoDTO);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(produtoDTO.getNome()).toUri();
            return ResponseEntity.created(uri).body(produtoDTO);
        } catch (RecursoBadRequestException recursoBadRequestException) {
            return ResponseEntity.badRequest().body(recursoBadRequestException.getMessage());
        }
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Alterar um produto", notes = "Alteração de um produto")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Altera um produto"),
            @ApiResponse(code = 401, message = "Erro de autenticação"),
            @ApiResponse(code = 403, message = "Recurso proibido"),
            @ApiResponse(code = 404, message = "Recurso não encontrado"),
            @ApiResponse(code = 500, message = "Erro de servidor") })
    @ResponseStatus(HttpStatus.OK)

    public ResponseEntity<Object> alterar(@PathVariable Long id,
            @Valid @RequestBody AlterarProdutoDTO alterarProdutoDTO) throws RecursoBadRequestException {

                if (produtoService.alterar(id, alterarProdutoDTO) != null) {
                    return ResponseEntity.ok(alterarProdutoDTO);
                }
                return ResponseEntity.notFound().build();
            }

    @GetMapping
    @ApiOperation(value = "Listar Produtos", notes = "Listagem de produtos")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna todos os Produtos"),
            @ApiResponse(code = 401, message = "Erro de autenticação"),
            @ApiResponse(code = 403, message = "Recurso proibido"),
            @ApiResponse(code = 404, message = "Recurso não encontrado"),
            @ApiResponse(code = 500, message = "Erro de servidor") })

    public ResponseEntity<List<ProdutoDTO>> listar() {
        return ResponseEntity.ok(produtoService.listar());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Buscar um produto por id", notes = "Busca um produto")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna um produto"),
            @ApiResponse(code = 401, message = "Erro de autenticação"),
            @ApiResponse(code = 403, message = "Recurso proibido"),
            @ApiResponse(code = 404, message = "Recurso não encontrado"),
            @ApiResponse(code = 500, message = "Erro de servidor") })

    public ResponseEntity<Produto> buscar(@PathVariable Long id) throws RecursoNotFoundException {
        Optional<Produto> Produto = ProdutoRepository.findById(id);
        if (!Produto.isPresent()) {
            throw new RecursoNotFoundException("Produto não encontrado");
        }
        return ResponseEntity.ok(Produto.get());
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletar um produto", notes = "Deleta produto")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Exclui um produto"),
            @ApiResponse(code = 204, message = "Exclui um produto e retorna vazio"),
            @ApiResponse(code = 401, message = "Erro de autenticação"),
            @ApiResponse(code = 403, message = "Recurso proibido"),
            @ApiResponse(code = 404, message = "Recurso não encontrado"),
            @ApiResponse(code = 500, message = "Erro de servidor") })

    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        produtoService.deletar(id);
        return ResponseEntity.ok().build();
    }

}
