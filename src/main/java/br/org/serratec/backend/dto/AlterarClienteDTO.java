package br.org.serratec.backend.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Embeddable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import br.org.serratec.backend.model.Cliente;
import br.org.serratec.backend.model.Endereco;

@Embeddable
public class AlterarClienteDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1090611581919824774L;

	@NotBlank
	@Email
	@Size(max = 30)
	private String email;

	@NotBlank
	@Size(max = 20)
	private String nomeUsuario;

	@NotBlank
	@Size(max = 60)
	private String nomeCompleto;

	@NotBlank
	@Size(max = 255)
	private String senha;

	@NotBlank
	@CPF
	@Size(min = 11, max = 11)
	private String cpf;

	@NotBlank
	@Size(max = 9)
	private String telefone;

	@Positive
	private Integer numero;

	@NotBlank
	@Size(max = 20)
	private String complemento;

	@Past
	private LocalDate dataNascimento;

	private Endereco endereco;

	public AlterarClienteDTO() {
		super();
	}

	public AlterarClienteDTO(Cliente cliente) {
		super();
		this.email = cliente.getEmail();
		this.nomeUsuario = cliente.getNomeUsuario();
		this.nomeCompleto = cliente.getNomeCompleto();
		this.senha = cliente.getSenha();
		this.cpf = cliente.getCpf();
		this.dataNascimento = cliente.getDataNascimento();
		this.numero = cliente.getNumero();
		this.complemento = cliente.getComplemento();
		this.endereco = cliente.getEndereco();
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNomeUsuario() {
		return this.nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getNomeCompleto() {
		return this.nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public LocalDate getDataNascimento() {
		return this.dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Integer getNumero() {
		return this.numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return this.complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Endereco getEndereco() {
		return this.endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

}
