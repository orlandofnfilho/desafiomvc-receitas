package br.com.gft.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gft.entites.UnidadeMedida;
import br.com.gft.repositories.UnidadeMedidaRepository;

@Service
public class UnidadeMedidaService {

	@Autowired
	private UnidadeMedidaRepository unidadeMedidaRepository;

	public UnidadeMedida insert(UnidadeMedida unidadeMedida) {
		return unidadeMedidaRepository.save(unidadeMedida);
	}

	public UnidadeMedida findById(Long id) throws Exception {
		Optional<UnidadeMedida> unidadeMedida = unidadeMedidaRepository.findById(id);
		if (unidadeMedida.isEmpty()) {
			throw new Exception("Unidade de medida não econtrada.");
		}
		return unidadeMedida.get();

	}

	public List<UnidadeMedida> findAll(String nome) {
		if(nome != null)
			return listByName(nome);
		
		return unidadeMedidaRepository.findAll();
	}
	
	private List<UnidadeMedida> listAll() {
		return unidadeMedidaRepository.findAll();
	}

	private List<UnidadeMedida> listByName(String nome) {
		return unidadeMedidaRepository.findByNomeContainingIgnoreCase(nome);
	}

	public void delete(Long id) {
		unidadeMedidaRepository.deleteById(id);
	}

}
