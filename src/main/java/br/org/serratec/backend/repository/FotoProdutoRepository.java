package br.org.serratec.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.serratec.backend.model.FotoProduto;

public interface FotoProdutoRepository extends JpaRepository<FotoProduto, Long> {
    
}
