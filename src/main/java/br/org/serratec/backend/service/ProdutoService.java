package br.org.serratec.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.backend.dto.ProdutoDTO;
import br.org.serratec.backend.model.Pedido;
import br.org.serratec.backend.model.Produto;
import br.org.serratec.backend.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	ProdutoRepository produtoRepository;
	
//Metodo para inserir um produto
	
	public ProdutoDTO inserir(Produto produto) {
		produto = produtoRepository.save(produto);
		return new ProdutoDTO(produto);
	}
//Metodo para editar um produto
	
	public ProdutoDTO editar(Produto produto) {
		
	}
}
