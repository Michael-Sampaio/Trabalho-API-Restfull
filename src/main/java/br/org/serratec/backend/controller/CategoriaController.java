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

import br.org.serratec.backend.dto.CategoriaDTO;
import br.org.serratec.backend.dto.InserirCategoriaDTO;
import br.org.serratec.backend.exception.RecursoBadRequestException;
import br.org.serratec.backend.model.Categoria;
import br.org.serratec.backend.repository.CategoriaRepository;
import br.org.serratec.backend.service.CategoriaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    CategoriaService categoriaService;

    @GetMapping
    @ApiOperation(value = "Listar Categorias", notes = "Listagem de categorias")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna todas as categorias"),
            @ApiResponse(code = 401, message = "Erro de autenticação"),
            @ApiResponse(code = 403, message = "Recurso proibido"),
            @ApiResponse(code = 404, message = "Recurso não encontrado"),
            @ApiResponse(code = 500, message = "Erro de servidor") })

    public ResponseEntity<List<CategoriaDTO>> listar() {
        return ResponseEntity.ok(categoriaService.listar());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Buscar uma categoria por id", notes = "Busca uma categoria")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna uma categoria"),
            @ApiResponse(code = 401, message = "Erro de autenticação"),
            @ApiResponse(code = 403, message = "Recurso proibido"),
            @ApiResponse(code = 404, message = "Recurso não encontrado"),
            @ApiResponse(code = 500, message = "Erro de servidor") })
    public ResponseEntity<CategoriaDTO> buscar(@PathVariable Long id) {
        Optional<Categoria> CategoriaDTO = categoriaRepository.findById(id);
        if (!CategoriaDTO.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(categoriaService.buscar(id));
    }

    @PostMapping
    @ApiOperation(value = "Cadastrar uma categoria", notes = "Cadastro de categoria")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Cadastra uma categoria"),
            @ApiResponse(code = 401, message = "Erro de autenticação"),
            @ApiResponse(code = 403, message = "Recurso proibido"),
            @ApiResponse(code = 404, message = "Recurso não encontrado"),
            @ApiResponse(code = 500, message = "Erro de servidor") })
    @ResponseStatus(HttpStatus.CREATED)
    public CategoriaDTO inserir(@Valid @RequestBody InserirCategoriaDTO inserirCategoriaDTO) throws RecursoBadRequestException {
        return categoriaService.inserir(inserirCategoriaDTO);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletar uma categoria", notes = "Deleta categoria")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Exclui uma categoria"),
            @ApiResponse(code = 204, message = "Exclui uma categoria e retorna vazio"),
            @ApiResponse(code = 401, message = "Erro de autenticação"),
            @ApiResponse(code = 403, message = "Recurso proibido"),
            @ApiResponse(code = 404, message = "Recurso não encontrado"),
            @ApiResponse(code = 500, message = "Erro de servidor") })
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        categoriaService.deletar(id);
        return ResponseEntity.ok().build();
    }

}
