package br.org.serratec.backend.dto;

import java.time.LocalDate;

import javax.persistence.Embeddable;

import br.org.serratec.backend.model.Cliente;
import br.org.serratec.backend.model.Endereco;

@Embeddable
public class AlterarClienteDTO {
	
	private String email;
	private String nomeUsuario;
	private String nomeCompleto;
	private String senha;
	private String cpf;
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
		this.endereco = cliente.getEndereco();
	}

	public LocalDate getDataNascimento() {
		return this.dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Endereco getEndereco() {
		return this.endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
}
