package br.com.gft.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gft.entites.Ingrediente;
import br.com.gft.repositories.IngredienteRepository;

@Service
public class IngredienteService {

	@Autowired
	private IngredienteRepository ingredienteRepository;

	public Ingrediente insert(Ingrediente ingrediente) {
		return ingredienteRepository.save(ingrediente);
	}

	public Ingrediente findById(Long id) throws Exception {
		Optional<Ingrediente> ingrediente = ingredienteRepository.findById(id);
		if (ingrediente.isEmpty()) {
			throw new Exception("Ingrediente não encontrado.");
		}
		return ingrediente.get();

	}

	public List<Ingrediente> findAll(String nome) {
		if (nome != null)
			return listByName(nome);

		return ingredienteRepository.findAll();
	}

	public List<Ingrediente> listByName(String nome) {
		return ingredienteRepository.findByNomeContainingIgnoreCase(nome);
	}
	
	public void delete(Long id) {
		ingredienteRepository.deleteById(id);
	}

	public void saveAll(List<Ingrediente> list) {
		ingredienteRepository.saveAll(list);
	}

}
