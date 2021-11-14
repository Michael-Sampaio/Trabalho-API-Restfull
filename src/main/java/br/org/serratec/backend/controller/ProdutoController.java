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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.backend.dto.AlterarProdutoDTO;
import br.org.serratec.backend.dto.ProdutoDTO;
import br.org.serratec.backend.exception.RecursoBadRequestException;
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

    @GetMapping
    @ApiOperation(value = "Listar produtos", notes = "Listagem de produtos")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna todos os produtos"),
            @ApiResponse(code = 401, message = "Erro de autenticação"),
            @ApiResponse(code = 403, message = "Recurso proibido"),
            @ApiResponse(code = 404, message = "Recurso não encontrado"),
            @ApiResponse(code = 500, message = "Erro de servidor") })

    public List<Produto> listar() {
        return ProdutoRepository.findAll();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Buscar um produto por id", notes = "Busca um produto")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna um produto"),
            @ApiResponse(code = 401, message = "Erro de autenticação"),
            @ApiResponse(code = 403, message = "Recurso proibido"),
            @ApiResponse(code = 404, message = "Recurso não encontrado"),
            @ApiResponse(code = 500, message = "Erro de servidor") })

    public ResponseEntity<Produto> buscar(@PathVariable Long id) {
        Optional<Produto> Produto = ProdutoRepository.findById(id);
        if (!Produto.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(Produto.get());
    }

    @PostMapping
    @ApiOperation(value = "Cadastrar um produto", notes = "Cadastro de produto")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Cadastra um produto"),
            @ApiResponse(code = 401, message = "Erro de autenticação"),
            @ApiResponse(code = 403, message = "Recurso proibido"),
            @ApiResponse(code = 404, message = "Recurso não encontrado"),
            @ApiResponse(code = 500, message = "Erro de servidor") })

    @ResponseStatus(HttpStatus.CREATED)
    public Produto inserir(@Valid @RequestBody Produto Produto) {
        return ProdutoRepository.save(Produto);
    }
    
    @PutMapping("/{id}")
    @ApiOperation(value = "Alterar um cliente", notes = "Alteração de um cliente")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Altera um cliente"),
            @ApiResponse(code = 401, message = "Erro de autenticação"),
            @ApiResponse(code = 403, message = "Recurso proibido"),
            @ApiResponse(code = 404, message = "Recurso não encontrado"),
            @ApiResponse(code = 500, message = "Erro de servidor") })
    public ProdutoDTO alterar(@PathVariable Long id, @Valid @RequestBody AlterarProdutoDTO alterarProdutoDTO) throws RecursoBadRequestException {
    	return produtoService.alterar(alterarProdutoDTO);
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
