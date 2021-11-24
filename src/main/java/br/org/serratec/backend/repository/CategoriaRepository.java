package br.org.serratec.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.serratec.backend.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    public Optional<Categoria> findById(Long id);

    public Categoria findByDescricao(String descricao);

    public Categoria findByNome(String nome);
    
}
