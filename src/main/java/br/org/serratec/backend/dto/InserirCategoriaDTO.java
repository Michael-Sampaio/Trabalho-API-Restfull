package br.org.serratec.backend.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.org.serratec.backend.model.Categoria;

public class InserirCategoriaDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 427669895544928229L;

	@NotBlank
	@Size(max = 30)
	private String nome;

	@NotBlank
	@Size(max = 150)
	private String descricao;

	public InserirCategoriaDTO() {
	}

	public InserirCategoriaDTO(Categoria categoria) {
		this.nome = categoria.getNome();
		this.descricao = categoria.getDescricao();
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
