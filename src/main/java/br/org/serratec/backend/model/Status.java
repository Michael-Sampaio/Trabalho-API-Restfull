package br.org.serratec.backend.model;

public enum Status {

	FINALIZADO("Finalizado"), NAO_FINALIZADO("Nao finalizado");

	private String nome;

	private Status(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return this.nome;
	}

}
