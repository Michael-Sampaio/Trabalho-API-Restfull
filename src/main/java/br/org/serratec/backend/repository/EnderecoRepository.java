package br.org.serratec.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.serratec.backend.model.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    public Endereco findByCep(String cep);

    public Endereco findByLogradouro(String logradouro);

    public Endereco findByBairro(String bairro);

    public Endereco findByLocalidade(String cidade);

    public Endereco findByNumero(Integer numero);

    public Endereco findByComplemento(String complemento);

    public Endereco findByUf(String estado);

}
