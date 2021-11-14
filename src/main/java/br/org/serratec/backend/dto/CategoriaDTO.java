package br.org.serratec.backend.dto;

import java.io.Serializable;

import javax.persistence.Embeddable;

import br.org.serratec.backend.model.Categoria;

@Embeddable
public class CategoriaDTO implements Serializable {

	private static final long serialVersionUID = 3015792880071967121L;

	private String nome;
	private String descricao;

	public CategoriaDTO() {
		super();
	}

	public CategoriaDTO(Categoria categoria) {
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
