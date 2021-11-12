package br.org.serratec.backend.dto;

import javax.persistence.Embeddable;

import br.org.serratec.backend.model.Categoria;

@Embeddable
public class CategoriaDTO {

	private Long id;
	private String nome;
	private String descricao;

	public CategoriaDTO() {
		super();
	}

	public CategoriaDTO(Categoria categoria) {
		super();
		this.id = categoria.getId();
		this.nome = categoria.getNome();
		this.descricao = categoria.getDescricao();
	}

	public Long getId() {
		return id;
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
