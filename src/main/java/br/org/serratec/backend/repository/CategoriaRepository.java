package br.org.serratec.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.serratec.backend.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    boolean existsById = false;

    Optional<Categoria> findById(Long id);

    Categoria findByDescricao(String descricao);

    Categoria findByNome(String nome);
}
