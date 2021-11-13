package br.org.serratec.backend.dto;

import javax.persistence.Embeddable;

@Embeddable
public class AlterarCategoriaDTO {

	private Long id;
	private String nome;
	private String descricao;

	public AlterarCategoriaDTO() {
		super();
	}

	public AlterarCategoriaDTO(Long id, String nome, String descricao) {
		super();
		this.nome = nome;
		this.descricao = descricao;
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
