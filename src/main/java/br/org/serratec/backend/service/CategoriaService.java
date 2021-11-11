package br.org.serratec.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.backend.dto.AlterarCategoriaDTO;
import br.org.serratec.backend.dto.CategoriaDTO;
import br.org.serratec.backend.dto.InserirCategoriaDTO;
import br.org.serratec.backend.exception.CategoriaException;
import br.org.serratec.backend.model.Categoria;
import br.org.serratec.backend.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	CategoriaRepository categoriaRepository;

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
	 * METODO PARA BUSCAR UMA CATEGORIA PELO ID
	 * 
	 * @param id
	 * @return UMA CATEGORIA
	 */
	public CategoriaDTO buscar(Long id) {
		Optional<Categoria> categoria = categoriaRepository.findById(id);
		return new CategoriaDTO(categoria.get());
	}

	/**
	 * METODO PARA INSERIR UMA CATEGORIA
	 * 
	 * @param categoria
	 * @return UMA NOVA CATEGORIA
	 * @throws CategoriaException
	 */
	public CategoriaDTO inserir(InserirCategoriaDTO inserirCtDTO) throws CategoriaException {
		
		if (categoriaRepository.findByNome(inserirCtDTO.getNome()) != null) {
			throw new CategoriaException();
		}
		
		Categoria categoria = new Categoria();
		categoria.setNome(inserirCtDTO.getNome());
		categoria.setDescricao(inserirCtDTO.getDescricao());
		categoriaRepository.save(categoria);
		return new CategoriaDTO(categoria);
	}

	/**
	 * METODO PARA EDITAR UMA CATEGORIA
	 * 
	 * @param alterarCategoriaDTO
	 * @return UMA CATEGORIA COM REGISTRO ALTERADO
	 * @throws CategoriaException
	 */
	public CategoriaDTO alterar(AlterarCategoriaDTO alterarCategoriaDTO) throws CategoriaException {

		if (categoriaRepository.findByNome(alterarCategoriaDTO.getNome()) != null) {
			throw new CategoriaException();
		}
		Categoria categoria = new Categoria();
		categoria.setId(alterarCategoriaDTO.getId());
        categoriaRepository.save(categoria);
		return new CategoriaDTO(categoria);
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
