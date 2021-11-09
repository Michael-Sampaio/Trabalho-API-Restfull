package br.org.serratec.backend.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import br.org.serratec.backend.model.FotoProduto;
import br.org.serratec.backend.model.Produto;
import br.org.serratec.backend.repository.FotoProdutoRepository;

public class FotoProdutoService {
	
	@Autowired
	FotoProdutoRepository fotoProdutoRepository;
	
	public FotoProduto inserir(Produto produto, MultipartFile file) throws IOException {
		FotoProduto fotoProduto = new FotoProduto();
		fotoProduto.setNome(file.getName());
		fotoProduto.setDados(file.getBytes());
		fotoProduto.setTipo(file.getContentType());
		fotoProduto.setProduto(produto);
		return fotoProdutoRepository.save(fotoProduto);
	}
	
	public FotoProduto buscar(Long id) {
		Optional<FotoProduto> fotoProduto = fotoProdutoRepository.findById(id);
		
		if(fotoProduto.isPresent()) {
			return fotoProduto.get();
		}
		return null;
	}
}
