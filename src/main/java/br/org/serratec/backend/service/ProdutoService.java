package br.org.serratec.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.backend.dto.AlterarProdutoDTO;
import br.org.serratec.backend.dto.ProdutoDTO;
import br.org.serratec.backend.exception.ProdutoException;
import br.org.serratec.backend.model.Produto;
import br.org.serratec.backend.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository produtoRepository;

	// Metodo para inserir um produto

	public ProdutoDTO inserir(Produto produto) {
		produto = produtoRepository.save(produto);
		return new ProdutoDTO(produto);
	}
	
	//METODO PARA LISTAR OS PRODUTOS
		public List<ProdutoDTO> listar() {
			List<Produto> produtos = produtoRepository.findAll();
			List<ProdutoDTO> produtosDTO = new ArrayList<ProdutoDTO>();
	
			for (Produto produto : produtos) {
				ProdutoDTO produtoDTO = new ProdutoDTO(produto);
				produtosDTO.add(produtoDTO);
			}
			return produtosDTO;
		}
	// Metodo para editar um produto

	public ProdutoDTO alterar(AlterarProdutoDTO alterarProdutoDTO) {

		if (produtoRepository.findBynome(alterarProdutoDTO.getNome()) != null) {

			Produto produto = new Produto();
			produto.setNome(alterarProdutoDTO.getNome());

			return new ProdutoDTO(produto);
		} else {
			throw new ProdutoException();
		}
	}

}
