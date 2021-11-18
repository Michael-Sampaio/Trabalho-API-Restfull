package br.org.serratec.backend.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.org.serratec.backend.model.Endereco;

public class InserirEnderecoDTO implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 7681121951525806758L;

    private Long id;

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

    public InserirEnderecoDTO() {
    }

    public InserirEnderecoDTO(Endereco endereco) {
        this.cep = endereco.getCep();
        this.logradouro = endereco.getLogradouro();
        this.bairro = endereco.getBairro();
        this.localidade = endereco.getLocalidade();
        this.uf = endereco.getUf();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCep() {
        return this.cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return this.logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return this.bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLocalidade() {
        return this.localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getUf() {
        return this.uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

}
