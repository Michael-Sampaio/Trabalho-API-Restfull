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
	
	public FotoCliente inserir(Cliente cliente, MultipartFile file) throws IOException {
		FotoCliente fotoCliente = new FotoCliente();
		fotoCliente.setNome(file.getName());
		fotoCliente.setDados(file.getBytes());
		fotoCliente.setTipo(file.getContentType());
		fotoCliente.setCliente(cliente);
		return fotoClienteRepository.save(fotoCliente);
	}
	
	public FotoCliente buscar(Long id) {
		Optional<FotoCliente> foto = fotoClienteRepository.findById(id);

		if (foto.isPresent()) {
			return foto.get();
		}
		return null;
	}

}
