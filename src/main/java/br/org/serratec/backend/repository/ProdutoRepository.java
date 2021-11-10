package br.org.serratec.backend.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.serratec.backend.model.Categoria;
import br.org.serratec.backend.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    public Produto findBynome(String nome);

    public Produto findBydescricao(String descricao);

    public Produto findByqtdEstoque(Integer qtdEstoque);

    public Produto findBydataCadastro(LocalDate dataCadastro);

    public Produto findByvalorUnitario(Double valorUnitario);

    public Produto findBycategoria(Categoria categoria);

}
