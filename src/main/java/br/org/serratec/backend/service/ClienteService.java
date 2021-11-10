package br.org.serratec.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.org.serratec.backend.dto.AlterarClienteDTO;
import br.org.serratec.backend.dto.ClienteDTO;
import br.org.serratec.backend.dto.InserirClienteDTO;
import br.org.serratec.backend.exception.ClienteException;
import br.org.serratec.backend.exception.EmailException;
import br.org.serratec.backend.model.Cliente;
import br.org.serratec.backend.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	//METODO PARA INSERIR UM CLIENTE
	public ClienteDTO inserir(Cliente cliente) {
		cliente = clienteRepository.save(cliente);
		cliente.setSenha(bCryptPasswordEncoder.encode(cliente.getSenha()));
		clienteRepository.save(cliente);
		return new ClienteDTO(cliente);
	}

	// Metodo para listar os clientes
	public List<ClienteDTO> listar() {
		List<Cliente> clientes = clienteRepository.findAll();
		List<ClienteDTO> clientesDTO = new ArrayList<ClienteDTO>();

		for (Cliente cliente : clientes) {
			ClienteDTO clienteDTO = new ClienteDTO(cliente);
			clientesDTO.add(clienteDTO);
		}

		return clientesDTO;
	}

	//METODO PARA EDITAR UM REGISTRO DE CLIENTE
	public ClienteDTO alterar(AlterarClienteDTO alterarClienteDTO) {
			
		if (clienteRepository.findById(alterarClienteDTO.getId()) != null) {
		Cliente cliente = new Cliente();
		cliente.setId(alterarClienteDTO.getId());

		return new ClienteDTO(cliente);
		}else {
			throw new ClienteException();
		}
	}

}
