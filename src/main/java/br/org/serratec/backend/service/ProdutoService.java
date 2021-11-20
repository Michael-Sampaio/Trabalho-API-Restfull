package br.org.serratec.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.backend.dto.AlterarProdutoDTO;
import br.org.serratec.backend.dto.InserirProdutoDTO;
import br.org.serratec.backend.dto.ProdutoDTO;
import br.org.serratec.backend.exception.RecursoBadRequestException;
import br.org.serratec.backend.exception.RecursoNotFoundException;
import br.org.serratec.backend.model.Produto;
import br.org.serratec.backend.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository produtoRepository;

	/**
	 * METODO PARA INSERIR UM PRODUTO
	 * 
	 * @param produto
	 * @return UM NOVO PRODUTO
	 */
	public ProdutoDTO inserir(InserirProdutoDTO inserirProdutoDTO) throws RecursoBadRequestException {

		if (produtoRepository.findByNome(inserirProdutoDTO.getNome()) != null) {
			throw new RecursoBadRequestException("Produto ja cadastrado!");
		}

		Produto produto = new Produto();
		produto.setCategoria(inserirProdutoDTO.getCategoria());
		produto.setDataCadastro(inserirProdutoDTO.getDataCadastro());
		produto.setDescricao(inserirProdutoDTO.getDescricao());
		produto.setNome(inserirProdutoDTO.getNome());
		produto.setQtdEstoque(inserirProdutoDTO.getQtdEstoque());
		produto.setValorUnitario(inserirProdutoDTO.getValorUnitario());
		produtoRepository.save(produto);

		return new ProdutoDTO(produto);
	}

	/**
	 * METODO PARA ALTERAR UM PRODUTO
	 * 
	 * @param alterarProdutoDTO
	 * @return UM PRODUTO ALTERADO
	 */
	public ProdutoDTO alterar(Long id, AlterarProdutoDTO alterarProdutoDTO) throws RecursoNotFoundException {

		if (produtoRepository.existsById(id)) {
			Produto produto = new Produto(alterarProdutoDTO);
			produto.setId(id);
			produto.setNome(alterarProdutoDTO.getNome());
			produto.setDataCadastro(alterarProdutoDTO.getDataCadastro());
			produto.setDescricao(alterarProdutoDTO.getDescricao());
			produto.setQtdEstoque(alterarProdutoDTO.getQtdEstoque());
			produto.setValorUnitario(alterarProdutoDTO.getValorUnitario());
			produto.setCategoria(alterarProdutoDTO.getCategoria());
			produtoRepository.save(produto);

			return new ProdutoDTO(produto);
		}
		throw new RecursoNotFoundException("Produto não encontrada");
	}

	/**
	 * METODO PARA LISTAR OS PRODUTOS
	 * 
	 * @return UMA LISTA DE PRODUTOS
	 */
	public List<ProdutoDTO> listar() {
		List<Produto> produtos = produtoRepository.findAll();
		List<ProdutoDTO> produtosDTO = new ArrayList<ProdutoDTO>();

		for (Produto produto : produtos) {
			ProdutoDTO produtoDTO = new ProdutoDTO(produto);
			produtosDTO.add(produtoDTO);
		}
		return produtosDTO;
	}

	/**
	 * METODO PARA LISTAR UM PRODUTO PELO ID
	 * 
	 * @param id
	 * @return UM PRODUTO
	 */
	public ProdutoDTO buscar(Long id) {
		Optional<Produto> produto = produtoRepository.findById(id);
		if (produto.isPresent()) {
			return new ProdutoDTO(produto.get());
		} else {
			throw new RecursoNotFoundException("Produto não encontrado");
		}
	}

	/**
	 * METODO PARA DELETAR UM PRODUTO
	 * 
	 * @param id
	 */
	public void deletar(Long id) {
		if (produtoRepository.existsById(id)) {
			produtoRepository.deleteById(id);
		}
	}

}