package br.org.serratec.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.backend.dto.AlterarCategoriaDTO;
import br.org.serratec.backend.dto.CategoriaDTO;
import br.org.serratec.backend.dto.InserirCategoriaDTO;
import br.org.serratec.backend.exception.RecursoBadRequestException;
import br.org.serratec.backend.exception.RecursoNotFoundException;
import br.org.serratec.backend.model.Categoria;
import br.org.serratec.backend.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	CategoriaRepository categoriaRepository;

	/**
	 * METODO PARA INSERIR UMA CATEGORIA
	 * 
	 * @param categoria
	 * @return UMA NOVA CATEGORIA
	 */
	public CategoriaDTO inserir(InserirCategoriaDTO inserirCategoriaDTO) throws RecursoBadRequestException {

		if (categoriaRepository.findByNome(inserirCategoriaDTO.getNome()) != null) {
			throw new RecursoBadRequestException("Categoria ja cadastrada!");
		}

		Categoria categoria = new Categoria();
		categoria.setNome(inserirCategoriaDTO.getNome());
		categoria.setDescricao(inserirCategoriaDTO.getDescricao());
		categoriaRepository.save(categoria);

		return new CategoriaDTO(categoria);
	}

	/**
	 * METODO PARA ALTERAR UMA CATEGORIA
	 * 
	 * @param alterarCategoriaDTO
	 * @return UMA CATEGORIA ALTERADA
	 */
	public CategoriaDTO alterar(Long id, AlterarCategoriaDTO alterarCategoriaDTO) throws RecursoNotFoundException {

		if (categoriaRepository.existsById(id)) {
			Categoria categoria = new Categoria(alterarCategoriaDTO);
			categoria.setId(id);
			categoria.setNome(alterarCategoriaDTO.getNome());
			categoria.setDescricao(alterarCategoriaDTO.getDescricao());
			categoriaRepository.save(categoria);

			return new CategoriaDTO(categoria);
		}
		throw new RecursoNotFoundException("Categoria não encontrada");
	}

	/**
	 * METODO PARA LISTAR AS CATEGORIAS
	 * 
	 * @return LISTA DE CATEGORIAS
	 */
	public List<CategoriaDTO> listar() {
		List<Categoria> categorias = categoriaRepository.findAll();
		List<CategoriaDTO> categoriasDTO = new ArrayList<CategoriaDTO>();

		for (Categoria categoria : categorias) {
			CategoriaDTO categoriaDTO = new CategoriaDTO(categoria);
			categoriasDTO.add(categoriaDTO);
		}

		return categoriasDTO;
	}

	/**
	 * METODO PARA LISTAR UMA CATEGORIA PELO ID
	 * 
	 * @param id
	 * @return UMA CATEGORIA
	 */
	public CategoriaDTO buscar(Long id) {
		Optional<Categoria> categoria = categoriaRepository.findById(id);
		if (categoria.isPresent()) {
			return new CategoriaDTO(categoria.get());
		} else {
			throw new RecursoNotFoundException("Categoria não encontrada");
		}
	}

	/**
	 * METODO PARA DELETAR UMA CATEGORIA
	 * 
	 * @param id
	 */
	public void deletar(Long id) {
		if (categoriaRepository.existsById(id)) {
			categoriaRepository.deleteById(id);
		}
	}

}
