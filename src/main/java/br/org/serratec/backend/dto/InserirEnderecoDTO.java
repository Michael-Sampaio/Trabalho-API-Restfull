package br.org.serratec.backend.dto;

import br.org.serratec.backend.model.Endereco;

public class InserirEnderecoDTO {

	private String cep;

	public InserirEnderecoDTO() {
	}

	public InserirEnderecoDTO(Endereco endereco) {
		super();
		this.cep = endereco.getCep();
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

}
