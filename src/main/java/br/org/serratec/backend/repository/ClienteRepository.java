package br.org.serratec.backend.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.serratec.backend.model.Cliente;
import br.org.serratec.backend.model.Endereco;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    public Cliente findByEmail(String email);
    public Cliente findByNomeUsuario(String nomeUsuario);
    public Cliente findByNomeCompleto(String nomeCompleto);
    public Cliente findByCpf(String cpf);
    public Cliente findByTelefone(String telefone);
    public Cliente findByDataNascimento(LocalDate dataNascimento);
    public Cliente findByEndereco(Endereco endereco);

}