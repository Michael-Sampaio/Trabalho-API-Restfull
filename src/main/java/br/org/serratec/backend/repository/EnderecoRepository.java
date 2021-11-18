package br.org.serratec.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.serratec.backend.model.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    public Optional<Endereco> findById(Long id);

    public Endereco findByCep(String cep);

    public Endereco findByLogradouro(String logradouro);

    public Endereco findByBairro(String bairro);

    public Endereco findByLocalidade(String cidade);

    public Endereco findByUf(String estado);

}
