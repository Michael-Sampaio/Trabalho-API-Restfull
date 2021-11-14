package br.org.serratec.backend.dto;

import javax.persistence.Embeddable;

import br.org.serratec.backend.model.Categoria;
import br.org.serratec.backend.model.Produto;

@Embeddable
public class AlterarProdutoDTO {

    private Long id;
    private String nome;
    private String descricao;
    private Integer qtdEstoque;
    private Double valorUnitario;
    private Categoria categoria;

    public AlterarProdutoDTO() {
    }

    public AlterarProdutoDTO(Produto produto) {
        super();
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.qtdEstoque = produto.getQtdEstoque();
        this.valorUnitario = produto.getValorUnitario();
        this.categoria = produto.getCategoria();
    }

    public Long getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getQtdEstoque() {
        return this.qtdEstoque;
    }

    public void setQtdEstoque(Integer qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public Double getValorUnitario() {
        return this.valorUnitario;
    }

    public void setValorUnitario(Double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

}