package br.org.serratec.backend.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import br.org.serratec.backend.model.Categoria;
import br.org.serratec.backend.model.Produto;

@Embeddable
public class InserirProdutoDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1874537595817669750L;

	@NotBlank
	@Size(max = 30)
	private String nome;

	@NotBlank
	@Size(max = 100)
	private String descricao;

	@PositiveOrZero
	private Integer qtdEstoque;

	@Positive
	private Double valorUnitario;

	@PastOrPresent
	private LocalDate dataCadastro;


	private Categoria categoria;

	public InserirProdutoDTO() {
	}

	public InserirProdutoDTO(Produto produto) {
		this.nome = produto.getNome();
		this.descricao = produto.getDescricao();
		this.qtdEstoque = produto.getQtdEstoque();
		this.dataCadastro = produto.getDataCadastro();
		this.valorUnitario = produto.getValorUnitario();
		this.categoria = produto.getCategoria();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getQtdEstoque() {
		return qtdEstoque;
	}

	public void setQtdEstoque(Integer qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}
