package br.org.serratec.backend.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.org.serratec.backend.model.Cliente;
import br.org.serratec.backend.model.FotoCliente;
import br.org.serratec.backend.repository.FotoClienteRepository;

@Service
public class FotoClienteService {

	@Autowired
	FotoClienteRepository fotoClienteRepository;

	/**
	 * METODO PARA INSERIR UMA FOTO A UM CLIENTE
	 * 
	 * @param cliente
	 * @param file
	 * @return UMA FOTO REFERENTE A UM CLIENTE
	 * @throws IOException
	 */
	public FotoCliente inserir(Cliente cliente, MultipartFile file) throws IOException {
		FotoCliente fotoCliente = new FotoCliente();
		fotoCliente.setNome(file.getName());
		fotoCliente.setDados(file.getBytes());
		fotoCliente.setTipo(file.getContentType());
		fotoCliente.setCliente(cliente);
		return fotoClienteRepository.save(fotoCliente);
	}

	/**
	 * METODO PARA BUSCAR FOTO DO CLIENTE POR ID DO CLIENTE
	 * 
	 * @param id
	 * @return FOTO DO CLIENTE POR ID DO CLIENTE
	 */
	public FotoCliente buscar(Long id) {
		Optional<FotoCliente> fotoCliente = fotoClienteRepository.findById(id);

		if (fotoCliente.isPresent()) {
			return fotoCliente.get();
		}
		return null;
	}

}
