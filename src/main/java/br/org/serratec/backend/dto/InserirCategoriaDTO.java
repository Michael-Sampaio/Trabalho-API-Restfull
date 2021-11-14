package br.org.serratec.backend.dto;

import br.org.serratec.backend.model.Categoria;

public class InserirCategoriaDTO {

	private Long id;
	private String nome;
	private String descricao;

	public InserirCategoriaDTO() {
	}

	public InserirCategoriaDTO(Categoria categoria) {
		this.nome = categoria.getNome();
		this.descricao = categoria.getDescricao();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

}
