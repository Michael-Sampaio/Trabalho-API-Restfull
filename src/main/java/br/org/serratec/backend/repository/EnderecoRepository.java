package br.org.serratec.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.serratec.backend.model.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    public Endereco findByCep(String cep);

    public Endereco findByRua(String rua);

    public Endereco findByBairro(String bairro);

    public Endereco findByCidade(String cidade);

    public Endereco findByNumero(String numero);

    public Endereco findByComplemento(String complemento);

    public Endereco findByEstado(String estado);

}
