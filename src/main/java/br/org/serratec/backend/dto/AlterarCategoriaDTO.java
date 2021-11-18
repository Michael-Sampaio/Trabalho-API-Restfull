package br.org.serratec.backend.dto;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.org.serratec.backend.model.Categoria;

@Embeddable
public class AlterarCategoriaDTO implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 7046118527322415583L;

	@NotBlank
	@Size(max = 30)
	private String nome;

	@NotBlank
	@Size(max = 150)
	private String descricao;

	public AlterarCategoriaDTO() {
		super();
	}

	public AlterarCategoriaDTO(Categoria categoria) {
		super();
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
