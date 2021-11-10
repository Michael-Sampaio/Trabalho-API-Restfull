package br.org.serratec.backend.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.org.serratec.backend.exception.EnumValidationException;

public enum Categoria {

	VESTUARIO(1, "Vestuário", "roupas em geral"), ELETRODOMESTICO(2, "Eletrodoméstico", "Artigos eletrônicos de casa"),
	ELETRONICO(3, "Eletrônico", "Artigos eletrônicos"), INFORMÁTICA(4, "Informática", "Artigos de informatica");

	private Integer id;
	private String nome;
	private String descricao;

	private Categoria(Integer id, String nome, String descricao) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
	}

	public Integer getId() {
		return this.id;
	}

	public String getNome() {
		return this.nome;
	}

	public String getDescricao() {
		return this.descricao;
	}

	@JsonCreator
	public static Categoria verifica(Integer valor) throws EnumValidationException {
		for (Categoria categoria : Categoria.values()) {
			if (valor.equals(categoria.getId())) {
				return categoria;
			}
		}
		throw new EnumValidationException();
	}

}
