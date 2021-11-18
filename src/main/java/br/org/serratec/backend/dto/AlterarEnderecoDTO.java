package br.org.serratec.backend.dto;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.org.serratec.backend.model.Endereco;

@Embeddable
public class AlterarEnderecoDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8111865095336035820L;

	@NotBlank
	@Size(max = 9)
	private String cep;

	@Size(max = 100)
	private String logradouro;

	@Size(max = 50)
	private String bairro;

	@Size(max = 30)
	private String localidade;

	@Size(max = 2)
	private String uf;

	public AlterarEnderecoDTO() {
		super();
	}

	public AlterarEnderecoDTO(Endereco endereco) {
		super();
		this.cep = endereco.getCep();
		this.logradouro = endereco.getLogradouro();
		this.bairro = endereco.getBairro();
		this.localidade = endereco.getLocalidade();
		this.uf = endereco.getUf();
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

}
