package br.org.serratec.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import br.org.serratec.backend.dto.EnderecoDTO;
import br.org.serratec.backend.model.Endereco;
import br.org.serratec.backend.repository.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;

	/**
	 * METODO PARA BUSCAR ENDEREÇO POR CEP
	 * 
	 * @param cep
	 * @return O CEP DO CLIENTE
	 * @throws HttpClientErrorException
	 */
	public EnderecoDTO buscar(Endereco endereco) throws HttpClientErrorException {
		Optional<Endereco> end = Optional.ofNullable(enderecoRepository.findByCep(endereco.getCep()));
		if (end.isPresent()) {
			return new EnderecoDTO(end.get());
		} else {
			RestTemplate restTemplate = new RestTemplate();

			String uriViaCep = "https://viacep.com.br/ws/" + endereco.getCep() + "/json/";

			Optional<Endereco> enderecoViaCep = Optional
					.ofNullable(restTemplate.getForObject(uriViaCep, Endereco.class));
			if (enderecoViaCep.get().getCep() != null) {
				String cepSemTraco = enderecoViaCep.get().getCep().replaceAll("-", "");
				enderecoViaCep.get().setCep(cepSemTraco);

				Endereco e = enderecoViaCep.get();
				e.setNumero(endereco.getNumero());
				e.setComplemento(endereco.getComplemento());
				EnderecoDTO eDTO = new EnderecoDTO(e);
				e = enderecoRepository.save(e);
				return eDTO;
			} else {
				return null;
			}
		}
	}

	/**
	 * METODO PARA LISTAR OS ENDEREÇOS
	 * 
	 * @return UMA LISTA DE ENDEREÇOS REFERENTE AOS CLIENTES
	 */
	public List<EnderecoDTO> listar() {
		List<Endereco> enderecos = enderecoRepository.findAll();
		List<EnderecoDTO> enderecosDTO = new ArrayList<EnderecoDTO>();

		for (Endereco endereco : enderecos) {
			EnderecoDTO enderecoDTO = new EnderecoDTO(endereco);
			enderecosDTO.add(enderecoDTO);
		}

		return enderecosDTO;
	}

	/**
	 * METODO PARA INSERIR UM NOVO ENDEREÇO
	 * 
	 * @param endereco
	 * @return UM NOVO ENDEREÇO
	 */
	public EnderecoDTO inserir(Endereco endereco) {
		endereco = enderecoRepository.save(endereco);
		return new EnderecoDTO(endereco);
	}

	/**
	 * METODO PARA DELETAR UM ENDEÇO
	 * 
	 * @param id
	 */
	public void deletar(Long id) {
		if (enderecoRepository.existsById(id)) {
			enderecoRepository.deleteById(id);
		}
	}

}
