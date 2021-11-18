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

    @NotBlank
    @Size(max = 9)
    private String cep;

    public InserirEnderecoDTO() {
    }

    public InserirEnderecoDTO(Endereco endereco) {
        this.cep = endereco.getCep();
    }

    public String getCep() {
        return this.cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
    
}
