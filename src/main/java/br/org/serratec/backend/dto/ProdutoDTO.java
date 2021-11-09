package br.org.serratec.backend.dto;

import java.time.LocalDate;

import javax.persistence.Column;

import br.org.serratec.backend.model.Categoria;
import br.org.serratec.backend.model.Produto;

public class ProdutoDTO {
	
private Long id;
	
	@Column
	private String nome;
	
	@Column
	private String descricao;
	
	@Column(name = "qtd_estoque")
	private Integer qtdEstoque;
	
	@Column(name = "data_cadastro")
	private LocalDate dataCadastro;
	
	@Column(name = "valor_unitario")
	private Double valorUnitario;
	
	@Column
	private Categoria categoria;
	
	public ProdutoDTO() {
		// TODO Auto-generated constructor stub
	}

	public ProdutoDTO(Produto produto) {
		super();
		this.id = produto.getId();
		this.nome = produto.getNome();
		this.descricao = produto.getDescricao();
		this.qtdEstoque = produto.getQtdEstoque();
		this.dataCadastro = produto.getDataCadastro();
		this.valorUnitario = produto.getValorUnitario();
		this.categoria = produto.getCategoria();
	}
	
	

}
