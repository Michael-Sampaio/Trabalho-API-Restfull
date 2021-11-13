package br.org.serratec.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.org.serratec.backend.config.MailConfig;
import br.org.serratec.backend.dto.AlterarClienteDTO;
import br.org.serratec.backend.dto.ClienteDTO;
import br.org.serratec.backend.dto.InserirClienteDTO;
import br.org.serratec.backend.exception.EmailException;
import br.org.serratec.backend.model.Cliente;
import br.org.serratec.backend.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	MailConfig mailConfig;

	/**
	 * METODO PARA INSERIR UM CLIENTE
	 * 
	 * @param cliente
	 * @return UM NOVO CLIENTE
	 * @throws EmailException
	 */

	public ClienteDTO inserir(InserirClienteDTO inserirClienteDTO) throws EmailException {

		if (clienteRepository.findByEmail(inserirClienteDTO.getEmail()) != null) {
			throw new EmailException("Email já existe ! Insira outro");
		}

		Cliente cliente = new Cliente();
		cliente.setCpf(inserirClienteDTO.getCpf());
		cliente.setDataNascimento(inserirClienteDTO.getDataNascimento());
		cliente.setNomeCompleto(inserirClienteDTO.getNomeCompleto());
		cliente.setTelefone(inserirClienteDTO.getTelefone());
		cliente.setNomeUsuario(inserirClienteDTO.getNomeUsuario());
		cliente.setEmail(inserirClienteDTO.getEmail());
		cliente.setEndereco(inserirClienteDTO.getEndereco());
		cliente.setSenha(bCryptPasswordEncoder.encode(inserirClienteDTO.getSenha()));
		clienteRepository.save(cliente);

		return new ClienteDTO(cliente);

	}

	/**
	 * METODO PARA LISTAR OS CLIENTES
	 * 
	 * @return LISTA DE CLIENTES
	 */
	public List<ClienteDTO> listar() {
		List<Cliente> clientes = clienteRepository.findAll();
		List<ClienteDTO> clientesDTO = new ArrayList<ClienteDTO>();

		for (Cliente cliente : clientes) {
			ClienteDTO clienteDTO = new ClienteDTO(cliente);
			clientesDTO.add(clienteDTO);
		}

		return clientesDTO;
	}

	/**
	 * METODO PARA EDITAR UM REGISTRO DE CLIENTE
	 * 
	 * @param alterarClienteDTO
	 * @return UM CLIENTE COM REGISTRO ALTERADO
	 * @throws EmailException
	 */
	public ClienteDTO alterar(AlterarClienteDTO alterarClienteDTO) throws EmailException {

		if (clienteRepository.findByEmail(alterarClienteDTO.getEmail()) != null) {
			throw new EmailException("Email já existe ! Insira outro");
		}
		Cliente cliente = new Cliente();
		cliente.setId(alterarClienteDTO.getId());

		cliente.setSenha(bCryptPasswordEncoder.encode(alterarClienteDTO.getSenha()));
		clienteRepository.save(cliente);
		mailConfig.enviarEmail(cliente.getEmail(), "Cadastro De Usuário Alterado", cliente.toString());
		return new ClienteDTO(cliente);
	}

	/**
	 * METODO PARA DELETAR UM CLIENTE
	 * 
	 * @param id
	 */
	public void deletar(Long id) {
		if (clienteRepository.existsById(id)) {
			clienteRepository.deleteById(id);
		}
	}

	/**
	 * METODO PARA LISTAR CLIENTE POR NUMERO COM TOTAL GERAL
	 * 
	 * @param id
	 * @return UM CLIENTE
	 */
	public ClienteDTO buscar(Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return new ClienteDTO(cliente.get());
	}

}
