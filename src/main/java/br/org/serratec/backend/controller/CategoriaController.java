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

import br.org.serratec.backend.dto.CategoriaDTO;
import br.org.serratec.backend.dto.InserirCategoriaDTO;
import br.org.serratec.backend.exception.CategoriaException;
import br.org.serratec.backend.model.Categoria;
import br.org.serratec.backend.repository.CategoriaRepository;
import br.org.serratec.backend.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	CategoriaService categoriaService;

    @Autowired
    CategoriaRepository categoriaRepository;

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> buscar(@PathVariable Long id) {
        Optional<Categoria> CategoriaDTO = categoriaRepository.findById(id);
        if (!CategoriaDTO.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(categoriaService.buscar(id));
    }

	@GetMapping
	public ResponseEntity<List<CategoriaDTO>> listar() {
		return ResponseEntity.ok(categoriaService.listar());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Object> inserir(@Valid @RequestBody InserirCategoriaDTO inserirCtDTO) {
		try {
			 CategoriaDTO categoriaDTO = categoriaService.inserir(inserirCtDTO);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(categoriaDTO.getId())
					.toUri();
			return ResponseEntity.created(uri).body(categoriaDTO);
		} catch (CategoriaException categoriaEx) {
			return ResponseEntity.unprocessableEntity().body(categoriaEx.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		categoriaService.deletar(id);
		return ResponseEntity.ok().build();
	}

}
