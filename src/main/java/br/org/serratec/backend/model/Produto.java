package br.org.serratec.backend.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_produto")
	private Long id;
	
	@Column
	private String nome;
	
	@Column
	private String descricao;
	
	@Column(name = "qtd_estoque")
	private Integer qtdEstoque;
	
	@Column(name = "data_cadastro")
	private LocalDate dataCadastro;
	
	@Column(name = "valor_unitario")
	private Double valorUnitario;
	
	@Column
	private Categoria categoria;
	
	
	
	
	
	

}
