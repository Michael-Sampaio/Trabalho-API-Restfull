package br.org.serratec.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import br.org.serratec.backend.dto.EnderecoDTO;
import br.org.serratec.backend.dto.InserirEnderecoDTO;
import br.org.serratec.backend.exception.RecursoBadRequestException;
import br.org.serratec.backend.model.Endereco;
import br.org.serratec.backend.repository.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;

	/**
	 * METODO PARA INSERIR UM NOVO ENDEREÇO
	 * 
	 * @param endereco
	 * @return UM NOVO ENDEREÇO
	 */
	public EnderecoDTO inserir(InserirEnderecoDTO inserirEnderecoDTO) throws RecursoBadRequestException {

		if (enderecoRepository.findByCep(inserirEnderecoDTO.getCep()) != null) {

			throw new RecursoBadRequestException("Endereco ja cadastrado");
		}

		Optional<Endereco> end = Optional.ofNullable(enderecoRepository.findByCep(inserirEnderecoDTO.getCep()));
		if (end.isPresent()) {
			return new EnderecoDTO(end.get());
		} else {
			RestTemplate restTemplate = new RestTemplate();

			String uriViaCep = "https://viacep.com.br/ws/" + inserirEnderecoDTO.getCep() + "/json/";

			Optional<Endereco> enderecoViaCep = Optional
					.ofNullable(restTemplate.getForObject(uriViaCep, Endereco.class));
			if (enderecoViaCep.get().getCep() != null) {
				String cepSemTraco = enderecoViaCep.get().getCep().replaceAll("-", "");
				enderecoViaCep.get().setCep(cepSemTraco);

				Endereco e = enderecoViaCep.get();
				EnderecoDTO eDTO = new EnderecoDTO(e);
				return eDTO;

			} else {
				throw new RecursoBadRequestException("CEP informado não existe");
			}
		}
	}/**
	 * METODO PARA INSERIR UM NOVO ENDEREÇO
	 * 
	 * @param endereco
	 * @return UM NOVO ENDEREÇO
	 */
	public EnderecoDTO inserirEndereco(InserirEnderecoDTO inserirEnderecoDTO) throws RecursoBadRequestException {

		if (enderecoRepository.findByCep(inserirEnderecoDTO.getCep()) != null) {

			throw new RecursoBadRequestException("Endereco ja cadastrado");
		}

		Optional<Endereco> end = Optional.ofNullable(enderecoRepository.findByCep(inserirEnderecoDTO.getCep()));
		if (end.isPresent()) {
			return new EnderecoDTO(end.get());
		} else {
			RestTemplate restTemplate = new RestTemplate();

			String uriViaCep = "https://viacep.com.br/ws/" + inserirEnderecoDTO.getCep() + "/json/";

			Optional<Endereco> enderecoViaCep = Optional
					.ofNullable(restTemplate.getForObject(uriViaCep, Endereco.class));
			if (enderecoViaCep.get().getCep() != null) {
				String cepSemTraco = enderecoViaCep.get().getCep().replaceAll("-", "");
				enderecoViaCep.get().setCep(cepSemTraco);

				Endereco e = enderecoViaCep.get();
				EnderecoDTO eDTO = new EnderecoDTO(e);
				enderecoRepository.save(e);
				return eDTO;

			} else {
				throw new RecursoBadRequestException("CEP informado não existe");
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
	 * METODO PARA INSERIR UM ENDERECO
	 * 
	 * @param cep
	 * @return O CEP DO CLIENTE
	 * @throws HttpClientErrorException
	 */
	public EnderecoDTO buscar(String cep) throws HttpClientErrorException {
		Optional<Endereco> endereco = Optional.ofNullable(enderecoRepository.findByCep(cep));
		if (endereco.isPresent()) {
			return new EnderecoDTO(endereco.get());
		} else {
			InserirEnderecoDTO inserirEnderecoDTO = new InserirEnderecoDTO();
			inserirEnderecoDTO.setCep(cep);
			return inserir(inserirEnderecoDTO);
		}
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
