package br.org.serratec.backend.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.serratec.backend.model.Categoria;
import br.org.serratec.backend.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    public Optional<Produto> findById(Long id);

    public Produto findByNome(String nome);

    public Produto findBydescricao(String descricao);

    public Produto findByqtdEstoque(Integer qtdEstoque);

    public Produto findBydataCadastro(LocalDate dataCadastro);

    public Produto findByvalorUnitario(Double valorUnitario);

    public Produto findBycategoria(Categoria categoria);

}
