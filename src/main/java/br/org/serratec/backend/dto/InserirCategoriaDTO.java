package br.org.serratec.backend.dto;

import javax.persistence.Embeddable;

@Embeddable
public class InserirCategoriaDTO {
	
	private Integer id;
	private String nome;
	private String descricao;
	
	public InserirCategoriaDTO() {
		super();
	}

	public InserirCategoriaDTO(Integer id, String nome, String descricao) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
